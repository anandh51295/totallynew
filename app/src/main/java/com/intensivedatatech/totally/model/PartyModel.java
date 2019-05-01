package com.intensivedatatech.totally.model;

import com.google.gson.annotations.SerializedName;

public class PartyModel {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("address")
    String address;
    @SerializedName("number")
    String number;

    public PartyModel(int id, String name, String address, String number){
        this.id=id;
        this.name=name;
        this.address=address;
        this.number=number;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
