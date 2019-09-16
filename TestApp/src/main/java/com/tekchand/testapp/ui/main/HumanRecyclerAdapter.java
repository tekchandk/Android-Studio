package com.tekchand.testapp.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tekchand.testapp.R;

import java.util.List;

public class HumanRecyclerAdapter extends RecyclerView.Adapter<HumanRecyclerAdapter.SimpleViewHolder>{
    private List<Human> humans;

    public HumanRecyclerAdapter(List<Human> humans){
        this.humans = humans;
    }

    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.itemsrecycleview,parent, false);
        SimpleViewHolder viewHolder = new SimpleViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull SimpleViewHolder holder, int position) {
        holder.name.setText(humans.get(position).getName());
        holder.location.setText(humans.get(position).getLocation());
        holder.email.setText(humans.get(position).getEmail());

    }

    @Override
    public int getItemCount() {
        return humans.size();
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder{
        public TextView location;
        public TextView name;
        public TextView email;
        public SimpleViewHolder(View itemView) {

            super(itemView);
            name = (TextView)itemView.findViewById(R.id.nameTextView);
            location = (TextView)itemView.findViewById(R.id.locTextView);
            email = (TextView)itemView.findViewById(R.id.emailTextView);

        }

    }

}