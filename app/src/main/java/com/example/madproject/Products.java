package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class Products extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
    }

    public void changeFragment(View view){
        Fragment fragment;
        if (view == findViewById(R.id.btnFragment1)){
            fragment = new FragmentKoththu();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frgment1,fragment);
            ft.commit();
        }
        if (view == findViewById(R.id.btnFragment2)){
            fragment = new FragmentRice();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frgment1,fragment);
            ft.commit();
        }
        if (view == findViewById(R.id.btnFragment3)){
            fragment = new FragmentBurger();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frgment1,fragment);
            ft.commit();
        }
    }
}