package edu.nau.cs.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("file-service", r -> r.path("/cs-api/file-service/**")
                        .filters(f -> f.rewritePath("/cs-api/file-service",
                                        "/cs-api")
                                .addResponseHeader("X_Powered-By", "Cloud storage Gateway Service")
                        )
                        .uri("http://localhost:8010")
                )
                .route("meta-service", r -> r.path("/cs-api/meta-service/**")
                        .filters(f -> f.rewritePath("/cs-api/meta-service",
                                        "/cs-api")
                                .addRequestHeader("userId", "user-value-id")
                                .addResponseHeader("X_Powered-By", "Cloud storage Gateway Service")
                        )
                        .uri("http://localhost:8011")
                )
                .build();
    }

}
