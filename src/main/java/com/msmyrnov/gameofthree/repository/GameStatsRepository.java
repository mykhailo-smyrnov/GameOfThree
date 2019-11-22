package com.msmyrnov.gameofthree.repository;

import com.msmyrnov.gameofthree.repository.entity.GameStatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameStatsRepository extends JpaRepository<GameStatsEntity, Long> {
}
