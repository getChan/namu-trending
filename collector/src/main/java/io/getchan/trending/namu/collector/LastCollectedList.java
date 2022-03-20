package io.getchan.trending.namu.collector;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.getchan.trending.namu.domain.NamuWikiChange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class LastCollectedList {
    private final File jsonFile;
    private final ObjectMapper objectMapper;
    private final List<NamuWikiChange> namuWikiChanges;
    private final int limit;

    public LastCollectedList(ObjectMapper objectMapper,
                             @Value("${last-collected-set.json-file}") String filePath,
                             @Value("${last-collected-set.limit}") int limit) throws IOException {
        this.objectMapper = objectMapper;
        this.jsonFile = new File(filePath);
        log.info("Load json file : {}", jsonFile);
        this.namuWikiChanges = objectMapper.readValue(jsonFile,
                objectMapper.getTypeFactory().constructParametricType(List.class, NamuWikiChange.class));
        if (limit <= 0) {
            throw new IllegalArgumentException("limit must greater than 0");
        }
        this.limit = limit;
    }

    @PreDestroy
    public void destroy() throws IOException {
        objectMapper.writeValue(jsonFile, this.namuWikiChanges);
    }

    public boolean contains(NamuWikiChange namuWikiChange) {
        return namuWikiChanges.contains(namuWikiChange);
    }

    public void add(NamuWikiChange namuWikiChange) {
        this.namuWikiChanges.add(namuWikiChange);
        if (namuWikiChanges.size() > limit) {
            namuWikiChanges.remove(0);
        }
    }
}
