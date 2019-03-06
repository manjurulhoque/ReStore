package com.example.rumi.ecommerce.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rumi.ecommerce.R;
import com.example.rumi.ecommerce.interfaces.OnCategoryItemClickListener;
import com.example.rumi.ecommerce.model.Category;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private List<Category> categories;
    private Context context;
    private final OnCategoryItemClickListener listener;

    public CategoryRecyclerViewAdapter(List<Category> categories, Context context, OnCategoryItemClickListener listener) {
        this.categories = categories;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_category_single, parent, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.bind(categories.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}

class CategoryViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.textViewCategoryName)
    TextView textViewCategoryName;

    public CategoryViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final Category category, final OnCategoryItemClickListener listener) {

        textViewCategoryName.setText(category.getName());
//        Picasso.get()
//                .load(Url.BASE_URL + product.getImages().get(0).replace("public", ""))
//                .placeholder(R.drawable.iphnx)
//                .into(imageViewProduct);

        itemView.setOnClickListener(v -> listener.onItemClick(category));
    }
}
