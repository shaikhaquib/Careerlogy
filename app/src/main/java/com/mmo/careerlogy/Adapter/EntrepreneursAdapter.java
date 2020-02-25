package com.mmo.careerlogy.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.mmo.careerlogy.R;

public class EntrepreneursAdapter extends RecyclerView.Adapter<EntrepreneursAdapter.ViewHolder> {

    int img [] = {R.drawable.ic_employee , R.drawable.ic_human_resources, R.drawable.ic_hotel };
    String name [] = {"Individual","Organisation" ,"Industry" };

    Activity activity;
    public EntrepreneursAdapter(FragmentActivity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public EntrepreneursAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(activity).inflate(R.layout.rv_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull EntrepreneursAdapter.ViewHolder holder, int position) {

        EntrepreneursAdapter.ViewHolder viewHolder = (EntrepreneursAdapter.ViewHolder)holder;
        viewHolder.text.setText(name[position]);
        viewHolder.icon.setImageResource(img[position]);

    }

    @Override
    public int getItemCount() {
        return name.length;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        ImageView icon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text = itemView.findViewById(R.id.text);
            icon = itemView.findViewById(R.id.icon);

        }
    }

}
