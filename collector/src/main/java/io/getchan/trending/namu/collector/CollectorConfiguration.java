package io.getchan.trending.namu.collector;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApi;
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

    @Bean(destroyMethod = "close")
    public InfluxDBClient influxDBClient(@Value("${influxdb.url}") String url,
                                         @Value("${influxdb.token}") char[] token,
                                         @Value("${influxdb.org}") String org,
                                         @Value("${influxdb.bucket}") String bucket) {
        return InfluxDBClientFactory.create(url, token, org, bucket);
    }

    @Bean(destroyMethod = "close")
    public WriteApi influxWriteApi(InfluxDBClient influxDBClient) {
        return influxDBClient.makeWriteApi();
    }
}
