package com.bamboo.gw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.factory.StripPrefixGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GWApplication {

  public static void main(String[] args) {
    SpringApplication.run(GWApplication.class, args);
  }

  @Bean
  public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
    StripPrefixGatewayFilterFactory.Config config = new StripPrefixGatewayFilterFactory.Config();
    config.setParts(1);
    return builder.routes()
        .route("host_route", r -> r.path("/servicea/**").filters(f -> f.stripPrefix(1))
            .uri("http://localhost:8090"))
        .route("host_route", r -> r.path("/serviceb/**").filters(f -> f.stripPrefix(1))
            .uri("http://localhost:8091"))
        .build();
  }

}