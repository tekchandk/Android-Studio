package com.tekchand.testapp.adapter.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.tekchand.testapp.R;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.SimpleViewHolder> {

    private String[] names;
    private int[] imageId;
    private OnClickListener listener;


    public interface OnClickListener {
        void onClick(String name);
        void onLongPress(String name);
    }

    public HomeAdapter(String[] names, int[] imageId, OnClickListener listener) {
        this.names = names;
        this.imageId = imageId;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item1, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleViewHolder holder, int position) {
        holder.dataBind(names, imageId, position, listener);
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private ImageView image;
        private CardView cardView;
        private SimpleViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_home);
            image = itemView.findViewById(R.id.image_home);
            cardView = itemView.findViewById(R.id.card_home);
        }

        private void dataBind(final String[] names, final int[] imagesId,  int position, final OnClickListener listener ) {
            name.setText(names[position]);
            image.setImageResource(imagesId[position]);
            cardView.setOnClickListener(v -> {
                listener.onClick(names[position]);
            });
            cardView.setOnLongClickListener(v -> {
                 listener.onLongPress(names[position]);
                 return false;
            });
        }
    }

    @Override
    public int getItemCount() {
        return names.length;
    }
}
