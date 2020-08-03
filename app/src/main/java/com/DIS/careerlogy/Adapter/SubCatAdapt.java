package com.DIS.careerlogy.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.DIS.careerlogy.Extra.Constants;
import com.DIS.careerlogy.Extra.ItemClickListener;
import com.DIS.careerlogy.Models.ProbSubCatLstItem;
import com.DIS.careerlogy.Models.ProbSubCatLstItem;
import com.DIS.careerlogy.R;

import java.util.List;

public class SubCatAdapt extends RecyclerView.Adapter<SubCatAdapt.ViewHolder> {
    Activity activity;
    List<ProbSubCatLstItem> problemSubCategories;
    ItemClickListener itemClickListener;

    public SubCatAdapt(Activity studentSubCategory, List<ProbSubCatLstItem> problemSubCategories, ItemClickListener itemClickListener) {
        this.activity = studentSubCategory;
        this.problemSubCategories = problemSubCategories;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public SubCatAdapt.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubCatAdapt.ViewHolder(LayoutInflater.from(activity).inflate(R.layout.itemlayout_subcate, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SubCatAdapt.ViewHolder holder, int position) {
        SubCatAdapt.ViewHolder viewHolder = (SubCatAdapt.ViewHolder) holder;
        ProbSubCatLstItem problem = problemSubCategories.get(position);
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text = itemView.findViewById(R.id.text);
            icon = itemView.findViewById(R.id.icon);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            itemClickListener.onItemClick(view, getAdapterPosition());
        }
    }

}
