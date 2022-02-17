package io.getchan.trending.namu.domain.collector;


import lombok.Getter;

/**
 * https://namu.wiki/sidebar.json
 */
@Getter
public class SidebarDTO {
    private final String document;
    private final String status;
    private final long date;

    public SidebarDTO(String document, String status, long date) {
        this.document = document;
        this.status = status;
        this.date = date;
    }
}

