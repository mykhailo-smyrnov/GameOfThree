package com.msmyrnov.gameofthree.controller;

import com.msmyrnov.gameofthree.repository.entity.GameEntity;
import com.msmyrnov.gameofthree.service.GameService;
import com.msmyrnov.gameofthree.service.GameStatsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StatsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);

    @Autowired
    private GameService gameService;

    @Autowired
    private GameStatsService gameStatsService;

    @GetMapping(value = "/stats/all")
    public String stats(Model model) {
        List<GameEntity> gameEntities = gameService.findAllFinished();
        model.addAttribute("statsType", "all");
        model.addAttribute("games", gameEntities);
        return "stats";
    }
}
