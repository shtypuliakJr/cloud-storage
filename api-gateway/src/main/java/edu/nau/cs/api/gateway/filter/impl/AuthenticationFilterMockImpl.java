package edu.nau.cs.api.gateway.filter.impl;

import edu.nau.cs.api.gateway.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import static edu.nau.cs.api.gateway.constants.FieldConfig.USER_ID_HEADER;

@ConditionalOnProperty(value = "auth.enabled", havingValue = "false")
@Component
public class AuthenticationFilterMockImpl extends AbstractGatewayFilterFactory<AuthenticationFilterMockImpl.Config> implements AuthenticationFilter {

    private final String userId;

    public AuthenticationFilterMockImpl(@Value("${auth.mock.user.id}") String userId) {
        super(Config.class);
        this.userId = userId;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> chain.filter(exchange.mutate()
                .request(exchange.getRequest()
                        .mutate()
                        .header(USER_ID_HEADER, userId)
                        .build())
                .build());
    }

}