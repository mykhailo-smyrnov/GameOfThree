package com.msmyrnov.gameofthree.repository;

import com.msmyrnov.gameofthree.repository.entity.GameEntity;
import com.msmyrnov.gameofthree.repository.entity.GameStatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameStatsRepository extends JpaRepository<GameStatsEntity, Long> {

    List<GameStatsEntity> findByGame(GameEntity game);

}
