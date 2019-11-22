package com.msmyrnov.gameofthree.repositoty;

import com.msmyrnov.gameofthree.repository.PlayerTypeRepository;
import com.msmyrnov.gameofthree.repository.entity.PlayerTypeEntity;
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
public class PlayerTypeRepositoryIntegrationTest {

    private static final String PLAYER_TYPE_HUMAN = "HUMAN";

    private static final String PLAYER_TYPE_COMPUTER = "COMPUTER";

    @Autowired
    private PlayerTypeRepository playerTypeRepository;

    @Test
    public void whenFindHuman_thenReturnPlayerType() {
        // when
        PlayerTypeEntity found = playerTypeRepository.findByName(PLAYER_TYPE_HUMAN);

        // then
        assertTrue(PLAYER_TYPE_HUMAN.equals(found.getName()));
    }

    @Test
    public void whenFindComputer_thenReturnPlayerType() {
        // when
        PlayerTypeEntity found = playerTypeRepository.findByName(PLAYER_TYPE_COMPUTER);

        // then
        assertTrue(PLAYER_TYPE_COMPUTER.equals(found.getName()));
    }

    @Test
    public void whenFindPlayerType_thenReturnList() {
        // when
        List<PlayerTypeEntity> found = playerTypeRepository.findAll();

        // then
        assertTrue(found.size() == 2);
    }
}
