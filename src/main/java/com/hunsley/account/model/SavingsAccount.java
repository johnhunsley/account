package com.hunsley.account.model;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class SavingsAccount extends Account implements Serializable {
    private static final long serialVersionUID = 42L;

    private Double maxDeposit;

    public Double getMaxDeposit() {
        return maxDeposit;
    }

    public void setMaxDeposit(Double maxDeposit) {
        this.maxDeposit = maxDeposit;
    }
}
