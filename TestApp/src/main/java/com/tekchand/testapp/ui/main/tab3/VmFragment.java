package com.tekchand.testapp.ui.main.tab3;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.tekchand.testapp.R;
import com.tekchand.testapp.network.INetworkManager;
import com.tekchand.testapp.network.impl.NetworkManager;

import java.util.Objects;

import static com.tekchand.testapp.ui.main.tab3.PaginationScrollListener.videos;

/**
 * @author Tek Chand
 * This fragment shows a list of youtube video's API data
 */
@MainThread
public class VmFragment extends Fragment implements PaginationScrollListener.PaginationListener {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private CallbackInterface mListener;
    private VideoRecyclerAdapter videoAdapter;
    private LinearLayoutManager layoutManager;
    private PaginationScrollListener paginationScrollListener;

    @Nullable
    private INetworkManager mNetworkManager;

    /**
     * Create a new instance of fragment
     * @return a new instance of fragment
     */
    public static VmFragment newInstance() {
        VmFragment vmFragment = new VmFragment();
        vmFragment.mNetworkManager = new NetworkManager(new Gson());
        return vmFragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleApi);
        progressBar = view.findViewById(R.id.progress_bar);
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
        layoutManager = new LinearLayoutManager(getContext());
        videoAdapter = new VideoRecyclerAdapter(Objects.requireNonNull(getContext()), videos);
        paginationScrollListener = new PaginationScrollListener(layoutManager, mNetworkManager, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(videoAdapter);
        recyclerView.addOnScrollListener(paginationScrollListener);
        paginationScrollListener.initiateRequestVideos();
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

    @Override
    public void onSuccess(Video video) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(()-> {
                    videos.add(video);
                    videoAdapter.notifyDataSetChanged();
            });
            toggleProgressBar(false);
        }
    }

    @Override
    public void onError(int errorCode) {
        toggleProgressBar(false);
    }

    @Override
    public void toggleProgressBar(boolean toggle) {
        if(getActivity() != null)
        {
            getActivity().runOnUiThread(()-> progressBar.setVisibility(toggle ? View.VISIBLE : View.GONE));
        }
    }


    public interface CallbackInterface {

    }

}
