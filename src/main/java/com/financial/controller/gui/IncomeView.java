package com.financial.controller.gui;

import com.financial.controller.ScreenController;
import com.financial.controller.UserController;
import com.financial.object.BankAccount;
import com.financial.object.Income;
import com.financial.object.RowObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.financial.controller.UserController.getBankAccounts;
import static com.financial.controller.UserController.getIncome;

public class IncomeView implements Initializable {

    @FXML
    private TableView tableIncome;
    @FXML
    private TableColumn tableColmAccount;
    @FXML
    private TableColumn tableColmValue;
    @FXML
    private TableColumn tableColmCat;
    @FXML
    private TableColumn tableColmDate;
    @FXML
    private TableColumn tableColmDesc;
    @FXML
    private PieChart incomeToAccountChart;
    @FXML
    private Button addIncomeButton;
    @FXML
    private Button deleteIncomeButton;
    @FXML
    private Button dashboardButton;
    @FXML
    private Button incomeButton;
    @FXML
    private Button expenseButton;
    @FXML
    private Button accountsButton;
    @FXML
    private Button optionButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserController.reloadUser();
        initTable();
        initPieChart();
        tableColmAccount.setCellValueFactory(new PropertyValueFactory<>("str"));
        tableColmValue.setCellValueFactory(new PropertyValueFactory<>("str2"));
        tableColmCat.setCellValueFactory(new PropertyValueFactory<>("str3"));
        tableColmDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableColmDesc.setCellValueFactory(new PropertyValueFactory<>("str4"));
        tableIncome.setItems(initTable());
    }

    private ObservableList<RowObject> initTable() {
        ObservableList<RowObject> rows = FXCollections.observableArrayList();
        ArrayList<Income> income = getIncome();
        for(Income in : income) {
            rows.add(new RowObject(UserController.getBankAccountForId(in.getBankAccount()).getAccountDecryptedName(), in.getAmount() + "€", in.getCategory(), in.getDate(), in.getDescription()));
        }
        return rows;
    }

    private void initPieChart() {
        ArrayList<BankAccount> accounts = UserController.getBankAccounts();
        ArrayList<PieChart.Data> chartBalance = new ArrayList<>();
        for (BankAccount acc : accounts) {
            chartBalance.add(new PieChart.Data(acc.getAccountDecryptedName(), acc.getBalance()));
        }
        if (chartBalance.size() > 0) {
            ObservableList<PieChart.Data> chart = FXCollections.observableList(chartBalance);
            incomeToAccountChart.setData(chart);
            incomeToAccountChart.setLegendSide(Side.RIGHT);
            incomeToAccountChart.setTitle("Kontoguthaben pro Konto");
        }
    }

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

}
