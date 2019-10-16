package com.tekchand.testapp.cropfertilizer.data;

import androidx.annotation.NonNull;

import java.util.List;

public class ListItem {

    @NonNull
    private String mTitle;

    @NonNull
    private List<ItemCard> mItems;

    public ListItem(@NonNull String title, @NonNull List<ItemCard> items) {
        mTitle = title;
        mItems = items;
    }

    @NonNull
    public String getTitle() {
        return mTitle;
    }

    @NonNull
    public List<ItemCard> getItems() {
        return mItems;
    }

    public void setTitle(@NonNull String title) {
        mTitle = title;
    }

    public void setItems(@NonNull List<ItemCard> items) {
        mItems = items;
    }
}
