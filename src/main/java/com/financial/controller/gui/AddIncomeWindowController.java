package com.financial.controller.gui;

import com.financial.connection.MySQLConnection;
import com.financial.controller.ScreenController;
import com.financial.object.IncomeAndExpenseCategory;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AddIncomeWindowController {

    @FXML
    private TextField descriptionField;
    @FXML
    private DatePicker incomeDatePicker;
    @FXML
    private TextField valueField;
    @FXML
    private ComboBox categoryBox;
    @FXML
    private ComboBox accountBox;
    @FXML
    private Button cancelButton;
    @FXML
    private Button submitButton;

    ArrayList<IncomeAndExpenseCategory> differentCategories;
    ArrayList<String> incomeCategory;

    public AddIncomeWindowController() throws IOException {

        differentCategories = MySQLConnection.getCategory();
        incomeCategory = new ArrayList<>();

        initCategoryBox();


        display();
    }

    private void display() throws IOException {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(ScreenController.class.getResource("AddIncome.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        window.setScene(scene);
        window.showAndWait();
    }

    private void initCategoryBox() {
        for(IncomeAndExpenseCategory cat : differentCategories) {
            if(cat.isIncome()) {
                incomeCategory.add(cat.getCategoryName());
            }
        }
        ObservableList<String> category = FXCollections.observableList(incomeCategory);
        categoryBox.getItems().add(category);
    }

}
