package com.tekchand.testapp.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tekchand.testapp.R;

import java.util.List;

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.SimpleViewHolder>{
    private List<User> users;
    private Context context;

    public UserRecyclerAdapter(Context context, List<User> users){
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.user_items,parent, false);
        SimpleViewHolder viewHolder = new SimpleViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull SimpleViewHolder holder, int position) {
        holder.name.setText("abc");
        holder.phoneNo.setText("100");
        holder.email.setText("abc@gmail.com");

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView phoneNo;
        public TextView email;
        public SimpleViewHolder(View itemView) {

            super(itemView);
            // ids
        }

    }

}
