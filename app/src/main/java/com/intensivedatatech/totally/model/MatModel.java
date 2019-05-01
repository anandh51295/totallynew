package com.intensivedatatech.totally.model;

import com.google.gson.annotations.SerializedName;

public class MatModel {
    @SerializedName("id")
    int id;
    @SerializedName("name_address")
    String name_address;
    @SerializedName("quantity")
    int quantity;
    @SerializedName("price")
    Float price;
    @SerializedName("totalprice")
    Float totalprice;
    @SerializedName("date")
    String date;

    public MatModel(int id, String name_address, int quantity, Float price, Float totalprice,String date) {
        this.id = id;
        this.name_address = name_address;
        this.quantity = quantity;
        this.price = price;
        this.totalprice = totalprice;
        this.date=date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_address() {
        return name_address;
    }

    public void setName_address(String name_address) {
        this.name_address = name_address;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Float totalprice) {
        this.totalprice = totalprice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}