package com.financial.object;

public class User {

    private final int userid;
    private final String username;
    private final String name;
    private final String firstname;
    private final String email;

    public User(int userid, String username, String name, String firstname, String email) {

        this.userid = userid;
        this.username = username;
        this.name = name;
        this.firstname = firstname;
        this.email = email;
    }

    public int getUserid() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return firstname + " " + name;
    }

}
