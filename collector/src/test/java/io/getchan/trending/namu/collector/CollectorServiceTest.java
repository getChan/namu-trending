package io.getchan.trending.namu.collector;

import io.getchan.trending.namu.domain.collector.SidebarDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CollectorServiceTest {

    @Autowired
    CollectorService collectorService;

    @Test
    void download() {
        final List<SidebarDTO> sidebarDTOs = collectorService.download()
                .toStream().toList();

        assertThat(sidebarDTOs.size()).isGreaterThan(1);
//        System.out.println("sidebarDTOS = " + sidebarDTOs);
    }
}