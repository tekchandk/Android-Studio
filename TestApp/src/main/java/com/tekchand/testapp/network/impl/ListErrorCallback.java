package com.tekchand.testapp.network.impl;

import androidx.annotation.NonNull;

import com.tekchand.testapp.network.IErrorCallback;

import java.util.List;

public class ListErrorCallback<T> implements IErrorCallback<List<T>> {
    @Override
    public void onError(@NonNull List<T> errorCode) {

    }
}
