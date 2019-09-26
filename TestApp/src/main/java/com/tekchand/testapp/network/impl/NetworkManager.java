package com.tekchand.testapp.network.impl;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.tekchand.testapp.network.IErrorCallback;
import com.tekchand.testapp.network.INetworkManager;
import com.tekchand.testapp.network.INetworkRequest;
import com.tekchand.testapp.network.ISuccessCallback;
import com.tekchand.testapp.ui.main.tab3.Video;

public class NetworkManager implements INetworkManager {

    private Gson mGson;

    public NetworkManager(Gson mGson) {
        this.mGson = mGson;
    }

    @Override
    public void requestVideo(@NonNull final String url,
                             @NonNull final ISuccessCallback<Video> successCallback,
                             @NonNull final IErrorCallback<Integer> errorCallback) {

        INetworkRequest<Video, Integer> request = new VideoNetworkRequest(url, mGson, successCallback, errorCallback);

        request.requestFromAPI();

    }
}
