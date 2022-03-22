package io.getchan.trending.namu.collector;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@EnableScheduling
public class CollectorService {
    private static final URI TARGET_URI = URI.create("https://namu.wiki/sidebar.json");

    private final WebClient webClient;
    private final LastCollectedList lastCollectedList;

    public CollectorService(WebClient webClient, LastCollectedList lastCollectedList) {
        this.webClient = webClient;
        this.lastCollectedList = lastCollectedList;
    }

    @Scheduled(fixedDelayString = "${collect.delay}", timeUnit = TimeUnit.SECONDS)
    public void collect() {
        requestSidebarDTO()
                .map(SidebarDTO::toNamuWikiChange)
                .filter(lastCollectedList::addIfNotExists)
                .doOnNext(wikiChange -> log.info("Collect : {}", wikiChange.getDocumentTitle()));
    }

    public Flux<SidebarDTO> requestSidebarDTO() {
        return webClient.get()
                .uri(TARGET_URI)
                .retrieve()
                .bodyToFlux(SidebarDTO.class);
    }

}
