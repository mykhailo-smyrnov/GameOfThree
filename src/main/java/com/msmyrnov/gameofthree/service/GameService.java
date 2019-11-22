package com.msmyrnov.gameofthree.service;

import com.msmyrnov.gameofthree.repository.GameRepository;
import com.msmyrnov.gameofthree.repository.entity.AccountEntity;
import com.msmyrnov.gameofthree.repository.entity.GameEntity;
import com.msmyrnov.gameofthree.repository.entity.GameStatusEntity;
import com.msmyrnov.gameofthree.repository.entity.PlayerTypeEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GameService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameService.class);

    @Autowired
    private GameRepository gameRepository;

    public GameEntity create(AccountEntity accountEntity, PlayerTypeEntity startedByEntity,
                             GameStatusEntity gameStatusEntity, int initNumber) {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setAccount(accountEntity);
        gameEntity.setStartedBy(startedByEntity);
        gameEntity.setStatus(gameStatusEntity);
        gameEntity.setInitNumber(initNumber);
        gameEntity.setCurrentNumber(initNumber);
        gameEntity.setStartDate(LocalDateTime.now());
        gameEntity = gameRepository.saveAndFlush(gameEntity);
        return gameEntity;
    }

    public List<GameEntity> findAllFinished() {
        return gameRepository.findAllByWinnerIsNotNull();
    }

    public GameEntity findActiveGameByAccountName(String accountName) {
        return gameRepository.findByAccountNameAndWinnerIsNull(accountName);
    }

    public void update(GameEntity gameEntity) {
        gameRepository.saveAndFlush(gameEntity);
    }

}
