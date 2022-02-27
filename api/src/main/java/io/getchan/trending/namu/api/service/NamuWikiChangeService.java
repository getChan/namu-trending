package io.getchan.trending.namu.api.service;

import io.getchan.trending.namu.domain.NamuWikiChange;
import io.getchan.trending.namu.domain.repository.NamuWikiChangeEntity;
import io.getchan.trending.namu.domain.repository.NamuWikiChangeRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class NamuWikiChangeService {
    private final NamuWikiChangeRepository namuWikiChangeRepository;

    public NamuWikiChangeService(NamuWikiChangeRepository namuWikiChangeRepository) {
        this.namuWikiChangeRepository = namuWikiChangeRepository;
    }

    public List<NamuWikiChange> changesOf(String title, Instant start, Instant stop) {
        return namuWikiChangeRepository.findAll(title, start, stop)
                .map(NamuWikiChangeEntity::toDomain)
                .toList();
    }
}
