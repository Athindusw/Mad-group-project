package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class ProductSelectCustomer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_select_customer);


    }


    public void changeFragment(View view){
        Fragment fragment;
        if (view == findViewById(R.id.btn_rice)){
            fragment = new FragmentP();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.defaultfood,fragment);
            ft.commit();
        }
        if (view == findViewById(R.id.btn_kottu)){
            fragment = new FragmentK();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.defaultfood,fragment);
            ft.commit();
        }
        if (view == findViewById(R.id.btn_burger)){
            fragment = new FragmentB();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.defaultfood,fragment);
            ft.commit();
        }
    }
}