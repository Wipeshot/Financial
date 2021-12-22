package com.financial.controller.gui;

import com.financial.controller.UserController;
import com.financial.object.IncomeAndExpenseCategory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.Icon;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstandlastnameLabel.setText(getUser().getFullName());
        incomeLabel.setText(calculateOverallIncome() + "€");
        biggestIncomeLabel.setText(getCategoryWithBiggestIncome().getCategoryName());
        expenseLabel.setText(calculateOverallExpense() + "€");
        biggestExpenseLabel.setText(getCategoryWithBiggestExpense().getCategoryName());
        setupIncomeChart();
        setupExpenseChart();
    }

    private void setupIncomeChart() {
        ArrayList<IncomeAndExpenseCategory> incomeCategory = getIncomeToCategory();
        ArrayList<PieChart.Data> incomeCatList = new ArrayList<>();
        for (IncomeAndExpenseCategory cat : incomeCategory) {
            incomeCatList.add(new PieChart.Data(cat.getCategoryName(), cat.getAmount()));
        }
        ObservableList<PieChart.Data> incomeChartList = FXCollections.observableList(incomeCatList);
        incomeChart.setData(incomeChartList);
        incomeChart.setTitle("Einnahmen");
        incomeChart.setLabelsVisible(false);
    }

    private void setupExpenseChart() {
        ArrayList<IncomeAndExpenseCategory> expenseCategory = getExpenseToCategory();
        ArrayList<PieChart.Data> expenseCatList = new ArrayList<>();
        for (IncomeAndExpenseCategory cat : expenseCategory) {
            expenseCatList.add(new PieChart.Data(cat.getCategoryName(), cat.getAmount()));
        }
        ObservableList<PieChart.Data> expenseChartList = FXCollections.observableList(expenseCatList);
        expenseChart.setData(expenseChartList);
        expenseChart.setTitle("Ausgaben");
        expenseChart.setLabelsVisible(false);
    }
}
