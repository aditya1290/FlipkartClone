package com.example.flipkartclone.Models;

public class ProductMobile {

    private String Name;
    private String Company;
    private String FrontCam;
    private String BackCam;
    private String RAM;
    private String Display;
    private String ScreenSize;
    private String Battery;
    private String Processor;
    private String Price;
    private String ROM;
    private String Color;
    private String Expanded;
    private String URL;
    private int OffMobile;
    private int star5;
    private int star4;
    private int star3;
    private int star2;
    private int star1;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private int count;

    public int getOffMobile() {
        return OffMobile;
    }

    public void setOffMobile(int offMobile) {
        OffMobile = offMobile;
    }



    public int getStar5() {
        return star5;
    }

    public void setStar5(int star5) {
        this.star5 = star5;
    }

    public int getStar4() {
        return star4;
    }

    public void setStar4(int star4) {
        this.star4 = star4;
    }

    public int getStar3() {
        return star3;
    }

    public void setStar3(int star3) {
        this.star3 = star3;
    }

    public int getStar2() {
        return star2;
    }

    public void setStar2(int star2) {
        this.star2 = star2;
    }

    public int getStar1() {
        return star1;
    }

    public void setStar1(int star1) {
        this.star1 = star1;
    }



    public ProductMobile(String name, String company, String frontCam, String backCam, String RAM, String display, String screenSize, String battery, String processor, String price, String ROM, String color, String expanded, String URL, int star5, int star4, int star3, int star2, int star1, int Offset,int count) {
        this.OffMobile = Offset;
        this.Name = name;
        this.Company = company;
        this.FrontCam = frontCam;
        this.count  = count;
        this.BackCam = backCam;
        this.RAM = RAM;
        this.Display = display;
        this.ScreenSize = screenSize;
        this.Battery = battery;
        this.Processor = processor;
        this.Price = price;
        this.ROM = ROM;
        this.Color = color;
        this.Expanded = expanded;
        this.URL = URL;
        this.star1 = star1;
        this.star2 = star2;
        this.star3 = star3;
        this.star4 = star4;
        this.star5 = star5;
    }

    public ProductMobile(){}




    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getFrontCam() {
        return FrontCam;
    }

    public void setFrontCam(String frontCam) {
        FrontCam = frontCam;
    }

    public String getBackCam() {
        return BackCam;
    }

    public void setBackCam(String backCam) {
        BackCam = backCam;
    }

    public String getRAM() {
        return RAM;
    }

    public void setRAM(String RAM) {
        this.RAM = RAM;
    }

    public String getDisplay() {
        return Display;
    }

    public void setDisplay(String display) {
        Display = display;
    }

    public String getScreenSize() {
        return ScreenSize;
    }

    public void setScreenSize(String screenSize) {
        ScreenSize = screenSize;
    }

    public String getBattery() {
        return Battery;
    }

    public void setBattery(String battery) {
        Battery = battery;
    }

    public String getProcessor() {
        return Processor;
    }

    public void setProcessor(String processor) {
        Processor = processor;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getROM() {
        return ROM;
    }

    public void setROM(String ROM) {
        this.ROM = ROM;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getExpanded() {
        return Expanded;
    }

    public void setExpanded(String expanded) {
        Expanded = expanded;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }




}
