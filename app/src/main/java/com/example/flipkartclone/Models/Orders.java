package com.example.flipkartclone.Models;

public class Orders {

    private  String Product_Name1 ;
    private  String Product_Price1;

    public String getProduct_Price1() {
        return Product_Price1;
    }

    public void setProduct_Price1(String Product_Price) {
        Product_Price1 = Product_Price;
    }

    public Orders(String Product_Name, String Product_Price) {
        this.Product_Name1 = Product_Name;
        this.Product_Price1 = Product_Price;
    }

    public String getProduct_Name1() {
        return Product_Name1;
    }

    public void setProduct_Name(String Product_Name) {
        Product_Name1 = Product_Name;
    }


    public Orders() {
    }

}
