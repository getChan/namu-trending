package io.getchan.trending.namu.domain.collector;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class SidebarDTO {
    private String document;
    private String status;
    private long date;
}
