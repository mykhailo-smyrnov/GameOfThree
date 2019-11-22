package com.msmyrnov.gameofthree.service;

import com.msmyrnov.gameofthree.repository.AccountRepository;
import com.msmyrnov.gameofthree.repository.entity.AccountEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class AccountServiceIntegrationTest {

    private static final String ACCOUNT_NAME = "TestAccountName";

    private static final LocalDateTime CREATED_DATE = LocalDateTime.now();

    @TestConfiguration
    static class AccountServiceTestContextConfiguration {

        @Bean
        public AccountService accountService() {
            return new AccountService();
        }
    }

    @Autowired
    private AccountService accountService;

    @MockBean
    private AccountRepository accountRepository;

    @Before
    public void setUp() {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setName(ACCOUNT_NAME);
        accountEntity.setCreatedDate(CREATED_DATE);

        Mockito.when(accountRepository.saveAndFlush(accountEntity)).thenReturn(accountEntity);
        Mockito.when(accountRepository.findAccountByName(ACCOUNT_NAME)).thenReturn(accountEntity);
        Mockito.when(accountRepository.existsAccountByName(ACCOUNT_NAME)).thenReturn(true);
    }

    @Test
    public void whenFindAccountByName_thenReturnAccount() {
        AccountEntity found = accountService.findByName(ACCOUNT_NAME);
        assertTrue(ACCOUNT_NAME.equals(found.getName()));
    }

    @Test
    public void whenCheckeIsAccountExistByName_thenReturnBoolean() {
        boolean found = accountService.isExistByName(ACCOUNT_NAME);
        assertTrue(found);
    }

}
