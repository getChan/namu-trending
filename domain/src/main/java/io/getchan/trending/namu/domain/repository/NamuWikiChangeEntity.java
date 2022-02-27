package io.getchan.trending.namu.domain.repository;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import io.getchan.trending.namu.domain.NamuWikiChange;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * InfluxDB identifies unique data points by their measurement, tag set, and timestamp (each a part of Line protocol used to write data to InfluxDB).
 */
@NoArgsConstructor
@Getter
@Measurement(name = "namuWikiChange")
public class NamuWikiChangeEntity {
    @Column(tag = true)
    private String documentTitle;
    @Column
    private String changedStatus;
    @Column(timestamp = true)
    private Instant changedTime;

    private NamuWikiChangeEntity(String documentTitle, String changedStatus, Instant changedTime) {
        this.documentTitle = documentTitle;
        this.changedStatus = changedStatus;
        this.changedTime = changedTime;
    }

    public static NamuWikiChangeEntity from(NamuWikiChange namuWikiChange) {
        return new NamuWikiChangeEntity(
                namuWikiChange.getDocumentTitle(),
                namuWikiChange.getChangedStatus(),
                namuWikiChange.getChangedTime());
    }

    public NamuWikiChange toDomain() {
        return NamuWikiChange.builder()
                .documentTitle(documentTitle)
                .changedStatus(changedStatus)
                .changedTime(changedTime)
                .build();
    }

}
