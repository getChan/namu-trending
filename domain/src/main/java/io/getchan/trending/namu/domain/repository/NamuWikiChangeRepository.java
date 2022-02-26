package io.getchan.trending.namu.domain.repository;

import io.getchan.trending.namu.domain.ChangeCount;

import java.time.Instant;
import java.util.stream.Stream;

public interface NamuWikiChangeRepository {

    void save(NamuWikiChangeEntity entity);

    Stream<ChangeCount> countByTitle(Instant start, Instant stop);

}
