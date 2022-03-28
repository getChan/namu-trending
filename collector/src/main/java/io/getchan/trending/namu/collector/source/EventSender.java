package io.getchan.trending.namu.collector.source;

import io.getchan.trending.namu.collector.CollectorService;
import io.getchan.trending.namu.domain.NamuWikiChangeMessage;
import org.springframework.cloud.function.context.PollableBean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Configuration
public class EventSender {

    private final CollectorService collectorService;

    public EventSender(CollectorService collectorService) {
        this.collectorService = collectorService;
    }

    @PollableBean()
    public Supplier<Flux<NamuWikiChangeMessage>> sendEvents() {
        return () ->
                Flux.fromStream(collectorService.collect().map(NamuWikiChangeMessage::from));
    }
}
