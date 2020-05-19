package com.DIS.careerlogy.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.DIS.careerlogy.Extra.ItemClickListener;
import com.DIS.careerlogy.Models.ProblemCategoryItem;
import com.DIS.careerlogy.R;

import java.util.ArrayList;
import java.util.List;

public class EntrepreneursAdapter extends RecyclerView.Adapter<EntrepreneursAdapter.ViewHolder> {

    int img [] = {R.drawable.ic_employee , R.drawable.ic_human_resources, R.drawable.ic_hotel };
    String name [] = {"Individual","Organisation" ,"Industry" };
    private List<ProblemCategoryItem> problemCategories = new ArrayList<>();
    private ItemClickListener itemClickListener;


    Activity activity;
    public EntrepreneursAdapter(FragmentActivity activity, List<ProblemCategoryItem> problemCategories, ItemClickListener itemClickListener) {
        this.activity = activity;
        this.problemCategories = problemCategories;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public EntrepreneursAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(activity).inflate(R.layout.rv_item,parent,false),itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EntrepreneursAdapter.ViewHolder holder, int position) {

        EntrepreneursAdapter.ViewHolder viewHolder = (EntrepreneursAdapter.ViewHolder)holder;
        ProblemCategoryItem problemCategoryItem = problemCategories.get(position);
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
        public ViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            this.listener = itemClickListener ;

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
