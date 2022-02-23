package io.getchan.trending.namu.collector;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class CollectorConfiguration {

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .defaultHeader("User-Agent", "Getchan/0.0.1")
                .defaultHeader("Accept", "*/*")
                .defaultHeader("Connection", "keep-alive")
                .build();
    }

    @Bean
    public InfluxDBClient influxDBClient(@Value("influxdb.host") String host,
                                         @Value("influxdb.port") String port,
                                         @Value("influxdb.token") char[] token,
                                         @Value("influxdb.org") String org,
                                         @Value("influxdb.bucket") String bucket) {
        return InfluxDBClientFactory.create(host + ":" + port, token, org, bucket);
    }
}
