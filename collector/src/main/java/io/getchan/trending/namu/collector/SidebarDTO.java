package io.getchan.trending.namu.collector;

import io.getchan.trending.namu.domain.NamuWikiChange;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@ToString
@Getter
@Setter
public class SidebarDTO {
    @NotEmpty
    private String document;
    @NotEmpty
    private String status;
    @NotEmpty
    private long date;

    public NamuWikiChange toNamuWikiChange() {
        return NamuWikiChange.builder()
                .documentTitle(document)
                .changedStatus(status)
                .changedTime(Instant.ofEpochSecond(date))
                .build();
    }
}
