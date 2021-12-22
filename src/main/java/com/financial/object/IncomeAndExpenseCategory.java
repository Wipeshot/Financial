package com.financial.object;

public class IncomeAndExpenseCategory {

    private final String category;
    private final boolean income;
    private double amount;

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
    }

    public void decreaseAmount(double amount) {
        this.amount -= amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public IncomeAndExpenseCategory getCategory() {
        return this;
    }

}
