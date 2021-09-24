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


public class FragmentRice extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    ArrayList<RecyclerRice> dataholder;

    Intent intent;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rice, container, false);
        recyclerView = view.findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataholder = new ArrayList<>();

        RecyclerRice ob1 = new RecyclerRice(R.drawable.r1, "mutton Rice", 200.00);
        dataholder.add(ob1);

        RecyclerRice ob2 = new RecyclerRice(R.drawable.r2, "chicken Rice", 200.00);
        dataholder.add(ob2);

        RecyclerRice ob3 = new RecyclerRice(R.drawable.r3, "carrot Rice", 200.00);
        dataholder.add(ob3);

        RecyclerRice ob4 = new RecyclerRice(R.drawable.r1, "mutton Rice", 200.00);
        dataholder.add(ob4);

        RecyclerRice ob5 = new RecyclerRice(R.drawable.r2, "chicken Rice", 200.00);
        dataholder.add(ob5);

        RecyclerRice ob6 = new RecyclerRice(R.drawable.r3, "carrot Rice", 200.00);
        dataholder.add(ob6);

        RecyclerRice ob7 = new RecyclerRice(R.drawable.r1, "mutton Rice", 200.00);
        dataholder.add(ob7);

        RecyclerRice ob8 = new RecyclerRice(R.drawable.r2, "chicken Rice", 200.00);
        dataholder.add(ob8);

        RecyclerRice ob9 = new RecyclerRice(R.drawable.r3, "carrot Rice", 200.00);
        dataholder.add(ob9);

        recyclerView.setAdapter(new RecyclerViewAdapter(dataholder));
        recyclerView.setHasFixedSize(true);

        Button btn_add = (Button) view.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),MainActivity.class);
            intent.putExtra("some", "add food");
            startActivity(intent);
        });

        return  view;
    }


}