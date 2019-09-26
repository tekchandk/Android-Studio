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
import com.tekchand.testapp.network.IErrorCallback;
import com.tekchand.testapp.network.INetworkManager;
import com.tekchand.testapp.network.ISuccessCallback;
import com.tekchand.testapp.network.impl.NetworkManager;

import java.util.ArrayList;
import java.util.List;

import static com.tekchand.testapp.constant.Constants.API_URL;

/**
 * @author Tek Chand
 * This fragment show a list of youtube video's API data
 */
public class VmFragment extends Fragment {

    private RecyclerView recyclerView;
    private CallbackInterface mListener;
    private List<Video> videos = new ArrayList<>();
    private RecyclerView.Adapter videoAdapter;

    @Nullable
    private INetworkManager mNetworkManager;

    public VmFragment() {

    }

    public static VmFragment newInstance() {
        VmFragment vmFragment = new VmFragment();
        vmFragment.mNetworkManager = new NetworkManager(new Gson());
        return vmFragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleApi);
        requestVideos();
    }

    private void requestVideos() {
        if (mNetworkManager != null) {
            mNetworkManager.requestVideo(API_URL, new ISuccessCallback<Video>() {
                @Override
                public void onSuccess(@NonNull Video succesObject) {
                    videos.add(succesObject);
                }
            }, new IErrorCallback<Integer>() {
                @Override
                public void onError(@NonNull Integer errorCode) {
                }
            });
        }
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
