package com.example.android.animalfragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;


public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // put an AnimalFragment in the "listcontainer"
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        AnimalFragment animalFragment = new AnimalFragment();
        ft.replace(R.id.listcontainer, animalFragment);
        //ft.addToBackStack(null);
        ft.commit();
    }
}
