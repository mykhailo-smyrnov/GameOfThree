package com.msmyrnov.gameofthree.repositoty;

import com.msmyrnov.gameofthree.repository.GameStatusRepository;
import com.msmyrnov.gameofthree.repository.entity.GameStatusEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GameStatusRepositoryIntegrationTest {

    private static final String GAME_STATUS_STARTED = "STARTED";

    private static final String GAME_STATUS_FINISHED = "FINISHED";

    @Autowired
    private GameStatusRepository gameStatusRepository;

    @Test
    public void whenFindGameStatusStarted_thenReturnGameStatus() {
        // when
        GameStatusEntity found = gameStatusRepository.findByName(GAME_STATUS_STARTED);

        // then
        assertTrue(GAME_STATUS_STARTED.equals(found.getName()));
    }

    @Test
    public void whenFindGameStatusFinished_thenReturnGameStatus() {
        // when
        GameStatusEntity found = gameStatusRepository.findByName(GAME_STATUS_FINISHED);

        // then
        assertTrue(GAME_STATUS_FINISHED.equals(found.getName()));
    }

    @Test
    public void whenFindFindGameStatus_thenReturnList() {
        // when
        List<GameStatusEntity> found = gameStatusRepository.findAll();

        // then
        assertTrue(found.size() == 2);
    }

}
