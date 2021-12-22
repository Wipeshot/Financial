package com.financial.controller.gui;

import com.financial.connection.MySQLConnection;
import com.financial.controller.UserController;
import com.financial.controller.login.LoginController;
import com.financial.controller.ScreenController;
import com.financial.object.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.financial.controller.login.Encrypt.encrypt;

public class LoginScreenControl {

    @FXML
    private Label passwordLabel;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label usernameLabel;
    @FXML
    private TextField usernameField;

    @FXML
    private void loginTryButton(ActionEvent event) throws IOException {

        String usernameString = usernameField.getText();
        String passwordString = passwordField.getText();
        if(LoginController.checkPassword(usernameString, passwordString)) {
            User user = MySQLConnection.getUserData(encrypt(usernameString, String.valueOf(usernameString.length())));
            UserController.setupUser(LoginController.encryptUserdata(user, usernameString.length()));
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(ScreenController.class.getResource("MainPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            window.setScene(scene);
        }
    }

    @FXML
    private void registerUserButton(ActionEvent event) throws IOException {
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ScreenController.class.getResource("RegisterScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        window.setScene(scene);
    }

    @FXML
    private void forgotPasswordButton(ActionEvent event) throws IOException {
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ScreenController.class.getResource("PasswordForgot.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        window.setScene(scene);
    }
}