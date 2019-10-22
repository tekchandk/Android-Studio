package com.tekchand.testapp.cropfertilizer.data;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

import java.util.List;

public class ItemCard {
    @NonNull
    @DrawableRes
    private int mImageRes;
    @NonNull
    private String mTitle;

    @NonNull
    private List<String> mInfo;

    public ItemCard(@NonNull int imageRes, @NonNull String title, @NonNull List<String> info) {
        mImageRes = imageRes;
        mTitle = title;
        mInfo = info;
    }

    @NonNull
    public int getimageRes() {
        return mImageRes;
    }

    @NonNull
    public List<String> getInfo() {
        return mInfo;
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
    public void setInfo(@NonNull List<String> info) {
        this.mInfo = info;
    }
}
