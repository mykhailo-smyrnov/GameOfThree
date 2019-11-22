package com.msmyrnov.gameofthree.service;

import com.msmyrnov.gameofthree.model.Game;
import com.msmyrnov.gameofthree.repository.entity.AccountEntity;
import com.msmyrnov.gameofthree.repository.entity.GameEntity;
import com.msmyrnov.gameofthree.repository.entity.GameStatusEntity;
import com.msmyrnov.gameofthree.repository.entity.PlayerTypeEntity;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ManagePlayService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ManagePlayService.class);

    private static final String MOVE_BY_HUMAN = "HUMAN";

    private static final String MOVE_BY_COMPUTER = "COMPUTER";

    private static final String GAME_STATUS_STARTED = "STARTED";

    private static final String GAME_STATUS_FINISHED = "FINISHED";

    private static final int RANDOM_MIN = -32768;

    private static final int RANDOM_MAX = 32767;

    private static final int MOVE_MIN = -1;

    private static final int MOVE_MAX = 1;

    private static final int DIVISIBLE_BY = 3;

    @Autowired
    private AccountService accountService;

    @Autowired
    private GameService gameService;

    @Autowired
    private GameStatusService gameStatusService;

    @Autowired
    private GameStatsService gameStatsService;

    @Autowired
    private PlayerTypeService playerTypeService;

    public Game getGameInProgress(String userName) {
        Game game = null;
        GameEntity gameEntity = gameService.findActiveGameByAccountName(userName);
        if (gameEntity != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Current number is : ").append(gameEntity.getCurrentNumber());
            game = new Game();
            game.setInfoMessage(sb.toString());
            game.setStarted(true);
        }
        return game;
    }

    //    @Transactional
    public Game play(String userName, String moveByName, Integer moveNumber) {
        // check is there already started game
        GameEntity gameEntity = gameService.findActiveGameByAccountName(userName);

        // init numbers
        int currentNumber;
        if (gameEntity == null) {
            AccountEntity account = accountService.findByName(userName);
            PlayerTypeEntity playerType = playerTypeService.findByName(moveByName);
            GameStatusEntity gameStatusStarted = gameStatusService.findByName(GAME_STATUS_STARTED);
            currentNumber = generateRandomIntegerInRange(RANDOM_MIN, RANDOM_MAX);
            gameEntity = gameService.create(account, playerType, gameStatusStarted, currentNumber);
            if (MOVE_BY_HUMAN.equalsIgnoreCase(moveByName))
                moveNumber = generateRandomIntegerInRange(MOVE_MIN, MOVE_MAX);
        } else {
            currentNumber = gameEntity.getCurrentNumber();
        }

        boolean isSuccess = false;
        if (MOVE_BY_HUMAN.equalsIgnoreCase(moveByName)) {
            PlayerTypeEntity humanPlayerType = playerTypeService.findByName(MOVE_BY_HUMAN);
            gameStatsService.create(gameEntity, humanPlayerType, moveNumber);
            currentNumber = currentNumber + moveNumber;
            isSuccess = isDivisibleBy(currentNumber);
            if (isSuccess)
                gameEntity.setWinner(humanPlayerType);
        }
        if (!isSuccess) {
            PlayerTypeEntity computerPlayerType = playerTypeService.findByName(MOVE_BY_COMPUTER);
            moveNumber = generateRandomIntegerInRange(MOVE_MIN, MOVE_MAX);
            gameStatsService.create(gameEntity, computerPlayerType, moveNumber);
            currentNumber = currentNumber + moveNumber;
            isSuccess = isDivisibleBy(currentNumber);
            if (isSuccess)
                gameEntity.setWinner(computerPlayerType);
        }

        // update model and db entity
        Game game = new Game();
        game.setStarted(true);
        gameEntity.setCurrentNumber(currentNumber);
        if (isSuccess) {
            GameStatusEntity gameStatusFinished = gameStatusService.findByName(GAME_STATUS_FINISHED);
            gameEntity.setStatus(gameStatusFinished);
            gameEntity.setFinishDate(LocalDateTime.now());
            game.setFinished(true);
        }
        gameService.update(gameEntity);

        return game;
    }

    private int generateRandomIntegerInRange(int min, int max) {
        RandomDataGenerator generator = new RandomDataGenerator();
        return generator.nextInt(min, max);
    }

    private boolean isDivisibleBy(int number) {
        String numberAsString = String.valueOf(number);
        int digitSum = 0;
        for (int i = 0; i < numberAsString.length(); i++) {
            digitSum = digitSum + numberAsString.charAt(i) - '0';
        }
        return (digitSum % DIVISIBLE_BY == 0);
    }
}
