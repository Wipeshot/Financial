package com.financial.object;

import com.financial.controller.UserController;
import com.financial.controller.login.Encrypt;

import static com.financial.controller.login.Encrypt.*;

public class BankAccount {

    private final int accountId;
    private final String accountName;
    private final int ownerId;
    private final String encryptAccountName;
    private double income = 0;
    private double expense = 0;

    public BankAccount(int accountId, String accountName, int ownerId) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.ownerId = ownerId;
        this.encryptAccountName = encrypt(accountName, UserController.getUser().getUsername());
    }

    public int getAccountId() {
        return accountId;
    }

    public String getAccountDecryptedName() {
        return Encrypt.decrypt(accountName, UserController.getUser().getUsername());
    }

    public String getAccountName() {
        return accountName;
    }

    public double getBalance() {
        return income-expense;
    }

    public void increaseBalance(double value) {
        this.income += value;
    }

    public void decreaseBalance(double value) {
        this.expense += value;
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
