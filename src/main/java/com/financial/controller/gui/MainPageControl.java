package com.financial.controller.gui;

import com.financial.controller.UserController;
import com.financial.controller.login.LoginController;
import com.financial.object.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import static com.financial.controller.UserController.getUser;

public class MainPageControl implements Initializable {

    @FXML
    private Label firstandlastnameLabel;
    @FXML
    private Label incomeLabel;
    @FXML
    private Label biggestIncomeLabel;
    @FXML
    private Label expenseLabel;
    @FXML
    private Label biggestExpenseLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstandlastnameLabel.setText(getUser().getFullName());
    }
}
