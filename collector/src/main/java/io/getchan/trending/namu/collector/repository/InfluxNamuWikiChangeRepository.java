package io.getchan.trending.namu.collector.repository;

import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import io.getchan.trending.namu.domain.ChangeCount;
import io.getchan.trending.namu.domain.repository.NamuWikiChangeEntity;
import io.getchan.trending.namu.domain.repository.NamuWikiChangeRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.stream.Stream;

@Repository
public class InfluxNamuWikiChangeRepository implements NamuWikiChangeRepository {

    private final WriteApi writeApi;

    public InfluxNamuWikiChangeRepository(WriteApi writeApi) {
        this.writeApi = writeApi;
    }

    public void save(NamuWikiChangeEntity entity) {
        writeApi.writeMeasurement(WritePrecision.S, entity);
    }

    @Override
    public Stream<ChangeCount> countByTitle(Instant start, Instant stop) {
        throw new UnsupportedOperationException("not implemented");
    }

}
