package io.getchan.trending.namu.collector;

import io.getchan.trending.namu.domain.collector.SidebarDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.net.URI;

@Service
public class CollectorService {
    private final WebClient webClient;
    private static final URI TARGET_URI = URI.create("https://namu.wiki/sidebar.json");

    public CollectorService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<SidebarDTO> download() {
        return webClient.get()
                .uri(TARGET_URI)
                .retrieve()
                .bodyToFlux(SidebarDTO.class);
    }


}
