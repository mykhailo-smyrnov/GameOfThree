package com.msmyrnov.gameofthree.service;

import com.msmyrnov.gameofthree.repository.GameStatusRepository;
import com.msmyrnov.gameofthree.repository.entity.GameStatusEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameStatusService {

    @Autowired
    private GameStatusRepository gameStatusRepository;

    public GameStatusEntity findByName(String name) {
        return gameStatusRepository.findByName(name);
    }
}
