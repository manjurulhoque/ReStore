package com.example.rumi.ecommerce.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rumi.ecommerce.R;
import com.example.rumi.ecommerce.adapter.CartRecyclerViewAdapter;
import com.example.rumi.ecommerce.dbhelper.CartDbHelper;
import com.example.rumi.ecommerce.model.Cart;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartActivity extends AppCompatActivity {


    @BindView(R.id.textViewCheckout)
    TextView textViewCheckout;
    @BindView(R.id.textViewTotal)
    TextView textViewTotal;
    @BindView(R.id.recyclerViewCarts)
    RecyclerView recyclerViewCarts;

    CartRecyclerViewAdapter cartRecyclerViewAdapter;
    List<Cart> carts = new ArrayList<Cart>();
    ProgressDialog mProgressDialog;
    CartDbHelper cartDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);

        cartDbHelper = new CartDbHelper(this);
        mProgressDialog = new ProgressDialog(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewCarts.setLayoutManager(mLayoutManager);
        cartRecyclerViewAdapter = new CartRecyclerViewAdapter(carts, this);
        loadCarts();

        textViewTotal.setText(String.valueOf("Tk. " + cartDbHelper.getCartTotal()));

        recyclerViewCarts.setAdapter(cartRecyclerViewAdapter);

        textViewCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Checkout done!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadCarts() {
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();

        carts.addAll(cartDbHelper.getAll());
        cartRecyclerViewAdapter.notifyDataSetChanged();
        mProgressDialog.dismiss();
    }
}
