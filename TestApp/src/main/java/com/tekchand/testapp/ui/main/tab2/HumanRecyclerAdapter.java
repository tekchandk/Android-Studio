package com.tekchand.testapp.ui.main.tab2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.tekchand.testapp.R;
import com.tekchand.testapp.ui.main.tab1.Human;

import java.util.List;

public class HumanRecyclerAdapter extends RecyclerView.Adapter<HumanRecyclerAdapter.SimpleViewHolder>  {
    private List<Human> humans;
    private OnClickListener listener;


    public interface OnClickListener {
        void onClick(Human human);
    }
    public HumanRecyclerAdapter(List<Human> humans,  OnClickListener listener){
        this.humans = humans;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, @NonNull final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemsrecycleview,parent, false);
        SimpleViewHolder viewHolder = new SimpleViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final SimpleViewHolder holder, @NonNull final int position) {
        holder.dataBind(humans, position, listener);
    }

    @Override
    public int getItemCount() {
        return humans.size();
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder{
        private TextView location;
        private TextView name;
        private TextView email;
        private CardView cardView;
        private SimpleViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameTextView);
            location = itemView.findViewById(R.id.locTextView);
            email = itemView.findViewById(R.id.emailTextView);
            cardView = itemView.findViewById(R.id.person_card_view);
        }

        private void dataBind(final List<Human> humans, final int position, final OnClickListener listener) {
            name.setText(humans.get(position).getName());
            location.setText(humans.get(position).getLocation());
            email.setText(humans.get(position).getEmail());
            cardView.setOnClickListener(v -> listener.onClick(humans.get(position)));
        }

        private void onClick(View v) {

        }

    }

}
