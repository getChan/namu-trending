package io.getchan.trending.namu.api.controller;

import io.getchan.trending.namu.api.service.RankService;
import io.getchan.trending.namu.domain.ChangeCount;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/rank")
public class RankController {
    private final RankService rankService;

    public RankController(RankService rankService) {
        this.rankService = rankService;
    }

    @GetMapping("/day")
    public List<ChangeCount> dayRank() {
        return rankService.dayRank(20);
    }

    @GetMapping("/week")
    public List<ChangeCount> weekRank() {
        return rankService.weekRank(20);
    }
}
