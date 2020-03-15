package com.mmo.careerlogy.Fragment.Admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmo.careerlogy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecentQuestionsFragment extends Fragment {

    public RecentQuestionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recent_questions, container, false);
    }
}
