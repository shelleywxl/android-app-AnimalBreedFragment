package com.example.android.animalfragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.ListFragment;
import android.content.Intent;

import java.util.*;


public class BreedFragment extends ListFragment implements
        FragmentManager.OnBackStackChangedListener {

    private static final String DEBUG_TAG = "BreedFragment";

    ListView mListView;
    int mCurPosition = -1;
    boolean mShowMultipleFragments;
    ArrayList<String> mFilteredBreeds = new ArrayList<String>(10);
    ArrayList<String> mFilteredUrls = new ArrayList<String>(10);

    // new BreedFragment with the passed animalType
    public static BreedFragment newInstance(String animalType) {
        Log.v(DEBUG_TAG, "creating new instance with animalType: " + animalType);
        BreedFragment fragment = new BreedFragment();
        Bundle args = new Bundle();
        args.putString("animal_type", animalType);
        fragment.setArguments(args);
        return fragment;
    }


    private void getFilteredAnimals(String[] animalStrs, String animalType,
                                    ArrayList<String> filteredBreeds, ArrayList<String> filteredUrls) {
        for (String animalStr : animalStrs) {
            List<String> animalList = Arrays.asList(animalStr.split(","));
            if (animalList.get(0).equals(animalType)) {
                filteredBreeds.add(animalList.get(1));
                filteredUrls.add(animalList.get(2));
            }
        }
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(DEBUG_TAG, "FRAGMENT LIFECYCLE EVENT: onActivityCreated()");

        mListView = getListView();
        //getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // get animal type chosen in AnimalFragment
        String animalType = "";
        Bundle args = getArguments();
        if (args != null) {
            animalType = args.getString("animal_type");
        }
        Log.d(DEBUG_TAG, "animalType from Bundle: " + animalType);

        Intent intent = getActivity().getIntent();
        String animalType2 = intent.getStringExtra("animal_type");
        Log.d(DEBUG_TAG, "animalType2 from intent: " + animalType2);
        if (animalType2 != null && !animalType2.equals("")) {
            animalType = animalType2;
            Log.d(DEBUG_TAG, "animalType2!=empty, animalType now: " + animalType);
        }


        // populate ListView control within the Fragment
        getFilteredAnimals(getResources().getStringArray(
                R.array.animal_secondary_array), animalType, mFilteredBreeds, mFilteredUrls);
        Log.d(DEBUG_TAG, "mFilteredBreeds: " + mFilteredBreeds);
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_activated_1, mFilteredBreeds));

        // retrieve the values of position from the Bundle
        if (savedInstanceState != null) {
            mCurPosition = savedInstanceState.getInt("curChoice", 0);
        }

        // if it is in landscape (shows webcontainer)
        View contentView = getActivity().findViewById(R.id.webcontainer);
        mShowMultipleFragments = (contentView != null ) && contentView.getVisibility() == View.VISIBLE;
        Log.d(DEBUG_TAG, "show multiple fragments: " + mShowMultipleFragments);

        // monitor back stack changes to update list view
        //getFragmentManager().addOnBackStackChangedListener(this);
    }


    // save the value of position to the Bundle
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", mCurPosition);
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        mCurPosition = position;
        String url = mFilteredUrls.get(position);

        //View webView = getActivity().findViewById(R.id.webcontainer);

        //if (webView != null && webView.getVisibility() == View.VISIBLE) {
            //in landscape mode
        //    ContentFragment contentFragment = ContentFragment.newInstance(url);
        //    FragmentManager fm = getFragmentManager();
        //    FragmentTransaction ft = fm.beginTransaction();
        //    ft.replace(R.id.webcontainer, contentFragment);
        //    ft.commit();
        //}
        //else {  // in portrait mode, open a new activity
        //    Intent intent = new Intent();
        //    intent.setClass(getActivity().getApplicationContext(), ThirdActivity.class);
        //    intent.putExtra("url", url);
        //    startActivity(intent);
        //}
        Intent intent = new Intent(getActivity().getApplicationContext(), ThirdActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }

    @Override
    public void onBackStackChanged() {

    }

    //@Override
    //public void onCreate(Bundle savedInstanceState) {


    //}
}