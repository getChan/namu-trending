package io.getchan.trending.namu.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class NamuWikiChangeMessage {
    private Instant changedTime;
    private String documentTitle;
    private String changedStatus;

    public static NamuWikiChangeMessage from(NamuWikiChange namuWikiChange) {
        final NamuWikiChangeMessage msg = new NamuWikiChangeMessage();
        msg.setChangedStatus(namuWikiChange.getChangedStatus());
        msg.setChangedTime(namuWikiChange.getChangedTime());
        msg.setDocumentTitle(namuWikiChange.getDocumentTitle());

        return msg;
    }
}
