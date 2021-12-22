package com.financial.object;

import static com.financial.controller.login.Encrypt.*;

public class BankAccount {

    private final int accountId;
    private final String accountName;
    private final int ownerId;
    private final String encryptAccountName;
    private double balance;

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

    public double getBalance() {
        return balance;
    }

    public void increaseBalance(double value) {
        this.balance += value;
    }

    public void decreaseBalance(double value) {
        this.balance -= value;
    }

    public void setBalance(double balance) {
        this.balance = balance;
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
