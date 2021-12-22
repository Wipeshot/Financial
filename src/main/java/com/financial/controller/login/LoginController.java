package com.financial.controller.login;

import com.financial.connection.MySQLConnection;
import com.financial.object.User;

import static com.financial.controller.login.Encrypt.decrypt;
import static com.financial.controller.login.Encrypt.encrypt;

public class LoginController {

    public static boolean checkPassword(String username, String password) {
        String rightPassword = MySQLConnection.getPasswordByUsername(encrypt(username, String.valueOf(username.length())));
        return rightPassword.equals(encrypt(password, username + encrypt(username, String.valueOf(username.length()))));
    }

    public static boolean setupNewUser(String username, String password, String name, String firstname, String email) {
        if(username.length() > 7 && password.length() > 5 && name.length() < 1 && firstname.length() < 1 && email.length() < 5) {
            String encryptedUsername = encrypt(username, String.valueOf(username.length()));
            String encryptedPassword = encrypt(password, username + encryptedUsername);
            String encryptedName = encrypt(name, encryptedUsername);
            String encryptedFirstname = encrypt(firstname, encryptedName);
            String encryptedEmail = encrypt(email, encryptedFirstname);
            MySQLConnection.createNewUser(encryptedUsername, encryptedPassword, encryptedName, encryptedFirstname, encryptedEmail);
            return true;
        } else {
            return false;
        }
    }

    public static User encryptUserdata(User user, int decryptedUsernameLength) {
        int userid = user.getUserid();
        String username = user.getUsername();
        String name = user.getName();
        String firstname = user.getFirstname();
        String email = user.getEmail();
        String decryptedUsername = decrypt(username, String.valueOf(decryptedUsernameLength));
        String decryptedName = decrypt(name, username);
        String decryptedFirstname = decrypt(firstname, name);
        String decryptedEmail = decrypt(email, firstname);
        return new User(userid, decryptedUsername, decryptedName, decryptedFirstname, decryptedEmail);
    }
}
