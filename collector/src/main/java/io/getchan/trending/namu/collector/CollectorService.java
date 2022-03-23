package io.getchan.trending.namu.collector;

import io.getchan.trending.namu.domain.NamuWikiChange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Slf4j
@Service
@EnableScheduling
public class CollectorService {
    private static final URI TARGET_URI = URI.create("https://namu.wiki/sidebar.json");

    private final WebClient webClient;
    private final LastCollectedList lastCollectedList;
    private final int crawlDelaySeconds;

    public CollectorService(WebClient webClient, LastCollectedList lastCollectedList,
                            @Value("${collect.delay}") int crawlDelaySeconds) {
        this.webClient = webClient;
        this.lastCollectedList = lastCollectedList;
        this.crawlDelaySeconds = crawlDelaySeconds;
    }

    public Stream<NamuWikiChange> collect() {

        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(crawlDelaySeconds));
        } catch (InterruptedException ie) {
            log.info("Cancel waiting crawlDelay", ie);
            Thread.currentThread().interrupt();
        }
        return requestSidebarDTO()
                .map(SidebarDTO::toNamuWikiChange)
                .filter(lastCollectedList::addIfNotExists)
                .doOnNext(wikiChange -> log.info("Collect : {}", wikiChange.getDocumentTitle()))
                .toStream();

    }

    public Flux<SidebarDTO> requestSidebarDTO() {
        return webClient.get()
                .uri(TARGET_URI)
                .retrieve()
                .bodyToFlux(SidebarDTO.class);
    }

}
