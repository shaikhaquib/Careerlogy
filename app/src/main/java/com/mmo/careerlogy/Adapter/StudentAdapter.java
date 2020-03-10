package com.mmo.careerlogy.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.mmo.careerlogy.Acivity.StudentSubCategory;
import com.mmo.careerlogy.Extra.ItemClickListener;
import com.mmo.careerlogy.Models.ProblemCategoryItem;
import com.mmo.careerlogy.R;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    int img [] = {R.drawable.ic_presentation , R.drawable.ic_homework , R.drawable.ic_time , R.drawable.ic_test , R.drawable.ic_analysis , R.drawable.ic_test_a , R.drawable.ic_family};
    String name [] = {"Learning in Class Room","Homework" ,"Exam Date Announcement" , "Day of Exam" , "Reporting Exam to Parent" ,"Day of Result" , "P.T.A Meeting"};
    private List<ProblemCategoryItem> problemCategories = new ArrayList<>();
    ItemClickListener clickListener;

    Activity activity;
    public StudentAdapter(FragmentActivity activity, List<ProblemCategoryItem> problemCategories, ItemClickListener itemClickListener) {
        this.activity = activity;
        this.problemCategories = problemCategories;
        this.clickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(activity).inflate(R.layout.rv_item,parent,false),clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ProblemCategoryItem problemCategoryItem = problemCategories.get(position);
        ViewHolder viewHolder = (ViewHolder)holder;
        viewHolder.text.setText(problemCategoryItem.getPCName());
        viewHolder.icon.setImageResource(img[position]);

    }

    @Override
    public int getItemCount() {
        return problemCategories.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView text;
        ImageView icon;
        ItemClickListener listener;
        public ViewHolder(@NonNull View itemView, ItemClickListener clickListener) {
            super(itemView);

            listener = clickListener;
            text = itemView.findViewById(R.id.text);
            icon = itemView.findViewById(R.id.icon);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(view,getAdapterPosition());
        }
    }

}
