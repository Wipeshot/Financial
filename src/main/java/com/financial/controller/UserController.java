package com.financial.controller;

import com.financial.connection.MySQLConnection;
import com.financial.object.*;

import java.util.ArrayList;

public class UserController {

        private static User user;
        private static ArrayList<IncomeAndExpenseCategory> category = MySQLConnection.getCategory();
        private static ArrayList<BankAccount> bankAccounts;
        private static ArrayList<Income> income;
        private static ArrayList<Expense> expense;

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
        }

        public static void setupIncomeToCategory() {
                for (IncomeAndExpenseCategory cat : category) {
                        if (cat.isIncome()) for(Income in : income) {
                                        if (in.getCategory() == cat.getCategoryName()) cat.increaseAmount(in.getAmount());
                        }
                }
        }

        public static void setupExpenseToCategory() {
                for (IncomeAndExpenseCategory cat : category) {
                        if (cat.isIncome()) for(Income in : income) {
                                if (in.getCategory() == cat.getCategoryName()) cat.increaseAmount(in.getAmount());
                        }
                }
        }

        private static void resetIncome() {

        }
}
