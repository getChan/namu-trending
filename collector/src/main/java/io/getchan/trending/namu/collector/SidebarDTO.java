package io.getchan.trending.namu.collector;

import io.getchan.trending.namu.domain.collector.Sidebar;
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

    public Sidebar toSidebar() {
        return Sidebar.builder()
                .documentTitle(document)
                .updatedStatus(status)
                .updatedAt(LocalDateTime.ofEpochSecond(date, 0, ZoneOffset.ofHours(9)))
                .build();
    }
}
