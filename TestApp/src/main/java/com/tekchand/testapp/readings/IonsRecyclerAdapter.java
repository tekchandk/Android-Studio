package com.tekchand.testapp.readings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.tekchand.testapp.R;

public class IonsRecyclerAdapter extends RecyclerView.Adapter<IonsRecyclerAdapter.SimpleViewHolder>  {
    private String[] ions;
    private OnClickListener listener;

    public interface OnClickListener {
        void onClick(String ion);
    }
        public IonsRecyclerAdapter(String[] ions,  OnClickListener listener){
            this.ions = ions;
            this.listener = listener;
        }

        @NonNull
        @Override
        public SimpleViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, @NonNull final int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_ions_list,parent, false);
            return new SimpleViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final SimpleViewHolder holder, @NonNull final int position) {
            holder.dataBind(ions, position, listener);
        }

        @Override
        public int getItemCount() {
            return ions.length;
        }

        public static class SimpleViewHolder extends RecyclerView.ViewHolder {
            private TextView ionName;
            private CardView cardView;

            private SimpleViewHolder(View itemView) {
                super(itemView);
                ionName = itemView.findViewById(R.id.ion_name);
                cardView = itemView.findViewById(R.id.ion_cardview);
            }

            private void dataBind(final String[] ions, final int position, final OnClickListener listener) {
                ionName.setText(ions[position]);
                cardView.setOnClickListener(v -> listener.onClick(ions[position]));
            }
        }
}
