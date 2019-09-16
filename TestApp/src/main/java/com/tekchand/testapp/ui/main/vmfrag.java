package com.tekchand.testapp.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.tekchand.testapp.R;
import com.tekchand.testapp.ui.main.pojos.ItemsItem;
import com.tekchand.testapp.ui.main.pojos.Responses;

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

public class vmfrag extends Fragment {

    private VmfragViewModel mViewModel;
    private Gson gson = new Gson();
    private RecyclerView recyclerView;
    private OnVideoFragmentListener mListener;
    private static List<Video> videos = new ArrayList<>();


    public static vmfrag newInstance() {
        return new vmfrag();
    }

    private String apiKey = "AIzaSyCjmYJ7OKn0CQtxByFBxYkwGVU8jb7hV7k";
    private String url= "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=20&q=cricket&key="+ apiKey;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleApi);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
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
                            //textView.setText(str);
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



    public List<Video> videoList(){
        return videos;
    }

        @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.vmfrag_fragment, container, false);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(VmfragViewModel.class);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mListener.onVideoFragmentInteraction(recyclerView);

        // TODO: Use the ViewModel
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnVideoFragmentListener) {
            mListener = (OnVideoFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnVideoFragmentListener {
        // TODO: Update argument type and name
        void onVideoFragmentInteraction(RecyclerView recyclerView);
    }

}
