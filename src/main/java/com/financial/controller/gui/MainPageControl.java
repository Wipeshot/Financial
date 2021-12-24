package com.financial.controller.gui;

import com.financial.controller.ScreenController;
import com.financial.controller.UserController;
import com.financial.object.IncomeAndExpenseCategory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.Icon;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.financial.controller.UserController.*;

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
    @FXML
    private PieChart expenseChart;
    @FXML
    private PieChart incomeChart;
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
        firstandlastnameLabel.setText(getUser().getFullName());
        incomeLabel.setText(calculateOverallIncome() + "€");
        biggestIncomeLabel.setText(getCategoryWithBiggestIncome().getCategoryName());
        expenseLabel.setText(calculateOverallExpense() + "€");
        biggestExpenseLabel.setText(getCategoryWithBiggestExpense().getCategoryName());
        setupIncomeChart();
        setupExpenseChart();
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
        UserController.reloadUser();
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ScreenController.class.getResource("MainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        window.setScene(scene);
    }
    @FXML
    private void reopenIncome(ActionEvent event) throws IOException {
        UserController.reloadUser();
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ScreenController.class.getResource("IncomeView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        window.setScene(scene);
    }
    @FXML
    private void openExpense(ActionEvent event) throws IOException {
        UserController.reloadUser();
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ScreenController.class.getResource("ExpenseView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        window.setScene(scene);
    }
    @FXML
    private void openAccounts(ActionEvent event) throws IOException {
        UserController.reloadUser();
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ScreenController.class.getResource("AccountView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        window.setScene(scene);
    }
    @FXML
    private void openOption(ActionEvent event) throws IOException {
        UserController.reloadUser();
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ScreenController.class.getResource("OptionView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        window.setScene(scene);
    }

    private void setupIncomeChart() {
        UserController.reloadUser();
        ArrayList<IncomeAndExpenseCategory> incomeCategory = getIncomeToCategory();
        ArrayList<PieChart.Data> incomeCatList = new ArrayList<>();
        for (IncomeAndExpenseCategory cat : incomeCategory) {
            incomeCatList.add(new PieChart.Data(cat.getCategoryName(), cat.getAmount()));
        }
        ObservableList<PieChart.Data> incomeChartList = FXCollections.observableList(incomeCatList);
        incomeChart.setData(incomeChartList);
        incomeChart.setTitle("Einnahmen");
        incomeChart.setLegendSide(Side.RIGHT);
        incomeChart.setLabelsVisible(false);
    }

    private void setupExpenseChart() {
        UserController.reloadUser();
        ArrayList<IncomeAndExpenseCategory> expenseCategory = getExpenseToCategory();
        ArrayList<PieChart.Data> expenseCatList = new ArrayList<>();
        for (IncomeAndExpenseCategory cat : expenseCategory) {
            expenseCatList.add(new PieChart.Data(cat.getCategoryName(), cat.getAmount()));
        }
        ObservableList<PieChart.Data> expenseChartList = FXCollections.observableList(expenseCatList);
        expenseChart.setData(expenseChartList);
        expenseChart.setTitle("Ausgaben");
        expenseChart.setLegendSide(Side.RIGHT);
        expenseChart.setLabelsVisible(false);
    }
}
