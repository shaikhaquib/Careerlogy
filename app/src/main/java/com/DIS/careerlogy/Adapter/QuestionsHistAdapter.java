package com.DIS.careerlogy.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.DIS.careerlogy.Extra.Constants;
import com.DIS.careerlogy.Extra.ItemClickListener;
import com.DIS.careerlogy.LoginActivity;
import com.DIS.careerlogy.Models.QuestionsHistoryItem;
import com.DIS.careerlogy.R;

import java.util.List;

public class QuestionsHistAdapter extends RecyclerView.Adapter<QuestionsHistAdapter.ViewHolder> {
    Activity activity;
    List<QuestionsHistoryItem> questions ;
    ItemClickListener itemClickListener;
    public QuestionsHistAdapter(Activity activity, List<QuestionsHistoryItem> questions, ItemClickListener itemClickListener) {
        this.questions =  questions ;
        this.activity = activity;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(activity).inflate(R.layout.question_item,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuestionsHistoryItem questionsItem = questions.get(position);
        holder.bind(questionsItem);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
/*
                new MaterialAlertDialogBuilder(activity)
                        .setTitle("Alert")
                        .setMessage("Do you want to Delete selected question")
                        .setPositiveButton("Ok",null)
                        .show();
*/

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView questinedBy ,questinedOn,questinedTitle ,questinedDesc ,issue,answeredBy,answeredOn;
        LinearLayout answerView;
        Button viewAnswer;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            questinedBy = itemView.findViewById(R.id.questinedBy);
            questinedOn = itemView.findViewById(R.id.questinedOn);
            questinedDesc = itemView.findViewById(R.id.questinedDesc);
            questinedTitle = itemView.findViewById(R.id.questinedTitle);
            issue = itemView.findViewById(R.id.issue);
            answerView = itemView.findViewById(R.id.answerView);
            answeredBy = itemView.findViewById(R.id.answeredBy);
            answeredOn = itemView.findViewById(R.id.answeredOn);
            viewAnswer = itemView.findViewById(R.id.viewAnswer);

            questinedBy.setText(LoginActivity.USER.getUMName());
            viewAnswer.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            itemClickListener.onItemClick(view,getAdapterPosition());
        }

        public void bind(QuestionsHistoryItem questionsItem) {

            questinedDesc.setText(questionsItem.getQQuestion());
            questinedTitle.setText(questionsItem.getQQuestionTitle());
            questinedOn.setText(Constants.Date(questionsItem.getQAddedDateTime()));

            if (questionsItem.getQAnswered().equals("0") && questionsItem.getQNeedClarification().equals("0") && !questionsItem.getPSCAnswerDocumentURL().isEmpty()) {
                answerView.setVisibility(View.VISIBLE);
                answeredBy.setText("Auto Generated ");
            } else if (questionsItem.getQAnswered().equals("0") && questionsItem.getQNeedClarification().equals("1") && !questionsItem.getPSCAnswerDocumentURL().isEmpty()) {
                answerView.setVisibility(View.VISIBLE);
                answeredBy.setText("Auto Generated\n(Under Review)");
            } else if (questionsItem.getAAnswer() != null) {
                answerView.setVisibility(View.VISIBLE);
                // answeredOn.setText(Constants.Date(questionsItem.getA()));
            }

        }
    }
}
