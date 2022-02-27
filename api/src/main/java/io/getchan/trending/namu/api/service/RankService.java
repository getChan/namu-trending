package io.getchan.trending.namu.api.service;

import io.getchan.trending.namu.domain.ChangeCount;
import io.getchan.trending.namu.domain.repository.NamuWikiChangeRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;

@Service
public class RankService {
    private final NamuWikiChangeRepository namuWikiChangeRepository;

    public RankService(NamuWikiChangeRepository namuWikiChangeRepository) {
        this.namuWikiChangeRepository = namuWikiChangeRepository;
    }


    public List<ChangeCount> dayRank(int topN) {
        return countByTitleNow(topN, Instant.now().minus(1, ChronoUnit.DAYS));
    }

    public List<ChangeCount> weekRank(int topN) {
        return countByTitleNow(topN, Instant.now().minus(7, ChronoUnit.DAYS));
    }

    /**
     * @implNote topN 값에 상관없이 기간내 모든 랭킹을 가져옴. 미리 정렬된 테이블(뷰)을 생성해두면 성능 개선될 듯.
     */
    private List<ChangeCount> countByTitleNow(int topN, Instant start) {
        return namuWikiChangeRepository.countByTitle(start, Instant.now())
                .sorted(Comparator.comparingLong(ChangeCount::getCount).reversed())
                .limit(topN)
                .toList();
    }
}
