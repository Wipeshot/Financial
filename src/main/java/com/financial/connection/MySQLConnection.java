package com.financial.connection;

import com.financial.object.*;

import java.sql.*;
import java.util.ArrayList;

public class MySQLConnection {

    private static final String url = "jdbc:mysql://127.0.0.1:3306/financial_manager";
    private static final String user = "programmcode";
    private static final String password = "X.rFNzZJS[14i[Th";

    public static String getPasswordByUsername(String username) {
        String sql = "SELECT password FROM user WHERE username = '" + username + "'";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()) return rs.getString(1);
        } catch (Exception e) {
            System.out.println("getPasswordByUsername()");
            System.out.println(e.getMessage());
        }
        return "";
    }

    public static void createNewUser(String username, String password, String name, String firstname, String mail) {
        String sql = "INSERT INTO user (userid ,username, password, name, firstname, mail)" +
                " VALUES (null, '" + username + "','"+ password + "','" + name + "','" + firstname + "','" + mail + "')";
        try {
            Connection con = DriverManager.getConnection(url, user, MySQLConnection.password);
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("createNewUser()");
            System.out.println(e.getMessage());
        }
    }

    public static User getUserData(String username) {
        String sql = "SELECT userid, username, name, firstname, mail " +
                "FROM user " +
                "WHERE username = '" + username + "'";
        int userid = 0;
        String usernameOut = null;
        String name = null;
        String firstname = null;
        String mail = null;
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                userid = rs.getInt("userid");
                usernameOut = rs.getString("username");
                name = rs.getString("name");
                firstname = rs.getString("firstname");
                mail = rs.getString("mail");
            }
            return new User(userid, usernameOut, name, firstname, mail);
        } catch (Exception e) {
            System.out.println("getUserData()");
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static ArrayList<Income> getIncome(int kontoid) {
        String sql = "SELECT k.bezeichnung, e.betrag, e.beschreibung, e.datum " +
                "FROM einnahme e, kategorie k " +
                "WHERE e.kategorieid = k.kategorieid" +
                "AND kontoid = " + kontoid;
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String category;
            double amount;
            String description;
            Date date;
            ArrayList<Income> income = new ArrayList<>();
            while(rs.next()) {
                category = rs.getString(1);
                amount = rs.getDouble(2);
                description = rs.getString(3);
                date = rs.getDate(4);
                income.add(new Income(category, amount, description, kontoid, date));
            }
            return income;
        } catch (Exception e) {
            System.out.println("getIncome()");
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static ArrayList<Expense> getExpense(int kontoid) {
        String sql = "SELECT k.bezeichnung, a.betrag, a.beschreibung, a.datum " +
                "FROM ausgabe a, kategorie k " +
                "WHERE a.kategorieid = k.kategorieid" +
                "AND kontoid = " + kontoid;
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String category;
            double amount;
            String description;
            Date date;
            ArrayList<Expense> expense = new ArrayList<>();
            while(rs.next()) {
                category = rs.getString(1);
                amount = rs.getDouble(2);
                description = rs.getString(3);
                date = rs.getDate(4);
                expense.add(new Expense(category, amount, description, kontoid, date));
            }
            return expense;
        } catch (Exception e) {
            System.out.println("getExpense()");
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static ArrayList<BankAccount> getBankAccounts(int userid) {
        String sql = "SELECT kontoid, kontoname, inhaberid FROM konto WHERE inhaberid = " + userid;
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int accountid;
            String name;
            int ownerid;
            ArrayList<BankAccount> bankAccount = new ArrayList<>();
            while(rs.next()) {
                accountid = rs.getInt(1);
                name = rs.getString(2);
                ownerid = rs.getInt(3);
                bankAccount.add(new BankAccount(accountid,name,ownerid));
            }
            return bankAccount;
        } catch (Exception e) {
            System.out.println("getBankAccounts()");
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static ArrayList<IncomeAndExpenseCategory> getCategory() {
        String sql = "SELECT bezeichnung, einnahme FROM kategorie";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<IncomeAndExpenseCategory> category = new ArrayList<>();
            while(rs.next()) {
                String name = rs.getString(1);
                int einnahmeValue = rs.getInt(2);
                if(einnahmeValue == 2) {
                    category.add(new IncomeAndExpenseCategory(name, true));
                    category.add(new IncomeAndExpenseCategory(name, false));
                } else if ( einnahmeValue == 1) {
                    category.add(new IncomeAndExpenseCategory(name, true));
                }else if (einnahmeValue == 0) {
                    category.add(new IncomeAndExpenseCategory(name, false));
                }
            }
            return category;
        } catch (Exception e) {
            System.out.println("getCategory()");
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void addBankAccount(String name, int ownerid) {
        String sql = "INSERT INTO konto (kontoid, kontoname, inhaberid) " +
                "VALUES (null, '" + name + "', " + ownerid + ")";
        try {
            Connection con = DriverManager.getConnection(url, user,password);
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException throwable) {
            System.out.println("addBankAccount()");
            throwable.printStackTrace();
        }
    }

    public static void addIncome(int accountid, int categoryid, double amount, String description) {
        String sql = "INSERT INTO einnahme (einnahmeid, kontoid, kategorieid, betrag, beschreibung) " +
                "VALUES (null, " + accountid + ", " + categoryid + ", " + amount + ", '" + description +"')";
        try {
            Connection con = DriverManager.getConnection(url, user,password);
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException throwable) {
            System.out.println("addIncome()");
            throwable.printStackTrace();
        }
    }

    public static void addExpense(int accountid, int categoryid, double amount, String description) {
        String sql = "INSERT INTO ausgabe (ausgabeid, kontoid, kategorieid, betrag, beschreibung) " +
                "VALUES (null, " + accountid + ", " + categoryid + ", " + amount + ", '" + description +"')";
        try {
            Connection con = DriverManager.getConnection(url, user,password);
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException throwable) {
            System.out.println("addExpense()");
            throwable.printStackTrace();
        }
    }
}
