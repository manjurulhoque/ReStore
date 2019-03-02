package com.example.rumi.ecommerce.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rumi.ecommerce.R;
import com.example.rumi.ecommerce.interfaces.OnItemClickListener;
import com.example.rumi.ecommerce.model.Product;
import com.example.rumi.ecommerce.viewholder.ProductRecyclerViewHolder;

import java.util.List;

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewHolder> {

    private List<Product> mProducts;
    private Context mContext;
    private final OnItemClickListener listener;

    public ProductRecyclerViewAdapter(Context context, List<Product> products, OnItemClickListener listener) {
        this.mContext = context;
        this.mProducts = products;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_product_single, parent, false);
        return new ProductRecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRecyclerViewHolder holder, int position) {
        holder.bind(mProducts.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }
}
