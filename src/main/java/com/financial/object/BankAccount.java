package com.financial.object;

import static com.financial.controller.login.Encrypt.*;

public class BankAccount {

    private final int accountId;
    private final String accountName;
    private final int ownerId;
    private final String encryptAccountName;

    public BankAccount(int accountId, String accountName, int ownerId) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.ownerId = ownerId;
        this.encryptAccountName = encrypt(accountName, accountName);
    }

    public int getAccountId() {
        return accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getEncryptAccountName() {
        return encryptAccountName;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public BankAccount getBankAccount() {
        return this;
    }
}
