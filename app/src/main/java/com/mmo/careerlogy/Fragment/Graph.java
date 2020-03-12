package com.mmo.careerlogy.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.mmo.careerlogy.Extra.UpdateTitle;
import com.mmo.careerlogy.MainActivity;
import com.mmo.careerlogy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Graph extends Fragment {


    MainActivity activity;
    UpdateTitle updateTitle;
    public Graph() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        activity= (MainActivity) getActivity();
        updateTitle = activity.updateTitle;
        //updateTitle.updateData("Graph");
        return inflater.inflate(R.layout.fragment_graph, container, false);
    }

}
