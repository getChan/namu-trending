package io.getchan.trending.namu.collector;

import io.getchan.trending.namu.domain.NamuWikiChange;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

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
                .changedTime(LocalDateTime.ofEpochSecond(date, 0, ZoneOffset.ofHours(9)))
                .build();
    }
}
