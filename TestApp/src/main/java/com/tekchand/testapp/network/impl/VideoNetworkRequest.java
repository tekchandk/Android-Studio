package com.tekchand.testapp.network.impl;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.tekchand.testapp.network.IErrorCallback;
import com.tekchand.testapp.network.INetworkRequest;
import com.tekchand.testapp.network.ISuccessCallback;
import com.tekchand.testapp.ui.main.models.ItemsItem;
import com.tekchand.testapp.ui.main.models.Responses;
import com.tekchand.testapp.ui.main.tab3.Video;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class VideoNetworkRequest implements INetworkRequest<Video, Integer> {

    @NonNull
    private String mUrl;
    @NonNull
    private Gson mGson;
    @NonNull
    private ISuccessCallback<Video> mSuccessCallback;
    @NonNull
    private IErrorCallback<Integer> mErrorCallback;

    public VideoNetworkRequest(@NonNull final String url, @NonNull final Gson gson,
                               @NonNull final ISuccessCallback<Video> mSuccessCallback,
                               @NonNull final IErrorCallback<Integer> mErrorCallback) {
        this.mUrl = url;
        this.mGson = gson;
        this.mSuccessCallback = mSuccessCallback;
        this.mErrorCallback = mErrorCallback;
    }

    @Override
    public void onSuccess(Video successObject) {
        mSuccessCallback.onSuccess(successObject);
    }

    @Override
    public void onError(Integer errorCode) {
        mErrorCallback.onError(errorCode);
    }

    @Override
    public void requestFromAPI() {
        // Talk to server using mUrl and fetch data.
        // If succeeds, call onSuccess with relevant data.
        // If fails, call onError with relevant data.
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(mUrl)
                .build();
        client.newCall(request).enqueue(new Callback(){
            @Override
            public void onFailure(@NonNull final Call call,
                                  @NonNull final IOException e) {
                onError(200);
            }

            @Override
            public void onResponse(@NonNull final Call call,
                                   @NonNull final Response response) {
                try {
                    ResponseBody body = response.body();
                    if (body != null && response.code() == 200) {
                        String str = body.string();
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(str);
                            Responses responses = mGson.fromJson(jsonObject.toString(), Responses.class);

                            List<ItemsItem> items = responses.getItems();
                            if(items != null) {
                                String nextPageToken = responses.getNextPageToken();
                                String prevPageToken = responses.getPrevPageToken();
                                for (ItemsItem item : items) {
                                    String title = item.getSnippet().getTitle();
                                    String publish = item.getSnippet().getPublishedAt();
                                    String desc = item.getSnippet().getDescription();
                                    String url = item.getSnippet().getThumbnails().getHigh().getUrl();
                                    Video video = new Video(publish, title, desc, url, nextPageToken, prevPageToken);
                                    onSuccess(video);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        onError(response.code());
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        });
    }
}
