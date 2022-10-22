package com.financial.controller.gui;

import com.financial.connection.SQLConnection;
import com.financial.controller.ScreenController;
import com.financial.controller.UserController;
import com.financial.object.BankAccount;
import com.financial.object.IncomeAndExpenseCategory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;


public class AddIncomeWindowController implements Initializable {

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

    Pattern validEditingState = Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9]*)?");

    UnaryOperator<TextFormatter.Change> filter = c -> {
        String text = c.getControlNewText();
        if (validEditingState.matcher(text).matches()) {
            return c ;
        } else {
            return null ;
        }
    };

    StringConverter<Double> converter = new StringConverter<Double>() {

        @Override
        public Double fromString(String s) {
            if (s.isEmpty() || "-".equals(s) || ".".equals(s) || "-.".equals(s)) {
                return 0.0 ;
            } else {
                return Double.valueOf(s);
            }
        }


        @Override
        public String toString(Double d) {
            return d.toString();
        }
    };

    TextFormatter<Double> textFormatter = new TextFormatter<>(converter, 0.0, filter);

    ArrayList<IncomeAndExpenseCategory> differentCategories;
    ArrayList<String> incomeCategory;
    ArrayList<BankAccount> bankAccounts;
    ArrayList<String> bankAccountsString;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserController.reloadUser();
        differentCategories = UserController.getCategory();
        incomeCategory = new ArrayList<>();
        bankAccounts = UserController.getBankAccounts();
        bankAccountsString = new ArrayList<>();

        initCategoryBox();
        initAccountBox();
        initValueField();
        initIncomeDatePicker();
    }

    @FXML
    private void cancelButton(ActionEvent event) throws IOException {
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ScreenController.class.getResource("IncomeView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        window.setScene(scene);
    }

    @FXML
    private void addIncomeButtonPressed(ActionEvent event) throws IOException {
        System.out.println("Pressed");
        BankAccount bankAccount = UserController.getBankAccountForName((String) accountBox.getSelectionModel().getSelectedItem());
        System.out.println(accountBox.getSelectionModel().getSelectedItem());
        if(bankAccount == null) return;
        System.out.println("Solved Bankaccount");
        IncomeAndExpenseCategory category = UserController.getCategoryForName((String) categoryBox.getSelectionModel().getSelectedItem(), true);
        if(category == null) return;
        System.out.println("Solved category");
        double value = Double.parseDouble(valueField.getText());
        if(value <= 0) return;
        System.out.println(incomeDatePicker.getValue());
        LocalDate localDate = incomeDatePicker.getValue();
        if(localDate.isAfter(localDate(getDateTime()))) return;
        String description = descriptionField.getText();
        SQLConnection.addIncome(bankAccount.getAccountId(), category.getCategoryId(), value, description);
        cancelButton(event);
    }

    private void initCategoryBox() {
        for(IncomeAndExpenseCategory cat : differentCategories) {
            if(cat.isIncome()) {
                incomeCategory.add(cat.getCategoryName());
            }
        }
        ObservableList<String> category = FXCollections.observableList(incomeCategory);
        categoryBox.setItems(category);
    }

    private void initAccountBox() {
        for (BankAccount acc : bankAccounts) {
            bankAccountsString.add(acc.getAccountDecryptedName());
        }
        ObservableList<String> bankAccount = FXCollections.observableList(bankAccountsString);
        accountBox.setItems(bankAccount);
    }

    private void initValueField() {
        valueField.setTextFormatter(textFormatter);
    }

    private void initIncomeDatePicker() {
        incomeDatePicker.setValue(localDate(getDateTime()));
    }

    private static final LocalDate localDate (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }

    private String getDateTime() {
        return new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    }


}
