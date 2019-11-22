package com.msmyrnov.gameofthree.service;

import com.msmyrnov.gameofthree.repository.GameStatsRepository;
import com.msmyrnov.gameofthree.repository.entity.GameEntity;
import com.msmyrnov.gameofthree.repository.entity.GameStatsEntity;
import com.msmyrnov.gameofthree.repository.entity.PlayerTypeEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GameStatsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameStatsService.class);

    @Autowired
    private GameStatsRepository gameStatsRepository;

    public GameStatsEntity create(GameEntity game, PlayerTypeEntity moveBy, Integer moveNumber) {
        GameStatsEntity gameStatsEntity = new GameStatsEntity();
        gameStatsEntity.setGame(game);
        gameStatsEntity.setMoveBy(moveBy);
        gameStatsEntity.setMoveNumber(moveNumber);
        gameStatsEntity.setMoveDate(LocalDateTime.now());
        gameStatsEntity = gameStatsRepository.saveAndFlush(gameStatsEntity);
        return gameStatsEntity;
    }

    public List<GameStatsEntity> findAll() {
        return gameStatsRepository.findAll();
    }
}
