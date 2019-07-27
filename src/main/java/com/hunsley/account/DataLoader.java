package com.hunsley.account;

import com.hunsley.account.model.CurrentAccount;
import com.hunsley.account.model.LimitedAccount;
import com.hunsley.account.model.SavingsAccount;
import com.hunsley.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * <p>
 *     This class is used to load some example data into the underlying HSQLDB instance when running as a service
 *
 *     This will only be used when setting the 'service' active profile
 * </p>
 * @author jhunsley
 */
@Profile({"service & prepopulated"})
@Component
public class DataLoader {

    @Autowired
    private AccountRepository accountRepository;

    @PostConstruct
    public void init() {
        CurrentAccount currentAccount = new CurrentAccount(10, "MyCurrentAccount", 103.34, 1.50, 400.00);
        accountRepository.save(currentAccount);
        SavingsAccount savingsAccount = new SavingsAccount(10, "MySavingsAccount",103.34, 2.5, 200000.00);
        accountRepository.save(savingsAccount);
        SavingsAccount savingsAccount2 = new SavingsAccount(10, "MyOtherSavingsAccount",1000.00, 2.5, 300000.00);
        accountRepository.save(savingsAccount2);
        LimitedAccount limitedAccount = new LimitedAccount(10, "MyLimitedAccount",2567.23, 2.5, 400000.00);
        accountRepository.save(limitedAccount);
    }
}
