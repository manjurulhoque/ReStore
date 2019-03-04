package com.example.rumi.ecommerce.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rumi.ecommerce.R;
import com.example.rumi.ecommerce.constants.Url;
import com.example.rumi.ecommerce.interfaces.OnItemClickListener;
import com.example.rumi.ecommerce.model.Product;
import com.example.rumi.ecommerce.viewholder.ProductRecyclerViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsByCategoryRecyclerViewAdapter extends RecyclerView.Adapter<ProductByCategoryRecyclerViewHolder> {

    private List<Product> mProducts;
    private Context mContext;
    private final OnItemClickListener listener;

    public ProductsByCategoryRecyclerViewAdapter(Context context, List<Product> products, OnItemClickListener listener) {
        this.mContext = context;
        this.mProducts = products;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductByCategoryRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_product_category_single, parent, false);
        return new ProductByCategoryRecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductByCategoryRecyclerViewHolder holder, int position) {
        holder.bind(mProducts.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }
}

class ProductByCategoryRecyclerViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.imageViewProduct)
    ImageView imageViewProduct;
    @BindView(R.id.textViewProductName)
    TextView textViewProductName;
    @BindView(R.id.textViewPrice)
    TextView textViewPrice;
    @BindView(R.id.textViewCategory)
    TextView textViewCategory;

    public ProductByCategoryRecyclerViewHolder(View itemView) {
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

        itemView.setOnClickListener(v -> listener.onItemClick(product));
    }
}
