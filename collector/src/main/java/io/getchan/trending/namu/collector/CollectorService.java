package io.getchan.trending.namu.collector;

import io.getchan.trending.namu.domain.NamuWikiChange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

@Slf4j
@Service
public class CollectorService {
    private static final URI TARGET_URI = URI.create("https://namu.wiki/sidebar.json");

    private final WebClient webClient;
    private final LastCollectedList lastCollectedList;
    private final long crawlDelayMillis;
    private final AtomicLong nextCrawlTimeMillis = new AtomicLong(System.currentTimeMillis());

    public CollectorService(WebClient webClient, LastCollectedList lastCollectedList,
                            @Value("${collect.delay}") int crawlDelaySeconds) {
        this.webClient = webClient;
        this.lastCollectedList = lastCollectedList;
        this.crawlDelayMillis = crawlDelaySeconds * 1000L;
    }


    public Stream<NamuWikiChange> collect() {
        synchronized (nextCrawlTimeMillis) {
            if (nextCrawlTimeMillis.get() <= System.currentTimeMillis()) {
                nextCrawlTimeMillis.addAndGet(crawlDelayMillis);

                return requestSidebarDTO()
                        .map(SidebarDTO::toNamuWikiChange)
                        .filter(lastCollectedList::addIfNotExists)
                        .doOnNext(wikiChange -> log.info("Collect : {}", wikiChange.getDocumentTitle()))
                        .toStream();
            } else {
                return Stream.empty();
            }
        }
    }

    public Flux<SidebarDTO> requestSidebarDTO() {
        return webClient.get()
                .uri(TARGET_URI)
                .retrieve()
                .bodyToFlux(SidebarDTO.class);
    }

}
