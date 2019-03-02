package com.example.rumi.ecommerce.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {

    //    @SerializedName("_id")
//    @Expose
    private String _id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("price")
    private String price;
    @SerializedName("available")
    private Boolean available;
    private List<String> images;

    public Product() {
    }

    public Product(String id, String name, String description, String price, Boolean available, List<String> images) {
        this.images = images;
        this._id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = available;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
