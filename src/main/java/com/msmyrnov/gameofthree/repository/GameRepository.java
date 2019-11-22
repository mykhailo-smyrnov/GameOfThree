package com.msmyrnov.gameofthree.repository;

import com.msmyrnov.gameofthree.repository.entity.AccountEntity;
import com.msmyrnov.gameofthree.repository.entity.GameEntity;
import com.msmyrnov.gameofthree.repository.entity.GameStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {

    GameEntity findByAccountNameAndWinnerIsNull(String accountName);

    List<GameEntity> findAllByWinnerIsNotNull();

}
