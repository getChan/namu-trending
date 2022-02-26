package io.getchan.trending.namu.api;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.QueryApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfiguration {
    @Bean(destroyMethod = "close")
    public InfluxDBClient influxDBClient(@Value("${influxdb.url}") String url,
                                         @Value("${influxdb.token}") char[] token,
                                         @Value("${influxdb.org}") String org,
                                         @Value("${influxdb.bucket}") String bucket) {
        return InfluxDBClientFactory.create(url, token, org, bucket);
    }

    @Bean
    public QueryApi influxQueryApi(InfluxDBClient influxDBClient) {
        return influxDBClient.getQueryApi();
    }
}
