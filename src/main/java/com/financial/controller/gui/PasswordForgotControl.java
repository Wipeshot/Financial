package com.financial.controller.gui;

import com.financial.controller.ScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PasswordForgotControl {

    @FXML
    private void goBackToLogin(ActionEvent event) throws IOException {
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ScreenController.class.getResource("LoginScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        window.setScene(scene);
    }

}
