package edu.nau.cs.api.gateway.filter.impl;

import edu.nau.cs.api.gateway.filter.AuthenticationFilter;
import edu.nau.cs.api.gateway.model.UserDto;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import static edu.nau.cs.api.gateway.constants.FieldConfig.USER_ID_HEADER;

@ConditionalOnProperty(value = "auth.enabled", havingValue = "true")
@Component
public class AuthenticationFilterImpl extends AbstractGatewayFilterFactory<AuthenticationFilterImpl.Config> implements AuthenticationFilter {

    private final WebClient.Builder webClientBuilder;

    public AuthenticationFilterImpl(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            System.out.println("auth-filter");
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                throw new RuntimeException("Missing authorization information  ");
            }

            String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

            String[] parts = authHeader.split(" ");

            if (parts.length != 2 || !"Bearer".equals(parts[0])) {
                throw new RuntimeException("Incorrect authorization structure");
            }
            return webClientBuilder.build()
                    .post()
                    .uri("http://service-users/users/validateToken?token=" + parts[1])
                    .retrieve().bodyToMono(UserDto.class)
                    .map(userDto -> {
                        exchange.getRequest()
                                .mutate()
                                .header(USER_ID_HEADER, String.valueOf(userDto.getId()));
                        return exchange;
                    }).flatMap(chain::filter);
        };
    }

}