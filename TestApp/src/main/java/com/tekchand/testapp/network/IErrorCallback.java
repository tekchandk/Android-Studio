package com.tekchand.testapp.network;

import androidx.annotation.NonNull;

public interface IErrorCallback<E> {

    void onError(@NonNull final E errorCode);
}
