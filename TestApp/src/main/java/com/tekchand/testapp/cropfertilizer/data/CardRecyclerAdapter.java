package com.tekchand.testapp.cropfertilizer.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.tekchand.testapp.R;

import java.util.List;

public class CardRecyclerAdapter extends RecyclerView.Adapter<CardRecyclerAdapter.SimpleViewHolder>  {
    private List<ItemCard> items;
    private OnClickListener listener;

    public interface OnClickListener {
        void onClick(ItemCard card);
    }
    public CardRecyclerAdapter(List<ItemCard> items, OnClickListener listener){
        this.items = items;
        this.listener = listener;

    }

    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, @NonNull final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent, false);
        SimpleViewHolder viewHolder = new SimpleViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final SimpleViewHolder holder, @NonNull final int position) {
        ItemCard itemCard = items.get(position);
        holder.dataBind(itemCard, listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        private TextView itemName;
        private ImageView itemIcon;
        private CardView cardView;
        private SimpleViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
            itemIcon = itemView.findViewById(R.id.item_image);
            cardView = itemView.findViewById(R.id.item_card);
        }

        private void dataBind(final ItemCard card, final OnClickListener listener) {
            itemName.setText(card.getTitle());
            itemIcon.setImageResource(card.getimageRes());
            cardView.setOnClickListener(v -> {
                listener.onClick(card);
            });
        }


    }
}