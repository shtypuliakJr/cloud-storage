package edu.nau.cs.api.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;

public interface AuthenticationFilter {

    GatewayFilter apply(Config config);

    class Config {
        // empty class as I don't need any particular configuration
    }

}
