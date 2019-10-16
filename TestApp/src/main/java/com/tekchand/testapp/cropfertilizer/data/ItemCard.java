package com.tekchand.testapp.cropfertilizer.data;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

public class ItemCard {
    @NonNull
    @DrawableRes
    private int mImageRes;
    @NonNull
    private String mTitle;

    public ItemCard(@NonNull int imageRes, @NonNull String title) {
        mImageRes = imageRes;
        mTitle = title;
    }

    @NonNull
    public int getimageRes() {
        return mImageRes;
    }

    @NonNull
    public String getTitle() {
        return mTitle;
    }

    public void setImageRes(@NonNull int imageRes) {
        mImageRes = imageRes;
    }

    public void setTitle(@NonNull String title) {
        mTitle = title;
    }
}
