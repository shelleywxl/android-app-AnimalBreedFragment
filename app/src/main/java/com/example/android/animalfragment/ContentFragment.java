package com.example.android.animalfragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebViewFragment;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Arrays;
import java.util.List;


public class ContentFragment extends WebViewFragment {

    private static final String DEBUG_TAG = "ContentFragment";

    //public static ContentFragment newInstance(String url) {
    //    ContentFragment fragment = new ContentFragment();
    //    Bundle args = new Bundle();
    //    args.putString("url", url);
    //    fragment.setArguments(args);
    //    return fragment;
    //}

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //String animalUrl = getArguments().getString("url");
        Intent intent = getActivity().getIntent();
        String animalUrl = intent.getStringExtra("url");

        // dynamically add a new WebView
        WebView webView = getWebView();
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(animalUrl);

        // if in landscape, add BreedFragment in the listcontainer
        View breedView = getActivity().findViewById(R.id.listcontainer);
        Log.d(DEBUG_TAG, "breedView != null && breedView.getVisibility() == View.VISIBLE: " +
                String.valueOf(breedView != null && breedView.getVisibility() == View.VISIBLE));

        if (breedView != null && breedView.getVisibility() == View.VISIBLE) {
            Log.d(DEBUG_TAG, "URL: " + animalUrl);
            String animalType = getAnimalTypeFromUrl(getResources().getStringArray(R.array.animal_secondary_array), animalUrl);
            Log.d(DEBUG_TAG, "animalType from url: " + animalType);

            BreedFragment breedFragment = BreedFragment.newInstance(animalType);
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.listcontainer, breedFragment, "BREED_LIST");
            ft.commit();
        }
    }

    private String getAnimalTypeFromUrl(String[] animalStrs, String url) {
        String animalType = "";
        //Log.d(DEBUG_TAG, "url passed to getAnimalTypeFromUrl: " + url);
        for (String animalStr : animalStrs) {
            //Log.d(DEBUG_TAG, "animalStr: " + animalStr);
            List<String> animalList = Arrays.asList(animalStr.split(","));
            //Log.d(DEBUG_TAG, "url in animalList: " + animalList.get(2));
            if (animalList.get(2).equals(url)) {
                Log.d(DEBUG_TAG, "found == url");
                animalType = animalList.get(0);
                break;
            }
        }
        return animalType;
    }



}