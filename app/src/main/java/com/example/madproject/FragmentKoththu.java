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

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class FragmentKoththu extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    RecyclerView recview;
    RecyclerViewAdapter adapter;
    public FragmentKoththu() {

    }

    public static FragmentKoththu newInstance(String param1, String param2) {
        FragmentKoththu fragment = new FragmentKoththu();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


//    //ArrayList<RecyclerRice> dataholder;

//    Intent intent;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_koththu, container, false);
        recview = view.findViewById(R.id.recview);

        recview.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Food> options =
                new FirebaseRecyclerOptions.Builder<Food>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Food").orderByChild("foodCategory").equalTo("Koththu"), Food.class)
                        .build();



        //recyclerView.setHasFixedSize(true);
        adapter = new RecyclerViewAdapter(options);
        recview.setAdapter(adapter);

        Button btn_add = (Button) view.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),MainActivity.class);
            intent.putExtra("some", "add food");
            startActivity(intent);
        });

        return  view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    public void onStop(){
        super.onStop();
        adapter.stopListening();
    }
}