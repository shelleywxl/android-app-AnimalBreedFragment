package com.example.android.animalfragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ThirdActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // put a ContentFragment in "webcontainer"
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ContentFragment contentFragment = new ContentFragment();
        ft.replace(R.id.webcontainer, contentFragment);
        ft.commit();


        // put a BreedFragment in "listcontainer" for landscape
        //FragmentManager fm2 = getFragmentManager();
        //FragmentTransaction ft2 = fm2.beginTransaction();
        //BreedFragment breedFragment = new BreedFragment();
        //ft2.replace(R.id.listcontainer, breedFragment);
        //ft.addToBackStack(null);
        //ft.replace(R.id.listcontainer, breedFragment);
        //ft2.commit();
    }
}
