package com.hunsley.account.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Account implements Serializable {
    private static final long serialVersionUID = 42L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long accountId;

    protected Integer uid;

    protected Double value;

    protected Double interestRate;

    public Account() {}

    public Account(final Double value, final Double interestRate) {
        this.value = value;
        this.interestRate = interestRate;
    }

    public Long getAccountId() {
        return accountId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return Objects.equals(accountId, account.accountId) &&
                Objects.equals(uid, account.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, uid);
    }
}
