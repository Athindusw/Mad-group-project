package com.example.madproject;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    ArrayList<RecyclerRice> dataholder;

    public RecyclerViewAdapter(ArrayList<RecyclerRice> dataholder) {
        this.dataholder = dataholder;
    }

    @NonNull
        @Override
        public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_design,parent,false);
            RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
            return viewHolder;
        }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");

        holder.img.setImageResource(dataholder.get(position).getImage());
        holder.name.setText(dataholder.get(position).getName());
        holder.price.setText(Double.toString(dataholder.get(position).getPrice()));

    }

        @Override
        public int getItemCount() {
            return dataholder.size();
        }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView name, price;
        ImageView img_delete;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.tv1);
            price = itemView.findViewById(R.id.tv2);
        }
    }

}
