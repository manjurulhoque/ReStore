package com.example.rumi.ecommerce.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rumi.ecommerce.R;
import com.example.rumi.ecommerce.adapter.ProductRecyclerViewAdapter;
import com.example.rumi.ecommerce.adapter.ProductsByCategoryRecyclerViewAdapter;
import com.example.rumi.ecommerce.interfaces.OnItemClickListener;
import com.example.rumi.ecommerce.model.Product;
import com.example.rumi.ecommerce.network.ApiService;
import com.example.rumi.ecommerce.network.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsByCategoryActivity extends AppCompatActivity {

    private static final String TAG = "ProductsByCategory";

    String category_id = "", category = "";

    ApiService apiService;
    Call<List<Product>> productsCall;
    private Unbinder unbinder;
    private List<Product> products = new ArrayList<>();
    private ProgressDialog mProgressDialog;
    @BindView(R.id.recyclerViewProductsByCategory)
    RecyclerView productsRecyclerView;
    @BindView(R.id.textViewCategoryName)
    TextView textViewCategoryName;

    private ProductsByCategoryRecyclerViewAdapter productsByCategoryRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_by_category);
        ButterKnife.bind(this);

        category = getIntent().getExtras().getString("category");
        category_id = getIntent().getExtras().getString("category_id");

        textViewCategoryName.setText("Category: " + category);

        mProgressDialog = new ProgressDialog(this);
        // initialize our builder
        apiService = RetrofitBuilder.createService(ApiService.class);
        loadProducts();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        productsRecyclerView.setLayoutManager(mLayoutManager);
        productsByCategoryRecyclerViewAdapter = new ProductsByCategoryRecyclerViewAdapter(getApplicationContext(), products, new OnItemClickListener() {
            @Override
            public void onItemClick(Product product) {
                Intent intent = new Intent(getApplicationContext(), ProductDetailsActivity.class);
                intent.putExtra("product", product);
                startActivity(intent);
            }
        });

        productsRecyclerView.setAdapter(productsByCategoryRecyclerViewAdapter);
    }

    // load all products which is available
    private void loadProducts() {
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();

        productsCall = apiService.allProducts();
        productsCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                Toast.makeText(getApplicationContext(), response.body().get(0).getName(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onResponse: " + response.body().get(0).getName());
                if (response.isSuccessful() && response.code() == 200) {
                    products.addAll(response.body());
                    productsByCategoryRecyclerViewAdapter.notifyDataSetChanged();
                    mProgressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                mProgressDialog.dismiss();
                Log.d(TAG, "onFailure: " + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind(); // unbind when activity destroy
    }
}
