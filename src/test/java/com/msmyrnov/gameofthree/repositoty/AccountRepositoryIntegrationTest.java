package com.msmyrnov.gameofthree.repositoty;

import com.msmyrnov.gameofthree.repository.AccountRepository;
import com.msmyrnov.gameofthree.repository.entity.AccountEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountRepositoryIntegrationTest {

    private static final String ACCOUNT_NAME = "TestAccountName";

    private static final LocalDateTime CREATED_DATE = LocalDateTime.now();

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void whenFindAccountByName_thenReturnAccount() {
        // given
        AccountEntity account = new AccountEntity();
        account.setName(ACCOUNT_NAME);
        account.setCreatedDate(CREATED_DATE);
        entityManager.persist(account);
        entityManager.flush();

        // when
        AccountEntity found = accountRepository.findAccountByName(ACCOUNT_NAME);

        // then
        assertTrue(ACCOUNT_NAME.equals(found.getName()));
        assertTrue(CREATED_DATE.isEqual(found.getCreatedDate()));
    }

    @Test
    public void whenCheckeIsAccountExistByName_thenReturnBoolean() {
        // given
        AccountEntity account = new AccountEntity();
        account.setName(ACCOUNT_NAME);
        account.setCreatedDate(CREATED_DATE);
        entityManager.persist(account);
        entityManager.flush();

        // when
        boolean isFound = accountRepository.existsAccountByName(ACCOUNT_NAME);

        // then
        assertTrue(isFound);
    }

}
