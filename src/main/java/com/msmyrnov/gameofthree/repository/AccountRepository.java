package com.msmyrnov.gameofthree.repository;

import com.msmyrnov.gameofthree.repository.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    boolean existsAccountByName(String name);

    AccountEntity findAccountByName(String name);
}
