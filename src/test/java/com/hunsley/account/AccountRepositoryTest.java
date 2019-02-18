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
        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setUid(10);
        currentAccount.setValue(103.34);
        currentAccount.setOverdraftLimit(400.00);
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
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setUid(10);
        savingsAccount.setValue(103.34);
        savingsAccount.setMaxDeposit(20000.00);
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
