package com.example.rumi.ecommerce.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rumi.ecommerce.R;
import com.example.rumi.ecommerce.model.Product;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailsActivity extends AppCompatActivity {

    @BindView(R.id.textViewProductName)
    TextView textViewProductName;
    @BindView(R.id.textViewPrice)
    TextView textViewPrice;

    private Product product;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);

        product = (Product) getIntent().getExtras().getSerializable("product");
        Toast.makeText(this, product.getName(), Toast.LENGTH_LONG).show();
        setProduct();
    }

    private void setProduct() {
        textViewProductName.setText(product.getName());
        textViewPrice.setText("Tk: " + product.getPrice());
    }
}
