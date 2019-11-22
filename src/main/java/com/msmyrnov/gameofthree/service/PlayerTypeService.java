package com.msmyrnov.gameofthree.service;

import com.msmyrnov.gameofthree.repository.PlayerTypeRepository;
import com.msmyrnov.gameofthree.repository.entity.PlayerTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerTypeService {

    @Autowired
    private PlayerTypeRepository playerTypeRepository;

    public PlayerTypeEntity findByName(String name) {
        return playerTypeRepository.findByName(name);
    }
}
