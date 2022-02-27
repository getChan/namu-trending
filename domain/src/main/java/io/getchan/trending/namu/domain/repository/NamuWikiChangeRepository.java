package io.getchan.trending.namu.domain.repository;

import io.getchan.trending.namu.domain.ChangeCount;

import java.time.Instant;
import java.util.stream.Stream;

public interface NamuWikiChangeRepository {

    /**
     * @param entity documentTitle, changedTime 이 동일하면 중복 저장되지 않는다.
     */
    void save(NamuWikiChangeEntity entity);

    Stream<ChangeCount> countByTitle(Instant start, Instant stop);

    Stream<NamuWikiChangeEntity> findAll(String title, Instant start, Instant stop);

}
