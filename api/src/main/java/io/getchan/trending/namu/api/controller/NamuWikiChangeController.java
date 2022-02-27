package io.getchan.trending.namu.api.controller;

import io.getchan.trending.namu.api.service.NamuWikiChangeService;
import io.getchan.trending.namu.domain.NamuWikiChange;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping(path = "/change")
public class NamuWikiChangeController {
    private final NamuWikiChangeService changeService;

    public NamuWikiChangeController(NamuWikiChangeService changeService) {
        this.changeService = changeService;
    }

    @GetMapping(path = "/{title}")
    public List<NamuWikiChange> allChanges(@PathVariable(value = "title") @NotEmpty String title) {
        // TODO : url에 공백 or 일부 특수문자 포함될 수 없음. URL encoding?
        return changeService.changesOf(title, Instant.EPOCH, Instant.now());
    }
}
