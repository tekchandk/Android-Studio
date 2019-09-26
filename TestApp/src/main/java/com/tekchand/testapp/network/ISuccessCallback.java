package com.tekchand.testapp.network;

import androidx.annotation.NonNull;

public interface ISuccessCallback<T> {

    void onSuccess(@NonNull T succesObject);
}
