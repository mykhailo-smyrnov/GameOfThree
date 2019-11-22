package com.msmyrnov.gameofthree.repository;

import com.msmyrnov.gameofthree.repository.entity.GameStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameStatusRepository extends JpaRepository<GameStatusEntity, Long> {

    GameStatusEntity findByName(String name);
}
