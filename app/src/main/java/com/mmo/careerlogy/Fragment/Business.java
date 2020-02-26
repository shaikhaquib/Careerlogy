package com.mmo.careerlogy.Fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmo.careerlogy.Adapter.EntrepreneursAdapter;
import com.mmo.careerlogy.Extra.MyItemDecoration;
import com.mmo.careerlogy.Extra.UpdateTitle;
import com.mmo.careerlogy.R;
import com.mmo.careerlogy.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class Business extends Fragment {



    MainActivity activity;
    UpdateTitle updateTitle;

    private RecyclerView rvEntrepreneursCategory;
    RecyclerView.Adapter entrepreneursAdapter;

    public Business() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        activity= (MainActivity) getActivity();
        updateTitle = activity.updateTitle;
        updateTitle.updateData("Entrepreneurs");

        return inflater.inflate(R.layout.fragment_buisness, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvEntrepreneursCategory = view.findViewById(R.id.rvEntrepreneursCategory);
        rvEntrepreneursCategory.setHasFixedSize(true);
        rvEntrepreneursCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvEntrepreneursCategory.addItemDecoration(new MyItemDecoration());
        entrepreneursAdapter = new EntrepreneursAdapter(getActivity());
        rvEntrepreneursCategory.setAdapter(entrepreneursAdapter);

    }

}
