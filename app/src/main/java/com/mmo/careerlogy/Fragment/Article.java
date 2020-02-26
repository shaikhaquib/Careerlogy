package com.mmo.careerlogy.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmo.careerlogy.Extra.UpdateTitle;
import com.mmo.careerlogy.R;
import com.mmo.careerlogy.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class Article extends Fragment {


    MainActivity activity;
    UpdateTitle updateTitle;

    public Article() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        activity= (MainActivity) getActivity();
        updateTitle = activity.updateTitle;
        updateTitle.updateData("Article");
        return inflater.inflate(R.layout.fragment_article, container, false);
    }

}
