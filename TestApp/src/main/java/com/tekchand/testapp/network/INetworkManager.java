package com.tekchand.testapp.network;

import androidx.annotation.NonNull;

import com.tekchand.testapp.ui.main.tab3.Video;

public interface INetworkManager {

    void requestVideo(@NonNull String url,
                      @NonNull ISuccessCallback<Video> successCallback,
                      @NonNull IErrorCallback<Integer> errorCallback);
}
