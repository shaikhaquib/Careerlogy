package com.DIS.careerlogy.Acivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.DIS.careerlogy.Network.RetrofitClient;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.DIS.careerlogy.Extra.Constants;
import com.DIS.careerlogy.Extra.Progress;
import com.DIS.careerlogy.Models.AskedQuestionListItem;
import com.DIS.careerlogy.Models.UploadTestimonialResponse;
import com.DIS.careerlogy.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.DIS.careerlogy.LoginActivity.USER;

public class AnswertheQuestionActivity extends AppCompatActivity {

    TextView userType;
    TextView cateGory;
    TextView questinedBy;
    TextView questinedOn;
    TextView questinedTitle;
    TextView questinedDesc;
    TextView edtAnswer;
    Button btnSubmit;
    AskedQuestionListItem categoryItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answerthe_question);

        userType = findViewById(R.id.userType);
        cateGory = findViewById(R.id.cateGory);
        questinedBy = findViewById(R.id.questinedBy);
        questinedOn = findViewById(R.id.questinedOn);
        questinedTitle = findViewById(R.id.questinedTitle);
        questinedDesc = findViewById(R.id.questinedDesc);
        edtAnswer = findViewById(R.id.edtAnswer);
        btnSubmit = findViewById(R.id.btnSubmit);

        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        categoryItem = (AskedQuestionListItem) getIntent().getSerializableExtra("categoryItem");

        userType.setText(Constants.capitalize(categoryItem.getPCType()));
        cateGory.setText(Constants.capitalize(categoryItem.getPSCName()));
        questinedBy.setText(Constants.capitalize(categoryItem.getUMName()));
        questinedTitle.setText(Constants.capitalize(categoryItem.getQQuestionTitle()));
        questinedDesc.setText(Constants.capitalize(categoryItem.getQQuestion()));
        questinedOn.setText(Constants.Date(categoryItem.getQAddedDateTime()));

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtAnswer.getText().toString().isEmpty()) {
                    Constants.Alert(AnswertheQuestionActivity.this, "Please Write Answer");
                } else {
                    SaveAnswer();
                }
            }
        });


    }

    private void SaveAnswer() {
        final Progress progress = new Progress(AnswertheQuestionActivity.this);
        progress.show();
        Call<UploadTestimonialResponse> addYouTubeLink = RetrofitClient.getInstance().getApi().SaveAnswer(edtAnswer.getText().toString(),
                categoryItem.getQID(),
                USER.getUMID());
        addYouTubeLink.enqueue(new Callback<UploadTestimonialResponse>() {
            @Override
            public void onResponse(Call<UploadTestimonialResponse> call, Response<UploadTestimonialResponse> response) {
                progress.dismiss();

                if (response.isSuccessful()) {
                    if (response.body().isError()) {
                        Constants.Alert(AnswertheQuestionActivity.this, response.body().getErrormsg());
                    } else {
                        new MaterialAlertDialogBuilder(AnswertheQuestionActivity.this)
                                .setTitle("Alert")
                                .setMessage(response.body().getErrormsg())
                                .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                })
                                .show();
                    }
                }

            }

            @Override
            public void onFailure(Call<UploadTestimonialResponse> call, Throwable t) {
                progress.dismiss();
            }
        });
    }

}
