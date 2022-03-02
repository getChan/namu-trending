package io.getchan.trending.namu.collector;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CollectorServiceTest {

    @Autowired
    CollectorService collectorService;

    @Test
    void requestSidebarDTO() {
        final List<SidebarDTO> sidebarDTOs = collectorService.requestSidebarDTO()
                .toStream().toList();

        assertThat(sidebarDTOs).hasSizeGreaterThan(1);
    }

    @Test
    void collect() {
        collectorService.collect();
    }
}