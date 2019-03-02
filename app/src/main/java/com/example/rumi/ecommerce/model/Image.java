package com.example.rumi.ecommerce.model;

public class Image {

    private String _id;
    private String name;

    public Image() {
    }

    public Image(String name, String _id) {
        this.name = name;
        this._id = _id;
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
