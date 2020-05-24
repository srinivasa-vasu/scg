package io.humourmind.gateway.route;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RouteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RouteServiceApplication.class, args);
	}

	@Bean
	public RouteLocator apiRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes().route("path",
				predicate -> predicate.path("/p/hello").uri("http://localhost:8081"))
				.route("host-a-path",
						predicate -> predicate.path("/pnh/hello").and()
								.host("humourmind.com").uri("http://localhost:8081"))
				.route("request-header",
						predicate -> predicate.header("X-Request-Id", "\\d+")
								.filters(filter -> filter.prefixPath("/hello"))
								.uri("http://localhost:8081"))
				.route("path-prefix",
						predicate -> predicate.path("/pp/hello")
								.filters(filter -> filter.prefixPath("/springfans"))
								.uri("http://localhost:8081"))
				.route("response-header",
						predicate -> predicate.header("X-Req-Id", "test")
								.filters(filter -> filter.prefixPath("/hello").addResponseHeader("X-Response-Id", "1"))
								.uri("http://localhost:8081"))
				.route("weight-bs-1",
						predicate -> predicate.weight("bs", 8)
								.filters(filter -> filter.stripPrefix(1).prefixPath("/hello"))
								.uri("http://localhost:8081"))
				.route("weight-bs-2",
						predicate -> predicate.weight("bs", 2)
								.filters(filter -> filter.stripPrefix(1).prefixPath("/hello2"))
								.uri("http://localhost:8081"))
				.build();
	}

}
