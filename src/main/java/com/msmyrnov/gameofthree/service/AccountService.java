package com.msmyrnov.gameofthree.service;

import com.msmyrnov.gameofthree.repository.AccountRepository;
import com.msmyrnov.gameofthree.repository.entity.AccountEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountRepository accountRepository;

    public AccountEntity create(String name) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setName(name);
        accountEntity.setCreatedDate(LocalDateTime.now());
        return accountRepository.saveAndFlush(accountEntity);
    }

    public AccountEntity findByName(String name) {
        return accountRepository.findAccountByName(name);
    }

    public boolean isExistByName(String name) {
        return accountRepository.existsAccountByName(name);
    }
}
