package com.example.rumi.ecommerce.model;

import com.google.gson.annotations.SerializedName;

public class Category {

    private String _id;
    @SerializedName("name")
    private String name;

    public Category() {
    }

    public Category(String _id, String name) {
        this._id = _id;
        this.name = name;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
