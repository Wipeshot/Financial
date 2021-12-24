package com.financial.object;

import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class RowObject {

    private SimpleStringProperty str, str2, str3, str4;
    private LocalDate date;

    public RowObject(String str, String str2) {
        this.str = new SimpleStringProperty(str);
        this.str2 = new SimpleStringProperty(str2);
    }

    public RowObject(String str, String str2, String str3, LocalDate date, String str4) {
        this.str = new SimpleStringProperty(str);
        this.str2 = new SimpleStringProperty(str2);
        this.str3 = new SimpleStringProperty(str3);
        this.str4 = new SimpleStringProperty(str4);
        this.date = date;
    }

    public String getStr() {
        return str.get();
    }
    public void setStr(String value) {
        str.setValue(value);
    }

    public String getStr2() {
        return str2.get();
    }
    public void setStr2(String value) {
        str2.setValue(value);
    }

    public String getStr3() {
        return str3.get();
    }
    public void setStr3(String value) {
        str3.setValue(value);
    }

    public String getStr4() {
        return str4.get();
    }
    public void setStr4(String value) {
        str4.setValue(value);
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

}
