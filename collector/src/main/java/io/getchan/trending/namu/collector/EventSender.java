package io.getchan.trending.namu.collector;

import io.getchan.trending.namu.domain.NamuWikiChange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Supplier;

@Configuration
public class EventSender {

    @Bean
    public Supplier<NamuWikiChange> sendEvents() {
        return () -> NamuWikiChange.builder().build();
        // TODO https://dataflow.spring.io/docs/stream-developer-guides/streams/standalone-stream-sample/#business-logic-1
    }
}
