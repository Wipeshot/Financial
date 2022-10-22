package com.financial.controller.gui;

import com.financial.connection.SQLConnection;
import com.financial.controller.ScreenController;
import com.financial.controller.UserController;
import com.financial.object.BankAccount;
import com.financial.object.RowObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.financial.controller.UserController.*;

public class AccountViewController implements Initializable {

    @FXML private TableView<RowObject> tableAccounts;
    @FXML private TableColumn<RowObject, String> tableColmAccountName, tableColmAccountBalance;
    @FXML private TextField addAccountField;
    @FXML private Button addAccountButton;
    @FXML private Label networthLabel;

    @FXML private void openDashboard(ActionEvent event) throws IOException {
        UserController.reloadUser();
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ScreenController.class.getResource("MainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        window.setScene(scene);
    }
    @FXML private void reopenIncome(ActionEvent event) throws IOException {
        UserController.reloadUser();
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ScreenController.class.getResource("IncomeView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        window.setScene(scene);
    }
    @FXML private void openExpense(ActionEvent event) throws IOException {
        UserController.reloadUser();
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ScreenController.class.getResource("ExpenseView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        window.setScene(scene);
    }
    @FXML private void openAccounts(ActionEvent event) throws IOException {
        UserController.reloadUser();
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ScreenController.class.getResource("AccountView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        window.setScene(scene);
    }
    @FXML private void openOption(ActionEvent event) throws IOException {
        UserController.reloadUser();
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ScreenController.class.getResource("OptionView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        window.setScene(scene);
    }

    @FXML private void openAddBankAccount(ActionEvent event) throws IOException {
        String accountName = addAccountField.getText();
        if(accountName != "" && accountName.length() <= 10) {
            BankAccount newAccount = new BankAccount(-1, addAccountField.getText(), UserController.getUser().getUserid());
            SQLConnection.addBankAccount(newAccount.getEncryptAccountName(), newAccount.getOwnerId());
        }
        openAccounts(event);
    }

    @FXML private void removeBankAccount(ActionEvent event) throws IOException {
        RowObject selectedItem = tableAccounts.getSelectionModel().getSelectedItem();
        if(selectedItem != null) {
            tableAccounts.getItems().remove(selectedItem);
            SQLConnection.removeBankAccount(UserController.getBankAccountForName(selectedItem.getStr()).getAccountId());
        }
        openAccounts(event);
    }

    private ObservableList<RowObject> initTable() {
        ObservableList<RowObject> rows = FXCollections.observableArrayList();
        ArrayList<BankAccount> account = getBankAccounts();
        for(BankAccount acc : account) {
            System.out.println(acc.getAccountDecryptedName());
            rows.add(new RowObject(acc.getAccountDecryptedName(), acc.getBalance() + "€"));
        }
        return rows;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reloadUser();
        networthLabel.setText("Gesamtes Vermögen: " + calculateNetWorth() + "€");
        tableColmAccountName.setCellValueFactory(new PropertyValueFactory<>("str"));
        tableColmAccountBalance.setCellValueFactory(new PropertyValueFactory<>("str2"));
        tableAccounts.setItems(initTable());
    }
}
