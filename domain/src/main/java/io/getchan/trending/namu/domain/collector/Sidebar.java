package io.getchan.trending.namu.domain.collector;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@Getter
@ToString
public class Sidebar {
    private final String documentTitle;
    private final String updatedStatus;
    private final LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sidebar sidebar = (Sidebar) o;

        if (!documentTitle.equals(sidebar.documentTitle)) return false;
        if (!updatedStatus.equals(sidebar.updatedStatus)) return false;
        return updatedAt.equals(sidebar.updatedAt);
    }

    @Override
    public int hashCode() {
        int result = documentTitle.hashCode();
        result = 31 * result + updatedStatus.hashCode();
        result = 31 * result + updatedAt.hashCode();
        return result;
    }
}
