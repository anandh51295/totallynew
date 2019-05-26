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

    @SerializedName("cottonmatquantity")
    int cottonmatquantity;
    @SerializedName("cottomnatprice")
    float cottonmatprice;
    @SerializedName("cottonmattotalprice")
    float cottonmattotalprice;
    @SerializedName("colormatquantity")
    int colormatquantity;
    @SerializedName("colormatprice")
    float colormatprice;
    @SerializedName("colormattotalprice")
    float colormattotalprice;
    @SerializedName("description")
    String description;
    @SerializedName("entryprice1")
    float entryprice1;
    @SerializedName("entryprice2")
    float entryprice2;
    @SerializedName("totalcottonprice")
    float totalcottonprice;
    @SerializedName("totalwhiteprice")
    float totalwhiteprice;


    public NoolModel(int id, int pid, int whitequantity, float whiteprice, int colorquantity, float colorprice, float totalprice, float paid,String date,int cottonmatquantity,float cottonmatprice,float cottonmattotalprice,int colormatquantity,float colormatprice,float colormattotalprice,String description,float entryprice1,float entryprice2,float totalcottonprice,float totalwhiteprice){
        this.id=id;
        this.pid=pid;
        this.whitequantity=whitequantity;
        this.whiteprice=whiteprice;
        this.colorquantity=colorquantity;
        this.colorprice=colorprice;
        this.totalprice=totalprice;
        this.paid=paid;
        this.date=date;
        this.cottonmatquantity=cottonmatquantity;
        this.cottonmatprice=cottonmatprice;
        this.cottonmattotalprice=cottonmattotalprice;
        this.colormatquantity=colormatquantity;
        this.colormatprice=colormatprice;
        this.colormattotalprice=colormattotalprice;
        this.description=description;
        this.entryprice1=entryprice1;
        this.entryprice2=entryprice2;
        this.totalcottonprice=totalcottonprice;
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


    public int getCottonmatquantity() {
        return cottonmatquantity;
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

    public int getColormatquantity() {
        return colormatquantity;
    }

    public void setColormatquantity(int colormatquantity) {
        this.colormatquantity = colormatquantity;
    }

    public float getColormatprice() {
        return colormatprice;
    }

    public void setColormatprice(float colormatprice) {
        this.colormatprice = colormatprice;
    }

    public float getColormattotalprice() {
        return colormattotalprice;
    }

    public void setColormattotalprice(float colormattotalprice) {
        this.colormattotalprice = colormattotalprice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getEntryprice1() {
        return entryprice1;
    }

    public void setEntryprice1(float entryprice1) {
        this.entryprice1 = entryprice1;
    }

    public float getEntryprice2() {
        return entryprice2;
    }

    public void setEntryprice2(float entryprice2) {
        this.entryprice2 = entryprice2;
    }

    public float getTotalcottonprice() {
        return totalcottonprice;
    }

    public void setTotalcottonprice(float totalcottonprice) {
        this.totalcottonprice = totalcottonprice;
    }

    public float getTotalwhiteprice() {
        return totalwhiteprice;
    }

    public void setTotalwhiteprice(float totalwhiteprice) {
        this.totalwhiteprice = totalwhiteprice;
    }
}
