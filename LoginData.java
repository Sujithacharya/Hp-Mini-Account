package com.example.hp_mini_account;

public class LoginData
{
    private String name,password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginData(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
