package com.hunsley.account.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CurrentAccount.class, name = "CurrentAccount"),
        @JsonSubTypes.Type(value = SavingsAccount.class, name = "SavingsAccount"),
        @JsonSubTypes.Type(value = LimitedAccount.class, name = "LimitedAccount")
})
public abstract class Account implements Serializable {
    private static final long serialVersionUID = 42L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;

    private Integer uid;

    private String name;

    private double value;

    private Double interestRate;

    public Account() {}

    public Account(final Integer uid, final String name, final Double value, final Double interestRate) {
        this.uid = uid;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
