package com.example.rumi.ecommerce.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rumi.ecommerce.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductRecyclerViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageViewProduct;
    @BindView(R.id.textViewProductName)
    TextView textViewProductName;
    @BindView(R.id.textViewPrice)
    TextView textViewPrice;

    public ProductRecyclerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
