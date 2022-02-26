package io.getchan.trending.namu.api.repository;

import io.getchan.trending.namu.domain.ChangeCount;
import io.getchan.trending.namu.domain.repository.NamuWikiChangeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class InfluxNamuWikiChangeRepositoryTest {

    @Autowired
    NamuWikiChangeRepository repository;

    @Test
    void countByTitle() {
        final Stream<ChangeCount> countByTitle = repository.countByTitle(Instant.now().minus(1, ChronoUnit.DAYS), Instant.now());
        assertThat(countByTitle).isNotEmpty();
    }
}