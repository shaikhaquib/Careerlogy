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

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    int img [] = {R.drawable.ic_presentation , R.drawable.ic_homework , R.drawable.ic_time , R.drawable.ic_test , R.drawable.ic_analysis , R.drawable.ic_test_a , R.drawable.ic_family};
    String name [] = {"Learning in Class Room","Homework" ,"Exam Date Announcement" , "Day of Exam" , "Reporting Exam to Parent" ,"Day of Result" , "P.T.A Meeting"};

    Activity activity;
    public StudentAdapter(FragmentActivity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(activity).inflate(R.layout.rv_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ViewHolder viewHolder = (ViewHolder)holder;
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
