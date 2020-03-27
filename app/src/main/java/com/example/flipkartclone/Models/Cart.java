package com.example.flipkartclone.Models;

public class Cart {

    private String Product_Name;
    private String company_name;
    private String ImageURL12;
    private String Price;
    private String quantity;

    public Cart(String product_Name, String company_name, String ImageURL, String Price, String quantity) {
        this.Product_Name = product_Name;
        this.company_name = company_name;
        this.ImageURL12 = ImageURL;
        this.Price = Price;
        this.quantity = quantity;
    }

    public Cart() {
    }


    public String getImageURL12() {
        return ImageURL12;
    }

    public void setImageURL12(String ImageURL) {
        ImageURL12 = ImageURL;
    }



    public String getProduct_Name() {
        return Product_Name;
    }

    public void setProduct_Name(String product_Name) {
        Product_Name = product_Name;
    }



    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }


    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        Price = Price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}