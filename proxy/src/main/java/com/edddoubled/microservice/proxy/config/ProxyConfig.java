package com.edddoubled.microservice.proxy.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class ProxyConfig {

    @Bean
    RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("solver_route", route -> route.path("/solver/**")
                        .and()
                        .method(HttpMethod.GET)
                        .filters(filter -> filter.stripPrefix(1)
                        )
                                .uri("lb://solver"))
                .route("generator_route", route -> route.path("/generator/**")
                        .filters(filter -> filter.stripPrefix(1)
                        )
                        .uri("lb://generator"))
                .build();
    }
}
