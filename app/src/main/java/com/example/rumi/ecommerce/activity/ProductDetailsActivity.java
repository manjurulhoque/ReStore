package com.example.rumi.ecommerce.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rumi.ecommerce.R;
import com.example.rumi.ecommerce.adapter.ProductSliderAdapter;
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

    private Product product;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);

        product = (Product) getIntent().getExtras().getSerializable("product");
        product_slider.setAdapter(new ProductSliderAdapter(product.getImages()));
        Toast.makeText(this, product.getName(), Toast.LENGTH_LONG).show();
        setProduct();
    }

    private void setProduct() {
        Slider.init(new PicassoImageLoadingService(this));
        textViewProductName.setText(product.getName());
        textViewPrice.setText("Tk: " + product.getPrice());
    }
}
