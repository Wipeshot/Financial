package com.financial.object;

public class IncomeAndExpenseCategory {

    private final String category;
    private final boolean income;
    private double amount;
    private boolean isUsed = false;

    public IncomeAndExpenseCategory(String category, boolean income) {
        this.category = category;
        this.income = income;
    }

    public String getCategoryName() {
        return category;
    }

    public boolean isIncome() {
        return income;
    }

    public double getAmount() {
        return amount;
    }

    public void increaseAmount(double amount) {
        this.amount += amount;
        isUsed = true;
    }

    public void decreaseAmount(double amount) {
        this.amount -= amount;
        isUsed = true;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public IncomeAndExpenseCategory getCategory() {
        return this;
    }

    public boolean isUsed() {
        return isUsed;
    }
}
