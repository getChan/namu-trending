package io.getchan.trending.namu.collector;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CollectorServiceTest {

    CollectorService collectorService = new CollectorService(WebClient.builder().build(), new LastCollectedList(15), 5);

    @Test
    void requestSidebarDTO() {
        final List<SidebarDTO> sidebarDTOs = collectorService.requestSidebarDTO()
                .toStream().toList();

        assertThat(sidebarDTOs).hasSizeGreaterThan(1);
    }

    @Test
    void collect() {
        final long first = collectorService.collect().count();
        System.out.println("first = " + first);
        final long count = collectorService.collect().count();
        System.out.println("count = " + count);
        assertThat(count).isLessThan(15);
    }

}