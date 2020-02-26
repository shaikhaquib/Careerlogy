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

import com.mmo.careerlogy.Adapter.StudentAdapter;
import com.mmo.careerlogy.Extra.MyItemDecoration;
import com.mmo.careerlogy.Extra.UpdateTitle;
import com.mmo.careerlogy.R;
import com.mmo.careerlogy.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class Student extends Fragment {


    private MainActivity activity;
    private UpdateTitle updateTitle;
    private RecyclerView rvStudentCategory;
    RecyclerView.Adapter studentAdapter;

    public Student() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        activity= (MainActivity) getActivity();
        updateTitle = activity.updateTitle;
        updateTitle.updateData("Student");
        return inflater.inflate(R.layout.fragment_student, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvStudentCategory = view.findViewById(R.id.rvStudentCategory);
        rvStudentCategory.setHasFixedSize(true);
        rvStudentCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvStudentCategory.addItemDecoration(new MyItemDecoration());
        studentAdapter = new StudentAdapter(getActivity());
        rvStudentCategory.setAdapter(studentAdapter);

    }
}
