package com.hunsley.account.model;

import javax.persistence.Entity;

@Entity
public class LimitedAccount extends Account {
    private static final long serialVersionUID = 42L;

    private Double accountLimit;

    public LimitedAccount() {}

    public LimitedAccount(Integer uid, Double value, Double interestRate, Double accountLimit) {
        super(uid, value, interestRate);
        this.accountLimit = accountLimit;
    }

    public Double getAccountLimit() {
        return accountLimit;
    }

    public void setAccountLimit(Double accountLimit) {
        this.accountLimit = accountLimit;
    }
}
