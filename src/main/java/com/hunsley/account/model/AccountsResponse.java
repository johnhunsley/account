package com.hunsley.account.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A representation of the JSON response from the Account REST Repository
 * {@link com.hunsley.account.repository.AccountRepository}
 *
 * Both classes are simple wrapper objects around an array of {@link com.hunsley.account.model.Account} types.
 *
 * {
 *     "_embedded": {
 *         "accounts": []
 *     }
 * }
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountsResponse<T> implements Serializable {
    @JsonProperty("_embedded")
    private Embedded<T> embedded;

    public Embedded<T> getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Embedded embedded) {
        this.embedded = embedded;
    }

    public List<T> getEmbeddedCurrentAccounts() {
        if(embedded == null) embedded = new Embedded<>();
        return Collections.unmodifiableList(embedded.currentAccounts);
    }

    public List<T> getEmbeddedSavingsAccounts() {
        if(embedded == null) embedded = new Embedded<>();
        return Collections.unmodifiableList(embedded.savingsAccounts);
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Embedded<T> implements Serializable {
    @JsonProperty("currentAccounts")
    List<T> currentAccounts = new ArrayList<>();

    @JsonProperty("savingsAccounts")
    List<T> savingsAccounts = new ArrayList<>();

    public Embedded() {}

    public List<T> getCurrentAccounts() {
        return currentAccounts;
    }

    public void setCurrentAccounts(List<T> currentAccounts) {
        this.currentAccounts = currentAccounts;
    }

    public List<T> getSavingsAccounts() {
        return savingsAccounts;
    }

    public void setSavingsAccounts(List<T> savingsAccounts) {
        this.savingsAccounts = savingsAccounts;
    }
}

