package com.mmo.careerlogy.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mmo.careerlogy.Acivity.StudentSubCategory;
import com.mmo.careerlogy.Models.ProblemSubCategoryItem;
import com.mmo.careerlogy.R;

import java.util.List;

public class LearningSubAdapter extends RecyclerView.Adapter<LearningSubAdapter.ViewHolder> {
    Activity activity;
    List<ProblemSubCategoryItem> problemSubCategories;

    String name [] = {"Learning in Class Room","Homework" ,"Exam Date Announcement" , "Day of Exam" , "Reporting Exam to Parent" ,"Day of Result" , "P.T.A Meeting"};
    public LearningSubAdapter(StudentSubCategory studentSubCategory, List<ProblemSubCategoryItem> problemSubCategories) {
        this.activity = studentSubCategory;
        this.problemSubCategories =problemSubCategories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(activity).inflate(R.layout.itemlayout_subcate,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder)holder;
        ProblemSubCategoryItem problem = problemSubCategories.get(position);
        viewHolder.text.setText(problem.getPSCName());
        viewHolder.icon.setText(String.valueOf(position+1));
    }

    @Override
    public int getItemCount() {
        return problemSubCategories.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView text;
        TextView icon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text = itemView.findViewById(R.id.text);
            icon = itemView.findViewById(R.id.icon);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
        }
    }

}
