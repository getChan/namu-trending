package io.getchan.trending.namu.collector;


import io.getchan.trending.namu.domain.NamuWikiChange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class LastCollectedList {
    private final List<NamuWikiChange> namuWikiChanges;
    private final int limit;

    public LastCollectedList(@Value("${last-collected-set.limit}") int limit) {
        if (limit <= 0) {
            throw new IllegalArgumentException("limit must greater than 0");
        }
        this.namuWikiChanges = new ArrayList<>(limit);
        this.limit = limit;
    }
    
    private void add(NamuWikiChange namuWikiChange) {
        this.namuWikiChanges.add(namuWikiChange);
        if (namuWikiChanges.size() > limit) {
            namuWikiChanges.remove(0);
        }
    }

    /**
     * @return true if not exist
     */
    public boolean addIfNotExists(NamuWikiChange newNamuWiki) {
        synchronized (namuWikiChanges) {
            if (namuWikiChanges.contains(newNamuWiki)) {
                return false;
            } else {
                this.add(newNamuWiki);
                return true;
            }
        }
    }
}
