package com.example.madproject;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.DialogPlusBuilder;
import com.orhanobut.dialogplus.ViewHolder;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends FirebaseRecyclerAdapter<Food,RecyclerViewAdapter.RecyclerViewHolder>
{
    public RecyclerViewAdapter(@NonNull FirebaseRecyclerOptions<Food> options)
    {
        super(options);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position, Food model) {
        Log.d(TAG, "onBindViewHolder: called");

        //holder.img.setImageResource(Integer.parseInt(Food.getFoodImage()));
            holder.tv3.setText(model.getFoodCategory());
            holder.tv1.setText(model.getFoodName());
            holder.tv2.setText(model.getFoodPrice());

            Glide.with(holder.image.getContext()).load(model.getFoodImage()).into(holder.image);

        holder.img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.image.getContext())
                        .setContentHolder(new ViewHolder(R.layout.activity_edit))
                        .setExpanded(true,1100)
                        .create();

                View myview = dialogPlus.getHolderView();

                final EditText name=myview.findViewById(R.id.uname);
                final EditText price=myview.findViewById(R.id.uprice);
                final EditText purl=myview.findViewById(R.id.uimgurl);
                Button submit=myview.findViewById(R.id.usubmit);

                name.setText(model.getFoodName());
                price.setText(model.getFoodPrice());
                purl.setText(model.getFoodImage());


                dialogPlus.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("foodName",name.getText().toString());
                        map.put("foodPrice",price.getText().toString());
                        map.put("foodImage",purl.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Food")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();
                                        Toast.makeText(view.getContext(), "Successfully updated", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });

            }
        });

        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.image.getContext());
                builder.setTitle("Delete Food");
                builder.setMessage("Delete...?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Food")
                                .child(getRef(position).getKey()).removeValue();
                        Toast.makeText(view.getContext(),"Successfully Deleted", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });

    }

        @NonNull
        @Override
        public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_design,parent,false);
            return new RecyclerViewHolder(view);
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView tv1, tv2,tv3;
        ImageView img_delete;
        ImageView img_edit;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tv3 = itemView.findViewById(R.id.tv3);
            image = itemView.findViewById(R.id.image);
            tv1 = itemView.findViewById(R.id.tv1);
            tv2 = itemView.findViewById(R.id.tv2);

            img_edit=itemView.findViewById(R.id.img_edit);
            img_delete=itemView.findViewById(R.id.img_delete);
        }
    }

}
