package com.financial.controller.gui;

import com.financial.connection.MySQLConnection;
import com.financial.controller.ScreenController;
import com.financial.controller.UserController;
import com.financial.object.BankAccount;
import com.financial.object.RowObject;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AccountViewController implements Initializable {

    @FXML
    private TableView<String> tableAccounts;
    @FXML
    private TableColumn<String , String> tableColmAccountName;
    @FXML
    private TableColumn<String, String> tableColmAccountBalance;
    @FXML
    private TextField addAccountField;

    private ObservableList<String> data = FXCollections.observableArrayList();

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

    @FXML
    private void openAddBankAccount(ActionEvent event) {
        String accountName = addAccountField.getText();
        if(accountName != "" && accountName.length() <= 10) MySQLConnection.addBankAccount(accountName, UserController.getUser().getUserid());
        initTable();
    }

    private void initTable() {
        tableAccounts.getItems().removeAll();
        ArrayList<BankAccount> account = UserController.getBankAccounts();
        for(BankAccount acc : account) {
            data.add(0, acc.getAccountName());
            data.add(1, String.valueOf(acc.getBalance()));
        }
        tableAccounts.setItems(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableColmAccountName.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
        tableColmAccountBalance.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
        initTable();
    }
}
