package io.getchan.trending.namu.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class ChangeCount {
    private final String documentTitle;
    private final Long count;
}
