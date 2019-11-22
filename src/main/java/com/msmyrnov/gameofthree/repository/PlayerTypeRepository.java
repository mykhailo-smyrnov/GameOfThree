package com.msmyrnov.gameofthree.repository;

import com.msmyrnov.gameofthree.repository.entity.PlayerTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerTypeRepository extends JpaRepository<PlayerTypeEntity, Long> {

    PlayerTypeEntity findByName(String name);
}
