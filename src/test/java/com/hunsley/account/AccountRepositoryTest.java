package com.hunsley.account;

import com.hunsley.account.model.Account;
import com.hunsley.account.model.CurrentAccount;
import com.hunsley.account.model.SavingsAccount;
import com.hunsley.account.repository.AccountRepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("service")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    final int uid = 10;


    @Test
    public void A_simpleWriteRead_CurrentAccount() {
        CurrentAccount currentAccount = new CurrentAccount(10, "MyCurrentAccount", 103.34, 1.50, 400.00);
        currentAccount.setUid(uid);
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
    public void B_simpleWriteRead_SavingsAccount() {
        SavingsAccount savingsAccount = new SavingsAccount(10, "MySavingsAccount",103.34, 2.5, 200000.00);
        savingsAccount.setUid(uid);
        accountRepository.save(savingsAccount);
        assertTrue(savingsAccount.getAccountId() > 0);
        Long id = savingsAccount.getAccountId();

        Optional<Account> value = accountRepository.findById(id);
        assertTrue(value.isPresent());
        Account read = value.get();
        assertTrue(read instanceof SavingsAccount);
        assertSame(read.getAccountId(), id);

    }

    /**
     * add another Account sub type instance which is not owned by the same uid
     */
    @Test
    public void C_testFindByUid() {
        SavingsAccount savingsAccount = new SavingsAccount(10, "MyBigSavingsAccount", 103.34, 2.5, 200000.00);
        savingsAccount.setUid(101);
        accountRepository.save(savingsAccount);

        List<Account> accounts = accountRepository.findByUid(uid);
        assertEquals(2, accounts.size());
    }

    @Test
    public void D_testPageByName() {
        Page<Account> page = accountRepository.findByName("My", PageRequest.of(0, 10));
        assertFalse(page.isEmpty());
    }
}
