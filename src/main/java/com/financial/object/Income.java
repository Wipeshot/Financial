package com.financial.object;

import java.sql.Date;
import java.time.LocalDate;

public class Income {

    private final String category;
    private final double amount;
    private final String description;
    private final int bankAccount;
    private final LocalDate date;

    public Income(String category, double amount, String description, int bankAccount, LocalDate date) {
        this.category = category;
        this.amount = amount;
        this.description = description;
        this.bankAccount = bankAccount;
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public int getBankAccount() {
        return bankAccount;
    }

    public Income getIncome() {
        return this;
    }
}
