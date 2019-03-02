package com.example.rumi.ecommerce.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rumi.ecommerce.R;
import com.example.rumi.ecommerce.interfaces.OnItemClickListener;
import com.example.rumi.ecommerce.model.Product;

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

    public void bind(final Product product, final OnItemClickListener listener) {

        textViewProductName.setText(product.getName());
        textViewPrice.setText(product.getPrice());
//        Toast.makeText(textViewProductName.getContext(), product.getId(), Toast.LENGTH_LONG).show();

        itemView.setOnClickListener(v -> listener.onItemClick(product));
    }
}
