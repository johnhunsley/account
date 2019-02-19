package com.hunsley.account.model;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class CurrentAccount extends Account implements Serializable {
    private static final long serialVersionUID = 42L;

    private Double overdraftLimit;

    public CurrentAccount() {}

    public CurrentAccount(final Double value, final Double interestRate, final Double overdraftLimit) {
        super(value, interestRate);
        this.overdraftLimit = overdraftLimit;
    }

    public Double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(Double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }
}
