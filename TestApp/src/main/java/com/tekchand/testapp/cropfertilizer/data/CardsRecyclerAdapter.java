package com.tekchand.testapp.cropfertilizer.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tekchand.testapp.R;
import com.tekchand.testapp.cropfertilizer.data.CardRecyclerAdapter.OnClickListener;

import java.util.ArrayList;
import java.util.List;

public class CardsRecyclerAdapter extends RecyclerView.Adapter<CardsRecyclerAdapter.SimpleViewHolder>  {
    private List<ListItem> listsItem;
    private Context mContext;
    private OnClickListener mListener;

    public CardsRecyclerAdapter(List<ListItem> listItems, Context context, OnClickListener listener){
        this.listsItem = listItems;
        mContext = context;
        mListener = listener;
    }

    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, @NonNull final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_items,parent, false);
        SimpleViewHolder viewHolder = new SimpleViewHolder(view, mContext, mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final SimpleViewHolder holder, @NonNull final int position) {
        holder.dataBind(listsItem.get(position));
    }

    @Override
    public int getItemCount() {
        return listsItem.size();
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        private TextView listTitle;
        private RecyclerView recyclerViewCard;
        private Context mContext;
        private OnClickListener mListener;
        private SimpleViewHolder(View itemView, Context context, OnClickListener listener) {
            super(itemView);
            listTitle = itemView.findViewById(R.id.list_title);
            recyclerViewCard = itemView.findViewById(R.id.list_recycler);
            mContext = context;
            mListener = listener;
        }

        private void dataBind(final ListItem item) {
            listTitle.setText(item.getTitle());
            recyclerViewCard.setHasFixedSize(true);

            recyclerViewCard.setLayoutManager(new LinearLayoutManager(mContext));

            CardRecyclerAdapter adapter = new CardRecyclerAdapter(item.getItems(), mListener);

            recyclerViewCard.setAdapter(adapter);
        }

    }

    public void setSearchOperation(List<ListItem> lists) {
        listsItem = new ArrayList<>();
        listsItem.addAll(lists);
        notifyDataSetChanged();

    }
}