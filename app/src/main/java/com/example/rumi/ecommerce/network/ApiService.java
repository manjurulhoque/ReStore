package com.example.rumi.ecommerce.network;

import com.example.rumi.ecommerce.model.Category;
import com.example.rumi.ecommerce.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @GET("products")
    public Call<List<Product>> allProducts();

    @GET("categories/limits/{limit}")
    public Call<List<Category>> limitedCategories(@Path("limit") String limit);

    @GET("products/{category_id}/category")
    public Call<List<Product>> productsByCategory(@Path("category_id") String category_id);
}