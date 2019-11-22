package com.msmyrnov.gameofthree.service;

import com.msmyrnov.gameofthree.repository.GameRepository;
import com.msmyrnov.gameofthree.repository.entity.AccountEntity;
import com.msmyrnov.gameofthree.repository.entity.GameEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class GameServiceIntegrationTest {

    private static final String ACCOUNT_NAME = "TestAccountName";

    @TestConfiguration
    static class GameServiceTestContextConfiguration {

        @Bean
        public GameService gameService() {
            return new GameService();
        }
    }

    @Autowired
    private GameService gameService;

    @MockBean
    private GameRepository gameRepository;

    @Before
    public void setUp() {
        GameEntity gameEntity = new GameEntity();
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setName(ACCOUNT_NAME);
        gameEntity.setAccount(accountEntity);

        Mockito.when(gameRepository.findByAccountNameAndWinnerIsNull(ACCOUNT_NAME)).thenReturn(gameEntity);
    }

    @Test
    public void whenFindAccountByName_thenReturnAccount() {
        GameEntity found = gameService.findActiveGameByAccountName(ACCOUNT_NAME);
        assertTrue(ACCOUNT_NAME.equals(found.getAccount().getName()));
    }
}
