package com.example.rumi.ecommerce.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rumi.ecommerce.R;
import com.example.rumi.ecommerce.constants.Url;
import com.example.rumi.ecommerce.interfaces.OnItemClickListener;
import com.example.rumi.ecommerce.model.Product;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductRecyclerViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.imageViewProduct)
    ImageView imageViewProduct;
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
        Picasso.get()
                .load(Url.BASE_URL + product.getImages().get(0).replace("public", ""))
                .placeholder(R.drawable.iphnx)
                .into(imageViewProduct);
//        Toast.makeText(textViewProductName.getContext(), product.getImages().get(0), Toast.LENGTH_LONG).show();

        itemView.setOnClickListener(v -> listener.onItemClick(product));
    }
}
