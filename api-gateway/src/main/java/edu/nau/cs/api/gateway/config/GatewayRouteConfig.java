package edu.nau.cs.api.gateway.config;

import edu.nau.cs.api.gateway.filter.AuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class GatewayRouteConfig {

    @Autowired
    private AuthenticationFilter authenticationFilter;

    @Value("${url.service.file}")
    private String fileServiceURL;

    @Value("${url.service.meta}")
    private String metaServiceURL;

    @Value("${url.service.notification}")
    private String notificationServiceURL;

    @Value("${url.service.auth}")
    private String authServiceURL;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        GatewayFilter authFilter = authenticationFilter.apply(new AuthenticationFilter.Config());
        return builder.routes()
                .route("file-service", r ->
                        r.path("/cs-api/files/**")
                                .filters(f -> f.filters(authFilter))
                                .uri(fileServiceURL)
                )
                .route("meta-service", r ->
                        r.path("/cs-api/devices/**",
                                        "/cs-api/folders/**",
                                        "/cs-api/tags/**",
                                        "/cs-api/search/**")
                                .filters(f -> f.filters(authFilter))
                                .uri(metaServiceURL)
                )
                .route("notification-service", r ->
                        r.path("/cs-api/workspace/**")
                                .filters(f -> f.filters(authFilter))
                                .uri(notificationServiceURL))
                .route("auth-service", r ->
                        r.path("cs-api/auth/**")
                                .filters(f -> f.filters(authFilter))
                                .uri(authServiceURL))
                .build();
    }

}