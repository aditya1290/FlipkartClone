package com.example.flipkartclone.Models;

public class User {


    public User(String userId, String name, String password, String phone, String email, int plusMember) {
        UserId = userId;
        Name = name;
        Password = password;
        Phone = phone;
        Email = email;
        PlusMember = plusMember;
    }

    private String UserId;
    private String Name;
    private String Password;
    private String Phone;
    private String Email;
    private int PlusMember;



    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getPlusMember() {
        return PlusMember;
    }

    public void setPlusMember(int plusMember) {
        PlusMember = plusMember;
    }
}
