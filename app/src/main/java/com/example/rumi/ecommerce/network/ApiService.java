package com.example.rumi.ecommerce.network;

import com.example.rumi.ecommerce.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {

    @GET("products")
    public Call<List<Product>> allProducts();
}
