package com.financial.controller;

import com.financial.connection.MySQLConnection;
import com.financial.object.*;

import java.util.ArrayList;
import java.util.Locale;

public class UserController {

        private static User user;
        private static ArrayList<IncomeAndExpenseCategory> category = MySQLConnection.getCategory();
        private static ArrayList<BankAccount> bankAccounts;
        private static ArrayList<Income> income = new ArrayList<>();
        private static ArrayList<Expense> expense = new ArrayList<>();

        public static User getUser() {
                return user;
        }

        public static void setupUser(User user) {
                UserController.user = user;
                bankAccounts = MySQLConnection.getBankAccounts(user.getUserid());
                if(bankAccounts != null) for (BankAccount acc : bankAccounts) {
                        ArrayList<Income> incomes = MySQLConnection.getIncome(acc.getAccountId());
                        ArrayList<Expense> expenses = MySQLConnection.getExpense(acc.getAccountId());
                        if (incomes != null) for (Income in : incomes) {
                                income.add(in);
                        }
                        if (expenses != null) for (Expense exp : expenses) {
                                expense.add(exp);
                        }
                }
                setupIncomeToCategory();
                setupExpenseToCategory();
        }

        public static void setupIncomeToCategory() {
                resetCategoryCapital();
                for (IncomeAndExpenseCategory cat : category) {
                        if (cat.isIncome()) for(Income in : income) {
                                        if (in.getCategory() == cat.getCategoryName()) cat.increaseAmount(in.getAmount());
                        }
                }
        }

        public static void setupExpenseToCategory() {
                resetCategoryCapital();
                for (IncomeAndExpenseCategory cat : category) {
                        if (cat.isIncome()) for(Income in : income) {
                                if (in.getCategory() == cat.getCategoryName()) cat.increaseAmount(in.getAmount());
                        }
                }
        }

        private static void resetCategoryCapital() {
                for (IncomeAndExpenseCategory cat : category) {
                        cat.setAmount(0);
                }
        }

        public static double calculateOverallIncome() {
                double overall = 0;
                for (Income in : income) {
                        overall += in.getAmount();
                }
                return overall;
        }

        public static double calculateOverallExpense() {
                double overall = 0;
                for (Expense exp : expense) {
                        overall += exp.getAmount();
                }
                return overall;
        }

        public static IncomeAndExpenseCategory getCategoryWithBiggestIncome() {
                IncomeAndExpenseCategory biggestIncomeCategory = new IncomeAndExpenseCategory("Kein Einkommen", true);
                for (IncomeAndExpenseCategory cat : category) {
                        if (cat.getAmount() > biggestIncomeCategory.getAmount() && cat.isIncome()) biggestIncomeCategory = cat;
                }
                return biggestIncomeCategory;
        }

        public static IncomeAndExpenseCategory getCategoryWithBiggestExpense() {
                IncomeAndExpenseCategory biggestExpenseCategory = new IncomeAndExpenseCategory("Keine Ausgaben", false);
                for (IncomeAndExpenseCategory cat : category) {
                        if (cat.getAmount() > biggestExpenseCategory.getAmount() && !cat.isIncome()) biggestExpenseCategory = cat;
                }
                return biggestExpenseCategory;
        }

        public static ArrayList<IncomeAndExpenseCategory> getIncomeToCategory() {
                ArrayList<IncomeAndExpenseCategory> incomeCategory = new ArrayList<>();
                for (IncomeAndExpenseCategory cat : category) {
                        if (cat.isIncome()) incomeCategory.add(cat);
                }
                try {
                        incomeCategory.get(0);
                } catch (Exception e) {
                        incomeCategory.add(new IncomeAndExpenseCategory("Keine Einnahmen", true));
                        incomeCategory.get(0).setAmount(100);
                }
                return incomeCategory;
        }

        public static ArrayList<IncomeAndExpenseCategory> getExpenseToCategory() {
                ArrayList<IncomeAndExpenseCategory> expenseCategory = new ArrayList<>();
                for (IncomeAndExpenseCategory cat : category) {
                        if (!cat.isIncome()) expenseCategory.add(cat);
                }
                try {
                        expenseCategory.get(0);
                } catch (Exception e) {
                        expenseCategory.add(new IncomeAndExpenseCategory("Keine Ausgaben", false));
                        expenseCategory.get(0).setAmount(100);
                }
                return expenseCategory;
        }
}
