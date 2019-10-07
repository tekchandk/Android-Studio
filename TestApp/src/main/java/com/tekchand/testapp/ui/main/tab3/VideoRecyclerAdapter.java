package com.tekchand.testapp.ui.main.tab3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tekchand.testapp.R;

import java.util.List;

public class VideoRecyclerAdapter extends RecyclerView.Adapter<VideoRecyclerAdapter.SimpleViewHolder>{
    private List<Video> videos;
    private Context context;
    private String nextPageToken;
    // flag for footer ProgressBar (i.e. last item of list)


    public VideoRecyclerAdapter(@NonNull final Context context, @NonNull final List<Video> videos){
        this.videos = videos;
        this.context = context;

    }

    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_items,parent, false);
        return new SimpleViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleViewHolder holder, final int position) {
        Video video = videos.get(position);
        holder.dataBind(video);
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public Video getLastItem() {
        return videos.get(getItemCount()-1);
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder{
        private final ImageView mImage;
        private final TextView mPublish;
        private final TextView mTitle;
        private final TextView mDesc;
        private final Context mContext;
        private SimpleViewHolder(@NonNull final View itemView,
                                 @NonNull final Context context) {
            super(itemView);
            this.mContext = context;
            mImage = itemView.findViewById(R.id.imageView);
            mPublish = itemView.findViewById(R.id.publish);
            mTitle = itemView.findViewById(R.id.title);
            mDesc = itemView.findViewById(R.id.desc);
        }

        private void dataBind(@NonNull final Video video) {
            Glide.with(mContext).load(video.getUrl()).into(mImage);
            mPublish.setText(video.getPublished());
            mTitle.setText(video.getTitle());
            mDesc.setText(video.getDescription());
        }

    }
}
