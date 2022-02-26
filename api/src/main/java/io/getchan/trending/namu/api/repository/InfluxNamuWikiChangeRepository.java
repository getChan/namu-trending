package io.getchan.trending.namu.api.repository;

import com.influxdb.client.QueryApi;
import com.influxdb.query.FluxTable;
import io.getchan.trending.namu.domain.ChangeCount;
import io.getchan.trending.namu.domain.repository.NamuWikiChangeEntity;
import io.getchan.trending.namu.domain.repository.NamuWikiChangeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Repository
public class InfluxNamuWikiChangeRepository implements NamuWikiChangeRepository {

    private final QueryApi queryApi;

    public InfluxNamuWikiChangeRepository(QueryApi queryApi) {
        this.queryApi = queryApi;
    }

    public void save(NamuWikiChangeEntity entity) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public Stream<ChangeCount> countByTitle(Instant start, Instant stop) {
        final String query = """
                from(bucket: "NamuWikiChange")
                  |> range(start: %s, stop: %s)
                  |> filter(fn: (r) => r["_measurement"] == "namuWikiChange")
                  |> group(columns: ["documentTitle"])
                  |> count()
                  """.formatted(start.toString(), stop.toString());

        final List<FluxTable> tables = queryApi.query(query);
        log.info("query result row count : {}", tables.size());

        return tables.stream()
                .flatMap(table -> table.getRecords().stream())
                .map(r -> ChangeCount.builder()
                        .documentTitle((String) r.getValueByKey("documentTitle"))
                        .count((Long) r.getValueByKey("_value"))
                        .build());
    }

}
