package com.example.madproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;


public class FragmentK extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    ArrayList<RecyclerFood> dataholder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fragment_k, container, false);
        recyclerView = view.findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataholder = new ArrayList<>();
        RecyclerFood ob1 = new RecyclerFood(R.drawable.kottu1, "Cheese Kottu", 450.00);
        dataholder.add(ob1);
        RecyclerFood ob2 = new RecyclerFood(R.drawable.kottu2, "Chicken Kottu", 300.00);
        dataholder.add(ob2);
        RecyclerFood ob3 = new RecyclerFood(R.drawable.kottu3, "Egg Kottu", 200.00);
        dataholder.add(ob3);
        recyclerView.setAdapter(new RecyclerViewAdapter(dataholder));
        recyclerView.setHasFixedSize(true);


        Button btn_cartK = (Button) view.findViewById(R.id.btn_cartK);

        btn_cartK.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),ProductCart.class);
            intent.putExtra("some", "add food");
            startActivity(intent);
        });
        return view;
    }
}