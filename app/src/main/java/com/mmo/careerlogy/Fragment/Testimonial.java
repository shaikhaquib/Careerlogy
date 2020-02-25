package com.mmo.careerlogy.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmo.careerlogy.Extra.UpdateTitle;
import com.mmo.careerlogy.R;
import com.mmo.careerlogy.SearchCity;

/**
 * A simple {@link Fragment} subclass.
 */
public class Testimonial extends Fragment {


    SearchCity activity;
    UpdateTitle updateTitle;

    public Testimonial() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        activity= (SearchCity) getActivity();
        updateTitle = activity.updateTitle;
        updateTitle.updateData("Testimonial Video");
        return inflater.inflate(R.layout.fragment_testimonial, container, false);
    }

}
