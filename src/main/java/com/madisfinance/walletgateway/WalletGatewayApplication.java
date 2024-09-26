package com.madisfinance.walletgateway;

import java.util.ArrayList;
import java.util.List;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class WalletGatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(WalletGatewayApplication.class, args);
	}

	private final RouteDefinitionLocator locator;
	

	public WalletGatewayApplication(RouteDefinitionLocator locator) {
		this.locator = locator;
	}


	@Bean
	List<GroupedOpenApi> apis() {
		List<GroupedOpenApi> groups = new ArrayList<>();
		List<RouteDefinition> definitions = locator
				.getRouteDefinitions().collectList().block();
		assert definitions != null;
		definitions.stream().filter(routeDefinition -> routeDefinition
				.getId()
				.matches(".*-service"))
				.forEach(routeDefinition -> {
					String name = routeDefinition.getId()
							.replace("-service", "");
					groups.add(GroupedOpenApi.builder()
							.pathsToMatch("/" + name + "/**").group(name).build());
				});
		return groups;
	}
}
