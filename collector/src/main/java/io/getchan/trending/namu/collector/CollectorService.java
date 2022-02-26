package io.getchan.trending.namu.collector;

import io.getchan.trending.namu.domain.repository.NamuWikiChangeEntity;
import io.getchan.trending.namu.domain.repository.NamuWikiChangeRepository;
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
    private final NamuWikiChangeRepository namuWikiChangeRepository;

    public CollectorService(WebClient webClient, NamuWikiChangeRepository namuWikiChangeRepository) {
        this.webClient = webClient;
        this.namuWikiChangeRepository = namuWikiChangeRepository;
    }

    @Scheduled(fixedDelayString = "${collect.delay}", timeUnit = TimeUnit.SECONDS)
    public void collect() {
        requestSidebarDTO()
                .map(SidebarDTO::toNamuWikiChange)
                .doOnNext(namuWiki -> log.info("{}", namuWiki))
                .map(NamuWikiChangeEntity::from)
                .subscribe(namuWikiChangeRepository::save)
                .isDisposed();
    }

    public Flux<SidebarDTO> requestSidebarDTO() {
        return webClient.get()
                .uri(TARGET_URI)
                .retrieve()
                .bodyToFlux(SidebarDTO.class);
    }

}
