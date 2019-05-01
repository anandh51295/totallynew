package com.intensivedatatech.totally.model;

import com.google.gson.annotations.SerializedName;

public class NoolModel {

    @SerializedName("id")
    int id;
    @SerializedName("p_id")
    int pid;
    @SerializedName("whitequantity")
    int whitequantity;
    @SerializedName("whiteprice")
    float whiteprice;
    @SerializedName("colorquantity")
    int colorquantity;
    @SerializedName("colorprice")
    float colorprice;
    @SerializedName("totalprice")
    float totalprice;
    @SerializedName("paid")
    float paid;
    @SerializedName("date")
    String date;

    public NoolModel(int id, int pid, int whitequantity, float whiteprice, int colorquantity, float colorprice, float totalprice, float paid,String date){
        this.id=id;
        this.pid=pid;
        this.whitequantity=whitequantity;
        this.whiteprice=whiteprice;
        this.colorquantity=colorquantity;
        this.colorprice=colorprice;
        this.totalprice=totalprice;
        this.paid=paid;
        this.date=date;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getWhitequantity() {
        return whitequantity;
    }

    public void setWhitequantity(int whitequantity) {
        this.whitequantity = whitequantity;
    }

    public float getWhiteprice() {
        return whiteprice;
    }

    public void setWhiteprice(float whiteprice) {
        this.whiteprice = whiteprice;
    }

    public int getColorquantity() {
        return colorquantity;
    }

    public void setColorquantity(int colorquantity) {
        this.colorquantity = colorquantity;
    }

    public float getColorprice() {
        return colorprice;
    }

    public void setColorprice(float colorprice) {
        this.colorprice = colorprice;
    }

    public float getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(float totalprice) {
        this.totalprice = totalprice;
    }

    public float getPaid() {
        return paid;
    }

    public void setPaid(float paid) {
        this.paid = paid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
