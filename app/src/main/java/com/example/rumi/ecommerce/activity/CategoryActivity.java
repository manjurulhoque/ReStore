package com.example.rumi.ecommerce.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.rumi.ecommerce.R;
import com.example.rumi.ecommerce.adapter.CategoryRecyclerViewAdapter;
import com.example.rumi.ecommerce.interfaces.OnCategoryItemClickListener;
import com.example.rumi.ecommerce.model.Category;
import com.example.rumi.ecommerce.network.ApiService;
import com.example.rumi.ecommerce.network.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {

    private static final String TAG = "CategoryActivity";

    @BindView(R.id.recyclerViewCategories)
    RecyclerView recyclerViewCategories;

    ApiService apiService;
    Call<List<Category>> categoriesCall;
    private List<Category> categories = new ArrayList<>();
    private ProgressDialog mProgressDialog;
    private CategoryRecyclerViewAdapter categoryRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);

        mProgressDialog = new ProgressDialog(this);
        // initialize our builder
        apiService = RetrofitBuilder.createService(ApiService.class);
        loadCategories();

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerViewCategories.setLayoutManager(mLayoutManager);
        categoryRecyclerViewAdapter = new CategoryRecyclerViewAdapter(categories, getApplicationContext(), new OnCategoryItemClickListener() {
            @Override
            public void onItemClick(Category category) {
//                finish();
                Intent intent = new Intent(getApplicationContext(), ProductsByCategoryActivity.class);
                intent.putExtra("category", category.getName());
                intent.putExtra("category_id", category.getId());
                startActivity(intent);
            }
        });
        recyclerViewCategories.setAdapter(categoryRecyclerViewAdapter);
    }

    private void loadCategories() {
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();

        categoriesCall = apiService.allCategories();
        categoriesCall.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                Log.d(TAG, "onResponse: " + response.body());
                if (response.isSuccessful() && response.code() == 200) {
                    categories.addAll(response.body());
                    categoryRecyclerViewAdapter.notifyDataSetChanged();
                    mProgressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
