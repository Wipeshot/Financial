package com.financial.object;

public class Income {

    private final String category;
    private final double amount;
    private final String description;
    private final int bankAccount;

    public Income(String category, double amount, String description, int bankAccount) {
        this.category = category;
        this.amount = amount;
        this.description = description;
        this.bankAccount = bankAccount;
    }

    public String getCategory() {
        return category;
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
