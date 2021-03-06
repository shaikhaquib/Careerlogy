package com.DIS.careerlogy.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.DIS.careerlogy.Activity.EntrepreneurSubCategory;
import com.DIS.careerlogy.Extra.Constants;
import com.DIS.careerlogy.Extra.ItemClickListener;
import com.DIS.careerlogy.Models.ProblemSubCategoryItem;
import com.DIS.careerlogy.R;

import java.util.List;

public class EntrepreneursSubAdapter extends RecyclerView.Adapter<EntrepreneursSubAdapter.ViewHolder> {
    Activity activity;
    List<ProblemSubCategoryItem> problemSubCategories;
    ItemClickListener itemClickListener;
    String name [] = {"Learning in Class Room","Homework" ,"Exam Date Announcement" , "Day of Exam" , "Reporting Exam to Parent" ,"Day of Result" , "P.T.A Meeting"};
    public EntrepreneursSubAdapter(EntrepreneurSubCategory entrepreneursSubAdapter, List<ProblemSubCategoryItem> problemSubCategories, ItemClickListener itemClickListener) {
        this.activity = entrepreneursSubAdapter;
        this.problemSubCategories =problemSubCategories;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(activity).inflate(R.layout.itemlayout_subcate,parent,false),itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder)holder;
        ProblemSubCategoryItem problem= problemSubCategories.get(position);
        viewHolder.text.setText(Constants.capitalize(problem.getPSCName()));
        viewHolder.icon.setText(String.valueOf(position + 1));
    }

    @Override
    public int getItemCount() {
        return problemSubCategories.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView text;
        TextView icon;
        public ViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            text = itemView.findViewById(R.id.text);
            icon = itemView.findViewById(R.id.icon);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            itemClickListener.onItemClick(view,getAdapterPosition());
        }
    }

}
