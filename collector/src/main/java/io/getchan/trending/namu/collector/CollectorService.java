package io.getchan.trending.namu.collector;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
@EnableScheduling
public class CollectorService {
    private static final URI TARGET_URI = URI.create("https://namu.wiki/sidebar.json");
    private final WebClient webClient;

    public CollectorService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<SidebarDTO> requestSidebarDTO() {
        return webClient.get()
                .uri(TARGET_URI)
                .retrieve()
                .bodyToFlux(SidebarDTO.class);
    }

    @Scheduled(fixedDelayString = "${collect.delay}", timeUnit = TimeUnit.SECONDS)
    public void collect() {
        requestSidebarDTO()
                .map(SidebarDTO::toSidebar).toStream().forEach(System.out::println);
        // TODO
    }

}
