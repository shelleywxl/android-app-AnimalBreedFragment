package com.example.android.animalfragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SecondActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // put a BreedFragment in the "listcontainer"
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        BreedFragment breedFragment = new BreedFragment();
        ft.replace(R.id.listcontainer, breedFragment);
        //ft.addToBackStack(null);
        ft.commit();
    }
}
