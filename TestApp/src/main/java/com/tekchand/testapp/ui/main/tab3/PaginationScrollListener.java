package com.tekchand.testapp.ui.main.tab3;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tekchand.testapp.network.IErrorCallback;
import com.tekchand.testapp.network.INetworkManager;
import com.tekchand.testapp.network.ISuccessCallback;

import java.util.ArrayList;
import java.util.List;

import static com.tekchand.testapp.constant.Constants.API_KEY;

public class PaginationScrollListener extends RecyclerView.OnScrollListener{
    private LinearLayoutManager layoutManager;
    private INetworkManager mNetworkManager;
    private PaginationListener mPaginationListener;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private final int START_PAGE = 1;
    private final int TOTAL_PAGE = 10000;
    private int currentPage = START_PAGE;
    private String presentPageToken = "CAoQAA";
    private String nextPageToken;
    public static List<Video> videos = new ArrayList<>();


    public PaginationScrollListener(LinearLayoutManager layoutManager,
                                    INetworkManager networkManager,
                                    PaginationListener paginationListener) {
        this.layoutManager = layoutManager;
        mNetworkManager = networkManager;
        mPaginationListener = paginationListener;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
        if (!isLoading() && !isLastPage()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount <= getTotalPages()) {
                RecyclerView.Adapter adapter = recyclerView.getAdapter();
                if (adapter instanceof VideoRecyclerAdapter) {
                    VideoRecyclerAdapter recyclerAdapter = ((VideoRecyclerAdapter) adapter);
                    Video lastItem = recyclerAdapter.getLastItem();
                    loadMoreItem(lastItem.getNextPageToken());
                }
            }
        }
    }

    public void initiateRequestVideos() {
        requestVideos(presentPageToken);
    }


    /** Request the video from the network manager using the given token.
     * @param pageToken There is a token for each page(10 videos list in each token)
     */
    public void requestVideos(String pageToken) {
        if (mNetworkManager != null) {
            String apiUrl ="https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=10&order=viewCount&pageToken="
                    + pageToken + "&q=skateboarding+dog&type=video&key=" + API_KEY;
            mNetworkManager.requestVideo(apiUrl, new ISuccessCallback<Video>() {
                    @Override
                    public void onSuccess(@NonNull Video succesObject) {
                        mPaginationListener.onSuccess(succesObject);
                    }
                }, new IErrorCallback<Integer>() {
                    @Override
                    public void onError(@NonNull Integer errorCode) {
                        mPaginationListener.onError(errorCode);
                    }
                });
        }
    }

    /**
     * load more items in the list
     * @param nextToken is a token for the next page loading.
     */
    private void loadMoreItem(String nextToken) {
        isLoading = true;
        currentPage++;
        //Load Next Page..........
        loadNextPage(nextToken);
    }

    /**
     * get the total pages of items (ten items in one page)
     * @return total no. of pages
     */
    private int getTotalPages() {
        return TOTAL_PAGE;
    }

    /**
     *
     * @return true if more data present
     */
    private boolean isLoading() {
        return isLoading;
    }

    /**
     *
     * @return true if the page is not last page otherwise false.
     */
    private boolean isLastPage() {
        return isLastPage;
    }

    /**
     *
     * @param nextToken
     */
    private void loadNextPage(String nextToken) {
        nextPageToken = nextToken;
        mPaginationListener.toggleProgressBar(true);
        requestVideos(nextPageToken);
        isLoading = false;
        if (currentPage == TOTAL_PAGE)
            isLastPage = true;
    }

    public interface PaginationListener {
        void onSuccess(Video video);
        void onError(int errorCode);
        void toggleProgressBar(boolean toggle);
    }

}