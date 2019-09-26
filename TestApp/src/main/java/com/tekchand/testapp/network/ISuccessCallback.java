package com.tekchand.testapp.network;

import androidx.annotation.Nullable;

public interface ISuccessCallback<T> {

    void onSuccess(@Nullable T succesObject);
}
