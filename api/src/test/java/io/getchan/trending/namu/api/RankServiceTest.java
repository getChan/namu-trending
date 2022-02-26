package io.getchan.trending.namu.api;

import io.getchan.trending.namu.domain.ChangeCount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class RankServiceTest {

    @Autowired
    RankService rankService;

    @Test
    void dayRank() {

        final List<ChangeCount> changeRank = rankService.dayRank(10);

        assertThat(changeRank)
                .hasSizeBetween(0, 10)
                .isSortedAccordingTo(Comparator.comparingLong(ChangeCount::getCount).reversed());
    }
}