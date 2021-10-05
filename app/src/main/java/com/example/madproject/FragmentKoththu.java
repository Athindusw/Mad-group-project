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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

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

    ProgressBar progressBar;
    EditText inputSearch;
    ImageView search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_koththu, container, false);
        recview = view.findViewById(R.id.recview);
        progressBar = view.findViewById(R.id.adminSpin);
        inputSearch = view.findViewById(R.id.inputSearch);
        search = view.findViewById(R.id.search);


        recview.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Food> options =
                new FirebaseRecyclerOptions.Builder<Food>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Food").orderByChild("foodCategory").equalTo("Koththu"), Food.class)
                        .build();

        adapter = new RecyclerViewAdapter(options);
        recview.setAdapter(adapter);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = inputSearch.getText().toString();
                processsearch(text);
            }
        });


        Button btn_add = (Button) view.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),AddFood.class);
            intent.putExtra("some", "add food");
            startActivity(intent);
        });

        return  view;
    }

    @Override
    public void onStart() {
        super.onStart();
        progressBar.setVisibility(View.VISIBLE);
        adapter.startListening();
    }

    public void onStop(){
        super.onStop();
        progressBar.setVisibility(View.GONE);
        adapter.stopListening();
    }

    private void processsearch(String s)
    {
        FirebaseRecyclerOptions<Food> options =
                new FirebaseRecyclerOptions.Builder<Food>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Food").orderByChild("foodName").startAt(s).endAt(s+"\uf8ff"), Food.class)
                        .build();

        adapter=new RecyclerViewAdapter(options);
        adapter.startListening();
        recview.setAdapter(adapter);

    }
}