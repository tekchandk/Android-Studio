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

    public VideoRecyclerAdapter(@NonNull final Context context, @NonNull final List<Video> videos){
        this.videos = videos;
        this.context = context;
    }

    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_items,parent, false);
        SimpleViewHolder viewHolder = new SimpleViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleViewHolder holder, final int position) {
        Glide.with(context).load(videos.get(position).getUrl()).into(holder.image);
        holder.dataBind(videos, position);
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView publish;
        private TextView title;
        private TextView desc;
        private SimpleViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageView);
            publish = itemView.findViewById(R.id.publish);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
        }

        private void dataBind(List<Video> videos, int position) {
            publish.setText(videos.get(position).getPublished());
            title.setText(videos.get(position).getTitle());
            desc.setText(videos.get(position).getDescription());
        }

    }
}
