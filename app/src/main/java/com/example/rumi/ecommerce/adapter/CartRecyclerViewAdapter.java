package com.example.rumi.ecommerce.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rumi.ecommerce.R;
import com.example.rumi.ecommerce.model.Cart;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartRecyclerViewAdapter extends RecyclerView.Adapter<CartViewHolder> {


    private List<Cart> carts;
    private Context mContext;

    public CartRecyclerViewAdapter(List<Cart> carts, Context mContext) {
        this.carts = carts;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layour_cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.bind(carts.get(position));
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }
}

class CartViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.textViewProductName)
    TextView textViewProductName;
    @BindView(R.id.textViewPrice)
    TextView textViewPrice;


    public CartViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final Cart cart) {
        textViewProductName.setText(cart.getName());
        textViewPrice.setText(String.valueOf("Tk." + cart.getPrice()));
    }
}
