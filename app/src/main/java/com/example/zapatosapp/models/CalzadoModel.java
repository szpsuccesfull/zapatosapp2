package com.example.zapatosapp.models;

import java.io.Serializable;

public class CalzadoModel implements Serializable {
    private int _id;
    private String size;
    private String brand;
    private String fbId;

    public CalzadoModel() {
    }

    public CalzadoModel(String size, String brand) {
        this.size = size;
        this.brand = brand;
    }

    public CalzadoModel(String size, String brand, String fbId) {
        this.size = size;
        this.brand = brand;
        this.fbId = fbId;
    }

    public CalzadoModel(int _id, String size, String brand) {
        this._id = _id;
        this.size = size;
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "CalzadoModel{" +
                "_id=" + _id +
                ", size='" + size + '\'' +
                ", brand='" + brand + '\'' +
                ", fbId='" + fbId + '\'' +
                '}';
    }

    public int get_id() {
        return this._id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }
}
