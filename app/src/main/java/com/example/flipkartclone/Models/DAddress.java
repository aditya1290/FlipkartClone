package com.example.flipkartclone.Models;

public class DAddress {

    private String Address;
    private String Pincode;
    private String City;

    public DAddress(String address, String pincode, String city) {
        this.Address = address;
        this.Pincode = pincode;
        this.City = city;
    }



    public DAddress() {
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPincode() {
        return Pincode;
    }

    public void setPincode(String pincode) {
        Pincode = pincode;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }




}
