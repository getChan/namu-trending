package io.getchan.trending.namu.collector;

import io.getchan.trending.namu.domain.NamuWikiChange;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SidebarDTOTest {

    @Test
    void toSidebar() {
        final SidebarDTO sidebarDTO = new SidebarDTO();
        sidebarDTO.setDate(1645538946);
        sidebarDTO.setStatus("normal");
        sidebarDTO.setDocument("전기면도기");

        final NamuWikiChange namuWikiChange = sidebarDTO.toNamuWikiChange();
        assertThat(namuWikiChange.getChangedTime()).hasToString("2022-02-22T14:09:06Z");
    }
}