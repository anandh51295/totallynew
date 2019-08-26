package com.intensivedatatech.totally.model;

import com.google.gson.annotations.SerializedName;

public class NoolModel {

    @SerializedName("id")
    int id;
    @SerializedName("p_id")
    int pid;
    @SerializedName("whitequantity")
    float whitequantity;
    @SerializedName("whiteprice")
    float whiteprice;

    @SerializedName("totalprice")
    float totalprice;
    @SerializedName("paid")
    float paid;
    @SerializedName("date")
    String date;

    @SerializedName("cottonmatquantity")
    float cottonmatquantity;
    @SerializedName("cottomnatprice")
    float cottonmatprice;
    @SerializedName("cottonmattotalprice")
    float cottonmattotalprice;


    @SerializedName("entryprice1")
    float entryprice1;


    @SerializedName("totalwhiteprice")
    float totalwhiteprice;


    public NoolModel(int id, int pid, float whitequantity, float whiteprice,  float totalprice, float paid,String date,float cottonmatquantity,float cottonmatprice,float cottonmattotalprice,float entryprice1,float totalwhiteprice){
        this.id=id;
        this.pid=pid;
        this.whitequantity=whitequantity;
        this.whiteprice=whiteprice;

        this.totalprice=totalprice;
        this.paid=paid;
        this.date=date;
        this.cottonmatquantity=cottonmatquantity;
        this.cottonmatprice=cottonmatprice;
        this.cottonmattotalprice=cottonmattotalprice;




        this.entryprice1=entryprice1;



        this.totalwhiteprice=totalwhiteprice;
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

    public float getWhitequantity() {
        return whitequantity;
    }

    public void setWhitequantity(float whitequantity) {
        this.whitequantity = whitequantity;
    }



    public float getCottonmatquantity() {
        return cottonmatquantity;
    }

    public void setCottonmatquantity(float cottonmatquantity) {
        this.cottonmatquantity = cottonmatquantity;
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




    public void setCottonmatquantity(int cottonmatquantity) {
        this.cottonmatquantity = cottonmatquantity;
    }

    public float getCottonmatprice() {
        return cottonmatprice;
    }

    public void setCottonmatprice(float cottonmatprice) {
        this.cottonmatprice = cottonmatprice;
    }

    public float getCottonmattotalprice() {
        return cottonmattotalprice;
    }

    public void setCottonmattotalprice(float cottonmattotalprice) {
        this.cottonmattotalprice = cottonmattotalprice;
    }










    public float getEntryprice1() {
        return entryprice1;
    }

    public void setEntryprice1(float entryprice1) {
        this.entryprice1 = entryprice1;
    }





    public float getTotalwhiteprice() {
        return totalwhiteprice;
    }

    public void setTotalwhiteprice(float totalwhiteprice) {
        this.totalwhiteprice = totalwhiteprice;
    }
}
