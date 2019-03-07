package com.example.rumi.ecommerce.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rumi.ecommerce.R;
import com.example.rumi.ecommerce.adapter.ProductSliderAdapter;
import com.example.rumi.ecommerce.dbhelper.CartDbHelper;
import com.example.rumi.ecommerce.model.Product;
import com.example.rumi.ecommerce.services.PicassoImageLoadingService;

import butterknife.BindView;
import butterknife.ButterKnife;
import ss.com.bannerslider.Slider;

public class ProductDetailsActivity extends AppCompatActivity {

    @BindView(R.id.textViewProductName)
    TextView textViewProductName;
    @BindView(R.id.textViewPrice)
    TextView textViewPrice;
    @BindView(R.id.product_slider)
    Slider product_slider;
    @BindView(R.id.buttonAddToCart)
    Button buttonAddToCart;
    @BindView(R.id.imageViewCart)
    ImageView imageViewCart;
    @BindView(R.id.textViewTotalCart)
    TextView textViewTotalCart;

    private Product product;
    private CartDbHelper cartDbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);

        cartDbHelper = new CartDbHelper(this);

        textViewTotalCart.setText(String.valueOf(cartDbHelper.getCartCount()));

        product = (Product) getIntent().getExtras().getSerializable("product");
        product_slider.setAdapter(new ProductSliderAdapter(product.getImages()));
        setProduct();

        buttonAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cartDbHelper.addData(product.getName(), Double.valueOf(product.getPrice()), 1)) {
                    Toast.makeText(getApplicationContext(), "Added to cart", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Something wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imageViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CartActivity.class));
            }
        });
    }

    private void setProduct() {
        Slider.init(new PicassoImageLoadingService(this));
        textViewProductName.setText(product.getName());
        textViewPrice.setText("Tk: " + product.getPrice());
    }
}
