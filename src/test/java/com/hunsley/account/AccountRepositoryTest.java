package com.hunsley.account;

import com.hunsley.account.model.Account;
import com.hunsley.account.model.CurrentAccount;
import com.hunsley.account.model.SavingsAccount;
import com.hunsley.account.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("service")
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;


    @Test
    public void simpleWriteRead_CurrentAccount() {
        CurrentAccount currentAccount = new CurrentAccount(103.34, 1.50, 400.00);
        currentAccount.setUid(10);
        accountRepository.save(currentAccount);
        assertTrue(currentAccount.getAccountId() > 0);
        Long id = currentAccount.getAccountId();

        Optional<Account> value = accountRepository.findById(id);
        assertTrue(value.isPresent());
        Account read = value.get();
        assertTrue(read instanceof CurrentAccount);
        assertSame(read.getAccountId(), id);

    }

    @Test
    public void simpleWriteRead_SavingsAccount() {
        SavingsAccount savingsAccount = new SavingsAccount(103.34, 2.5, 200000.00);
        savingsAccount.setUid(10);
        accountRepository.save(savingsAccount);
        assertTrue(savingsAccount.getAccountId() > 0);
        Long id = savingsAccount.getAccountId();

        Optional<Account> value = accountRepository.findById(id);
        assertTrue(value.isPresent());
        Account read = value.get();
        assertTrue(read instanceof SavingsAccount);
        assertSame(read.getAccountId(), id);

    }
}
