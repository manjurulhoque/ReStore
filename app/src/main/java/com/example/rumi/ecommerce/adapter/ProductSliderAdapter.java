package com.example.rumi.ecommerce.adapter;

import android.widget.ImageView;

import com.example.rumi.ecommerce.constants.Url;

import java.util.List;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class ProductSliderAdapter extends SliderAdapter {

    private List<String> images;

    public ProductSliderAdapter(List<String> images) {
        this.images = images;
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder imageSlideViewHolder) {
        imageSlideViewHolder.bindImageSlide(Url.BASE_URL + images.get(position).replace("public", ""));
        imageSlideViewHolder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//        switch (position) {
//            case 0:
//                imageSlideViewHolder.bindImageSlide("https://assets.materialup.com/uploads/dcc07ea4-845a-463b-b5f0-4696574da5ed/preview.jpg");
//                break;
//            case 1:
//                imageSlideViewHolder.bindImageSlide("https://assets.materialup.com/uploads/20ded50d-cc85-4e72-9ce3-452671cf7a6d/preview.jpg");
//                break;
//            case 2:
//                imageSlideViewHolder.bindImageSlide("https://assets.materialup.com/uploads/76d63bbc-54a1-450a-a462-d90056be881b/preview.png");
//                break;
//        }
    }
}
