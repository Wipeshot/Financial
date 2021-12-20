package com.financial.connection;

import com.financial.object.User;

import java.sql.*;

public class MySQLConnection {

    private static String url = "jdbc:mysql://127.0.0.1:3306/financial_manager";
    private static String user = "programmcode";
    private static String password = "X.rFNzZJS[14i[Th";

    public static String getPasswordByUsername(String username) {
        String sql = "SELECT password FROM user WHERE username = '" + username + "'";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()) return rs.getString(1);
        } catch (Exception e) {
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
            System.out.println(e.getMessage());
        }
        return null;
    }


}
