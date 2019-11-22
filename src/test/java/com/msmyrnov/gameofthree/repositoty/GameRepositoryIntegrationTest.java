package com.msmyrnov.gameofthree.repositoty;

import com.msmyrnov.gameofthree.repository.AccountRepository;
import com.msmyrnov.gameofthree.repository.GameRepository;
import com.msmyrnov.gameofthree.repository.GameStatusRepository;
import com.msmyrnov.gameofthree.repository.PlayerTypeRepository;
import com.msmyrnov.gameofthree.repository.entity.AccountEntity;
import com.msmyrnov.gameofthree.repository.entity.GameEntity;
import com.msmyrnov.gameofthree.repository.entity.GameStatusEntity;
import com.msmyrnov.gameofthree.repository.entity.PlayerTypeEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GameRepositoryIntegrationTest {

    private static final String ACCOUNT_NAME = "TestAccountName";

    private static final LocalDateTime CREATED_DATE = LocalDateTime.now();

    private static final String GAME_STATUS_STARTED = "STARTED";

    private static final String PLAYER_TYPE_HUMAN = "HUMAN";

    private static final int INIT_NUMBER = 2846;

    private static final int CURRENT_NUMBER = 384;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameStatusRepository gameStatusRepository;

    @Autowired
    private PlayerTypeRepository playerTypeRepository;

    @Test
    public void whenFindActiveGameByAccountName_thenReturnGame() {
        // given
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setName(ACCOUNT_NAME);
        accountEntity.setCreatedDate(CREATED_DATE);
        entityManager.persist(accountEntity);
        entityManager.flush();
        accountEntity = accountRepository.findAccountByName(ACCOUNT_NAME);

        GameStatusEntity gameStatusEntity = gameStatusRepository.findByName(GAME_STATUS_STARTED);
        PlayerTypeEntity playerTypeEntity = playerTypeRepository.findByName(PLAYER_TYPE_HUMAN);

        GameEntity gameEntity = new GameEntity();
        gameEntity.setAccount(accountEntity);
        gameEntity.setStartedBy(playerTypeEntity);
        gameEntity.setStatus(gameStatusEntity);
        gameEntity.setInitNumber(INIT_NUMBER);
        gameEntity.setCurrentNumber(CURRENT_NUMBER);
        gameEntity.setStartDate(CREATED_DATE);
        entityManager.persist(gameEntity);
        entityManager.flush();

        // when
        GameEntity found = gameRepository.findByAccountNameAndWinnerIsNull(ACCOUNT_NAME);

        // then
        assertTrue(ACCOUNT_NAME.equals(found.getAccount().getName()));
        assertTrue(PLAYER_TYPE_HUMAN.equals(found.getStartedBy().getName()));
        assertTrue(GAME_STATUS_STARTED.equals(found.getStatus().getName()));
        assertTrue(INIT_NUMBER == found.getInitNumber());
        assertTrue(CURRENT_NUMBER == found.getCurrentNumber());
        assertTrue(CREATED_DATE.isEqual(found.getStartDate()));
        assertNull(found.getWinner());
        assertNull(found.getFinishDate());
    }

}
