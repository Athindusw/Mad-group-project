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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class FragmentP extends Fragment {

    /*TextView tv1, tv2;
    Button btn_add_cart;
    DatabaseReference dbRef;
    OrderDetails orderdetails1;*/

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    ArrayList<RecyclerFood> dataholder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_p, container, false);
        recyclerView = view.findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataholder = new ArrayList<>();
        RecyclerFood ob1 = new RecyclerFood(R.drawable.rice1, "Mutton Rice", 450.00);
        dataholder.add(ob1);
        RecyclerFood ob2 = new RecyclerFood(R.drawable.rice2, "Chicken Rice", 300.00);
        dataholder.add(ob2);
        RecyclerFood ob3 = new RecyclerFood(R.drawable.rice3, "Garlic Rice", 200.00);
        dataholder.add(ob3);
        recyclerView.setAdapter(new RecyclerViewAdapter(dataholder));
        recyclerView.setHasFixedSize(true);

        /*TextView tv1 = (TextView) view.findViewById(R.id.tv1);
        TextView tv2 = (TextView) view.findViewById(R.id.tv2);

        orderdetails1 = new OrderDetails();

        dbRef = FirebaseDatabase.getInstance().getReference().child("Order_Details");
        btn_add_cart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //orderdetails1.setOrderID();
                orderdetails1.setPrice(Double.parseDouble(tv2.getText().toString().trim()));
                //orderdetails1.setProductID();
                orderdetails1.setQuantity(1);

                dbRef.push().setValue(orderdetails1);

                Toast.makeText(getActivity(), "Hi", Toast.LENGTH_SHORT).show();
            }
        });*/


        Button btn_cart = (Button) view.findViewById(R.id.btn_cart);

        btn_cart.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),ProductCart.class);
            intent.putExtra("some", "add food");
            startActivity(intent);
        });

        return  view;

    }
}