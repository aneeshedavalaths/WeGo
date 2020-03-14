package com.travelcrew.wego;

public class Users {
    private String Name, Email, Password, MobileNo;

    public Users() {
    }

    public Users(String name, String email, String password, String mobileNo) {
        Name = name;
        Email = email;
        Password = password;
        MobileNo = mobileNo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }
}
