package com.madisfinance.walletgateway.config;

import org.springframework.cloud.gateway.config.HttpClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.netty.resolver.DefaultAddressResolverGroup;

@Configuration
public class HttpClientResolverFixConfig {
    @Bean
    HttpClientCustomizer httpClientResolverCustomizer() {
        return httpClient -> httpClient.resolver(DefaultAddressResolverGroup.INSTANCE);
    }
}
