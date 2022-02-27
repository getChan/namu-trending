package io.getchan.trending.namu.api;

import io.getchan.trending.namu.api.service.NamuWikiChangeService;
import io.getchan.trending.namu.domain.NamuWikiChange;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class NamuWikiChangeServiceTest {

    @Autowired
    NamuWikiChangeService service;

    @Test
    void changesOf() {
        final List<NamuWikiChange> namuWikiChanges = service.changesOf("노르웨이/왕실", Instant.parse("2022-02-27T13:51:59Z"), Instant.parse("2022-02-27T16:51:59Z"));

        assertThat(namuWikiChanges).hasSize(1);
        final NamuWikiChange namuWikiChange = namuWikiChanges.get(0);
        assertThat(namuWikiChange.getChangedStatus()).isEqualTo("normal");
        assertThat(namuWikiChange.getChangedTime()).isEqualTo("2022-02-27T15:51:59Z");
    }
}