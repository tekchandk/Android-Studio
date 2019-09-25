package com.tekchand.testapp.ui.main.tab3;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.tekchand.testapp.R;
import com.tekchand.testapp.ui.main.models.ItemsItem;
import com.tekchand.testapp.ui.main.models.Responses;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.tekchand.testapp.constant.Constants.API_URL;

/**
 * @author Tek Chand
 * This fragment show a list of youtube video's API data
 */
public class vmfrag extends Fragment {

    private Gson gson = new Gson();
    private RecyclerView recyclerView;
    private CallbackInterface mListener;
    private List<Video> videos = new ArrayList<>();
    private RecyclerView.Adapter videoAdapter;


    public static vmfrag newInstance() {
        return new vmfrag();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleApi);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_URL)
                .build();
        client.newCall(request).enqueue(new Callback(){
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       // textView.setText("Failure !");
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String str = response.body().string();
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(str);
                                Responses responses= gson.fromJson(jsonObject.toString(), Responses.class);
                                List<ItemsItem> items = responses.getItems();
                                for(ItemsItem item : items){
                                    String title = item.getSnippet().getTitle();
                                    String publish = item.getSnippet().getPublishedAt();
                                   String desc = item.getSnippet().getDescription();
                                    String url = item.getSnippet().getThumbnails().getHigh().getUrl();
                                    Video video = new Video(publish, title, desc, url);
                                    videos.add(video);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } catch (IOException ioe) {
                           ioe.printStackTrace();
                        }
                    }
                });
            }
        });
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.vmfrag_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        videoAdapter = new VideoRecyclerAdapter(getContext(), videos);
        recyclerView.setAdapter(videoAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CallbackInterface) {
            mListener = (CallbackInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement CallbackInterface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface CallbackInterface {

    }

}
