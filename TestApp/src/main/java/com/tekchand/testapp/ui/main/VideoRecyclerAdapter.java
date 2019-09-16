package com.tekchand.testapp.ui.main;

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

    public VideoRecyclerAdapter(Context context, List<Video> videos){
        this.videos = videos;
        this.context = context;
    }

    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.video_items,parent, false);
        SimpleViewHolder viewHolder = new SimpleViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull SimpleViewHolder holder, int position) {
        Glide.with(context).load(videos.get(position).getUrl()).into(holder.image);
        holder.publish.setText(videos.get(position).getPublished());
        holder.title.setText(videos.get(position).getTitle());
        holder.desc.setText(videos.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder{
        public ImageView image;
        public TextView publish;
        public TextView title;
        public TextView desc;
        public SimpleViewHolder(View itemView) {

            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.imageView);
            publish = (TextView)itemView.findViewById(R.id.publish);
            title = (TextView)itemView.findViewById(R.id.title);
            desc = (TextView)itemView.findViewById(R.id.desc);

        }

    }

}
