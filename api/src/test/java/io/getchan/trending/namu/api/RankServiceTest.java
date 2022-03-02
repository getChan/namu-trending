package io.getchan.trending.namu.api;

import io.getchan.trending.namu.api.service.RankService;
import io.getchan.trending.namu.domain.ChangeCount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RankServiceTest {

    @Autowired
    RankService rankService;

    @Test
    void dayRank() {

        final List<ChangeCount> changeRank = rankService.dayRank(10);

        System.out.println("changeRank = " + changeRank);
        assertThat(changeRank)
                .hasSizeBetween(0, 10)
                .isSortedAccordingTo(Comparator.comparingLong(ChangeCount::getCount).reversed());
    }
}