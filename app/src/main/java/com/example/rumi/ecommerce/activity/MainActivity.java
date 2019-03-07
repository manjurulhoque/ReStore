package com.example.rumi.ecommerce.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rumi.ecommerce.R;
import com.example.rumi.ecommerce.adapter.ProductRecyclerViewAdapter;
import com.example.rumi.ecommerce.dbhelper.CartDbHelper;
import com.example.rumi.ecommerce.interfaces.OnItemClickListener;
import com.example.rumi.ecommerce.model.Category;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private Unbinder unbinder;
    private List<Product> products = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();
    private ProgressDialog mProgressDialog;
    private ProductRecyclerViewAdapter productRecyclerViewAdapter;
    //    @BindView(R.id.textViewNotingFound)
//    TextView textViewNotingFound;
    @BindView(R.id.textViewCategoryOne)
    TextView textViewCategoryOne;
    @BindView(R.id.textViewCategoryTwo)
    TextView textViewCategoryTwo;
    @BindView(R.id.textViewCategoryThree)
    TextView textViewCategoryThree;
    @BindView(R.id.textViewCategoryFour)
    TextView textViewCategoryFour;
    @BindView(R.id.textViewCategoryOneHidden)
    TextView textViewCategoryOneHidden;
    @BindView(R.id.textViewCategoryTwoHidden)
    TextView textViewCategoryTwoHidden;
    @BindView(R.id.textViewCategoryThreeHidden)
    TextView textViewCategoryThreeHidden;
    @BindView(R.id.textViewCategoryFourHidden)
    TextView textViewCategoryFourHidden;
    @BindView(R.id.textViewShowAllCategory)
    TextView textViewShowAllCategory;
    @BindView(R.id.textViewTotalCart)
    TextView textViewTotalCart;
    @BindView(R.id.imageViewCart)
    ImageView imageViewCart;

    ApiService apiService;
    Call<List<Product>> productsCall;
    Call<List<Category>> categoriesCall;
    RecyclerView productsRecyclerView;
    CartDbHelper cartDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        cartDbHelper = new CartDbHelper(this);

        mProgressDialog = new ProgressDialog(this);
        textViewTotalCart.setText(String.valueOf(cartDbHelper.getCartCount()));
        // initialize our builder
        apiService = RetrofitBuilder.createService(ApiService.class);
        loadProducts();

        productsRecyclerView = findViewById(R.id.recyclerViewProducts);


        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        productsRecyclerView.setLayoutManager(mLayoutManager);
        productRecyclerViewAdapter = new ProductRecyclerViewAdapter(getApplicationContext(), products, new OnItemClickListener() {
            @Override
            public void onItemClick(Product product) {
                Intent intent = new Intent(getApplicationContext(), ProductDetailsActivity.class);
                intent.putExtra("product", product);
                startActivity(intent);
            }
        });
        productsRecyclerView.setAdapter(productRecyclerViewAdapter);

        textViewCategoryOne.setOnClickListener(this);
        textViewCategoryTwo.setOnClickListener(this);
        textViewCategoryThree.setOnClickListener(this);
        textViewCategoryFour.setOnClickListener(this);

        textViewShowAllCategory.setOnClickListener(this);
        imageViewCart.setOnClickListener(this);
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
                Log.d(TAG, "onResponse: " + response.body().get(0).getName());
                if (response.isSuccessful() && response.code() == 200) {
                    products.addAll(response.body());
                    productRecyclerViewAdapter.notifyDataSetChanged();
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

        categoriesCall = apiService.limitedCategories("4");
        categoriesCall.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                Log.d(TAG, "onResponse: " + response.body());
                if (response.isSuccessful() && response.code() == 200) {
                    categories.addAll(response.body());
                    textViewCategoryOne.setText(categories.get(0).getName());
                    textViewCategoryOneHidden.setText(categories.get(0).getId());
                    textViewCategoryTwo.setText(categories.get(1).getName());
                    textViewCategoryTwoHidden.setText(categories.get(1).getId());
                    textViewCategoryThree.setText(categories.get(2).getName());
                    textViewCategoryThreeHidden.setText(categories.get(2).getId());
                    textViewCategoryFourHidden.setText(categories.get(3).getId());
                    textViewCategoryFour.setText(categories.get(3).getName());
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
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

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(getApplicationContext(), ProductsByCategoryActivity.class);

        switch (view.getId()) {
            case R.id.imageViewCart:
                startActivity(new Intent(getApplicationContext(), CartActivity.class));
                break;
            case R.id.textViewCategoryOne:
                intent.putExtra("category", textViewCategoryOne.getText());
                intent.putExtra("category_id", textViewCategoryOneHidden.getText());
                startActivity(intent);
                break;
            case R.id.textViewCategoryTwo:
                intent.putExtra("category", textViewCategoryTwo.getText());
                intent.putExtra("category_id", textViewCategoryTwoHidden.getText());
                startActivity(intent);
                break;
            case R.id.textViewCategoryThree:
                intent.putExtra("category", textViewCategoryThree.getText());
                intent.putExtra("category_id", textViewCategoryThreeHidden.getText());
                startActivity(intent);
                break;
            case R.id.textViewCategoryFour:
                intent.putExtra("category", textViewCategoryOne.getText());
                intent.putExtra("category_id", textViewCategoryFourHidden.getText());
                startActivity(intent);
                break;
            case R.id.textViewShowAllCategory:
                startActivity(new Intent(getApplicationContext(), CategoryActivity.class));
                break;
        }
    }
}
