package com.example.android.animalfragment;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.app.ListFragment;
import android.app.Activity;

import java.lang.reflect.Array;
import java.util.*;


public class AnimalFragment extends ListFragment implements
        FragmentManager.OnBackStackChangedListener {

    int mCurPosition = -1;
    ListView mListView;
    ArrayList<String> animalArray;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListView = getListView();

        animalArray = new ArrayList<String>(Arrays.asList(
                getResources().getStringArray(R.array.animal_array)));
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_activated_1, animalArray));

        if (savedInstanceState != null) {
            mCurPosition = savedInstanceState.getInt("curChoice", 0);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", mCurPosition);
    }

    // click list item, start second activity with animalType (get from position)
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        mCurPosition = position;
        String animalType = animalArray.get(position);

        Intent intent = new Intent(getActivity().getApplicationContext(), SecondActivity.class);
        //intent.setClass(getActivity(), SecondActivity.class);
        intent.putExtra("animal_type", animalType);
        startActivity(intent);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onBackStackChanged() {

    }

}