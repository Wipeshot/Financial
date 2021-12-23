package com.financial.object;

public class RowObject {

    private String str;
    private String str2;
    private String str3;

    public RowObject(String str) {

        this.str = str;
    }

    public RowObject(String str, String str2) {

        this.str = str;
        this.str2 = str2;
    }

    public RowObject(String str, String str2, String str3) {

        this.str = str;
        this.str2 = str2;
        this.str3 = str3;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String getStr2() {
        return str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }

    public String getStr3() {
        return str3;
    }

    public void setStr3(String str3) {
        this.str3 = str3;
    }
}
