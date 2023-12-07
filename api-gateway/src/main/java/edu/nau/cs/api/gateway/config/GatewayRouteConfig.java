package edu.nau.cs.api.gateway.config;

import edu.nau.cs.api.gateway.filter.AuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class GatewayRouteConfig {

    private final AuthenticationFilter authenticationFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("file-service", r ->
                        r.path("/cs-api/files/**")
                                .filters(f -> f.filters(authenticationFilter))
                                .uri("http://localhost:8010")
                )
                .route("meta-service", r ->
                        r.path("/cs-api/devices/**",
                                        "/cs-api/folders/**",
                                        "/cs-api/tags/**",
                                        "/cs-api/search/**")
                                .filters(f -> f.filters(authenticationFilter))
                                .uri("http://localhost:8011")
                )
                .route("notification-service", r ->
                        r.path("/cs-api/workspace/**")
                                .filters(f -> f.filters(authenticationFilter))
                                .uri("http://localhost:8012"))
                .route("auth-service", r ->
                        r.path("cs-api/auth/**")
                                .filters(f -> f.filter(authenticationFilter))
                                .uri("http://localhost:9000"))
                .build();
    }

}