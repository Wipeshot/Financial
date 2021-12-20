package com.financial.controller.gui;

import com.financial.connection.MySQLConnection;
import com.financial.controller.ScreenController;
import com.financial.controller.login.Encrypt;
import com.financial.controller.login.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;

import static com.financial.controller.login.Encrypt.encrypt;

public class RegisterScreenControl {

    @FXML
    private GridPane registerPane;
    @FXML
    private TextField firstnameField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField mailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField passwordFieldRepeat;
    private Boolean rdyToRegister;

    private Paint red = Paint.valueOf(Integer.toHexString(Color.RED.hashCode()));

    @FXML
    private void tryToRegister(ActionEvent event) throws IOException {
        rdyToRegister = true;
        if(checkName(firstnameField.getText())){
            firstnameField.setStyle("-fx-control-inner-background: #" +red.toString().substring(2));
            rdyToRegister = false;
        }
        if(checkName(nameField.getText())){
            nameField.setStyle("-fx-control-inner-background: #" +red.toString().substring(2));
            rdyToRegister = false;
        }
        if(!checkUsername(usernameField.getText())) {
            usernameField.setStyle("-fx-control-inner-background: #" + red.toString().substring(2));
            rdyToRegister = false;
        }
        if(!checkMail(mailField.getText())){
            mailField.setStyle("-fx-control-inner-background: #" +red.toString().substring(2));
            rdyToRegister = false;
        }
        if(!checkPassword(passwordField.getText(), passwordFieldRepeat.getText())) {
            passwordField.setStyle("-fx-control-inner-background: #" +red.toString().substring(2));
            passwordFieldRepeat.setStyle("-fx-control-inner-background: #" +red.toString().substring(2));
            rdyToRegister = false;
        }
        if(rdyToRegister) {
            LoginController.setupNewUser(usernameField.getText(), passwordField.getText(), nameField.getText(), firstnameField.getText(), mailField.getText());
            goBackToLogin(event);
        }
    }

    @FXML
    private void goBackToLogin(ActionEvent event) throws IOException {
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ScreenController.class.getResource("LoginScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        window.setScene(scene);
    }

    private boolean checkName(String name) {
        if(name.length() > 2) return false;
        return true;
    }

    private boolean checkUsername(String username) {
        if(username.length() <= 5 || username.length() >12) return false;
        return true;
    }

    private boolean checkMail(String mail) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(mail);
        return m.matches();
    }

    private boolean checkPassword(String password, String repeatPassword) {
        return (password.length() >= 6 && password.equals(repeatPassword));
    }



}
