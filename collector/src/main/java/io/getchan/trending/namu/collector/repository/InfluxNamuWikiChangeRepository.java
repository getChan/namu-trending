package io.getchan.trending.namu.collector.repository;

import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import org.springframework.stereotype.Repository;

@Repository
public class InfluxNamuWikiChangeRepository implements NamuWikiChangeRepository {

    private final WriteApi writeApi;

    public InfluxNamuWikiChangeRepository(WriteApi writeApi) {
        this.writeApi = writeApi;
    }

    public void save(NamuWikiChangeEntity entity) {
        writeApi.writeMeasurement(WritePrecision.S, entity);
    }

}
