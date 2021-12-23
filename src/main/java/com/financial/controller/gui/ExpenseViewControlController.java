package com.financial.controller.gui;

import com.financial.controller.ScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ExpenseViewControlController {

    @FXML
    private void openAddIncome(ActionEvent event) throws IOException {
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ScreenController.class.getResource("AddIncome.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void openDashboard(ActionEvent event) throws IOException {
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ScreenController.class.getResource("MainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        window.setScene(scene);
    }
    @FXML
    private void reopenIncome(ActionEvent event) throws IOException {
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ScreenController.class.getResource("IncomeView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        window.setScene(scene);
    }
    @FXML
    private void openExpense(ActionEvent event) throws IOException {
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ScreenController.class.getResource("ExpenseView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        window.setScene(scene);
    }
    @FXML
    private void openAccounts(ActionEvent event) throws IOException {
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ScreenController.class.getResource("AccountView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        window.setScene(scene);
    }
    @FXML
    private void openOption(ActionEvent event) throws IOException {
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ScreenController.class.getResource("OptionView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        window.setScene(scene);
    }

    public void openAddExpense(ActionEvent event) throws IOException {
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ScreenController.class.getResource("AddExpenseView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        window.setScene(scene);
    }
}
