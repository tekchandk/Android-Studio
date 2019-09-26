package com.tekchand.testapp.network;

public interface INetworkRequest<T, E> {

    void onSuccess(T successObject);

    void onError(E errorCode);

    void requestFromAPI();
}
