package com.example.madproject;



import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
public class RecyclerViewAdapter extends FirebaseRecyclerAdapter<Food,RecyclerViewAdapter.RecyclerViewHolder> {

    OrderDetails orderdetails = new OrderDetails();
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("OrderDetails");

    public RecyclerViewAdapter(@NonNull FirebaseRecyclerOptions<Food> options)
    {
        super(options);
    }



    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_single_row_design,parent,false);
        return new RecyclerViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position, Food model) {
        Log.d(TAG, "onBindViewHolder: called");
        holder.tv1.setText(model.getFoodName());
        holder.tv2.setText(model.getFoodPrice());
        holder.tv3.setText(model.getFoodCategory());
        Glide.with(holder.image.getContext()).load(model.getFoodImage()).into(holder.image);



        holder.btn_add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String>parameters = new HashMap<>();
                parameters.put("Price", model.getFoodPrice());
                parameters.put("ProductName", model.getFoodName());
                parameters.put("Quantity", "1");
                ref.child(ref.push().getKey()).setValue(parameters);
                Toast.makeText(v.getContext(), "Added to the Cart", Toast.LENGTH_SHORT).show();
                holder.btn_add_cart.setEnabled(false);


            }
        });
    }



    public class RecyclerViewHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView tv1, tv2, tv3;
        Button btn_add_cart;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            tv1 = itemView.findViewById(R.id.tv_name);
            tv2 = itemView.findViewById(R.id.tv2);
            tv3 = itemView.findViewById(R.id.tv3);
            btn_add_cart = itemView.findViewById(R.id.btn_add_cart);
        }
    }




}

