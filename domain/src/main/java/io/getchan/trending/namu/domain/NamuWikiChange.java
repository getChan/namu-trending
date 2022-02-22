package io.getchan.trending.namu.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@Getter
@ToString
public class NamuWikiChange {
    private final String documentTitle;
    private final String changedStatus;
    private final LocalDateTime changedTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NamuWikiChange namuWikiChange = (NamuWikiChange) o;

        if (!documentTitle.equals(namuWikiChange.documentTitle)) return false;
        if (!changedStatus.equals(namuWikiChange.changedStatus)) return false;
        return changedTime.equals(namuWikiChange.changedTime);
    }

    @Override
    public int hashCode() {
        int result = documentTitle.hashCode();
        result = 31 * result + changedStatus.hashCode();
        result = 31 * result + changedTime.hashCode();
        return result;
    }
}
