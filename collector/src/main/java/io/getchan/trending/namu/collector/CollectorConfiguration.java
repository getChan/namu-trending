package io.getchan.trending.namu.collector;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class CollectorConfiguration {

    @Bean
    public WebClient webClient() {
        return WebClient.create();
    }
}
