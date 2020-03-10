package com.mmo.careerlogy.Acivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.mmo.careerlogy.Extra.Constants;
import com.mmo.careerlogy.Extra.Progress;
import com.mmo.careerlogy.LoginActivity;
import com.mmo.careerlogy.Models.AskQuestionResponse;
import com.mmo.careerlogy.Models.ProblemCategory;
import com.mmo.careerlogy.Models.ProblemCategoryItem;
import com.mmo.careerlogy.Models.ProblemSubCategoryItem;
import com.mmo.careerlogy.Models.ProblemSubCategoryResponse;
import com.mmo.careerlogy.Network.RetrofitClient;
import com.mmo.careerlogy.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AskQuestionEntrepreneur extends AppCompatActivity {

    List<ProblemCategoryItem> problemCategories = new ArrayList<>();
    List<String> strProblem = new ArrayList<>();
    private List<ProblemSubCategoryItem> problemSubCategories = new ArrayList<>();
    List<String> strSubProblem = new ArrayList<>();
    Button dropDownCategory,dropDownSubCategory,btnSubmit;
    TextInputEditText askTitleQuestion;
    TextInputEditText askQuestion;
    private String questionTitle,question,pscId;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question_entrepreneur);
        getCategory();

        dropDownCategory = findViewById(R.id.dropDownCategory);
        dropDownSubCategory = findViewById(R.id.dropDownSubCategory);
        btnSubmit = findViewById(R.id.btnSubmit);
        askTitleQuestion = findViewById(R.id.askTitleQuestion);
        askQuestion = findViewById(R.id.askQuestion);
        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        dropDownCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(AskQuestionEntrepreneur.this);
                builder.setCancelable(true);
                builder.setTitle("Select Problem Category");
                String[] categoryArray = new String[0];
                if (!strProblem.isEmpty()) {
                    categoryArray = new String[strProblem.size()];
                    categoryArray = strProblem.toArray(categoryArray);
                }
                final String[] finalCategoryArray = categoryArray;
                builder.setItems(categoryArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        dropDownCategory.setText(finalCategoryArray[which]);
                        if (dropDownCategory.getError()!=null)
                            dropDownCategory.setError(null);
                        getSubCategory(problemCategories.get(which).getPCID());

                    }
                });
                // Create the alert dialog
                AlertDialog dialog = builder.create();

                // Get the alert dialog ListView instance
                ListView listView = dialog.getListView();

                // Set the divider color of alert dialog list view
                listView.setDivider(new ColorDrawable(Color.GRAY));

                // Set the divider height of alert dialog list view
                listView.setDividerHeight(3);
                builder.show();
            }
        });
        dropDownSubCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(AskQuestionEntrepreneur.this);
                builder.setCancelable(true);
                builder.setTitle("Select Problem Sub Category");
                String[] categoryArray = new String[0];
                if (!strProblem.isEmpty()) {
                    categoryArray = new String[strSubProblem.size()];
                    categoryArray = strSubProblem.toArray(categoryArray);
                }
                final String[] finalCategoryArray = categoryArray;
                builder.setSingleChoiceItems(categoryArray, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        dropDownSubCategory.setText(finalCategoryArray[which]);
                        if (dropDownCategory.getError()!=null)
                            dropDownCategory.setError(null);
                        pscId =  problemSubCategories.get(0).getPSCID();

                    }
                });
                AlertDialog dialog = builder.create();

                // Get the alert dialog ListView instance
                ListView listView = dialog.getListView();

                // Set the divider color of alert dialog list view
                listView.setDivider(new ColorDrawable(Color.GRAY));

                // Set the divider height of alert dialog list view
                listView.setDividerHeight(3);
                builder.show();
            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question = askQuestion.getText().toString();
                questionTitle = askTitleQuestion.getText().toString();
                if (isValidForm()){
                    askQuestion(pscId,questionTitle,question);
                }
            }
        });

    }

    private boolean isValidForm() {
        if (dropDownCategory.getText().toString().equalsIgnoreCase("Choose")) {
            Constants.Alert(AskQuestionEntrepreneur.this, "Please Select Question Category");
            dropDownCategory.setError("Please Select Question Category");
            return false;
        }else if (dropDownSubCategory.getText().toString().equalsIgnoreCase("Choose")) {
            Constants.Alert(AskQuestionEntrepreneur.this, "Please Select Question SubCategory");
            dropDownSubCategory.setError("Please Select Question SubCategory");
            return false;
        }else  if (askTitleQuestion.getText().toString().isEmpty()) {
            Constants.Alert(AskQuestionEntrepreneur.this, "Please Enter Question Title");
            askTitleQuestion.setError("Please Enter Question Title");
            return false;
        }else  if (askQuestion.getText().toString().isEmpty()) {
            Constants.Alert(AskQuestionEntrepreneur.this, "Please Enter Question");
            askQuestion.setError("Please Enter Question");
            return false;
        }
        else {
            return true;
        }
    }


    public void getCategory() {
        final Progress progress =new Progress(AskQuestionEntrepreneur.this);
        progress.show();
        Call<ProblemCategory> call= RetrofitClient.getInstance().getApi().problemCategory("entrepreneurship");
        call.enqueue(new Callback<ProblemCategory>() {
            @Override
            public void onResponse(Call<ProblemCategory> call, Response<ProblemCategory> response) {
                progress.dismiss();
                if (response.isSuccessful()) {
                    problemCategories.addAll( response.body().getProblemCategory());
                    for (int i = 0 ; i < problemCategories.size() ;i++){
                        ProblemCategoryItem problemCategoryItem = problemCategories.get(i);
                        strProblem.add(problemCategoryItem.getPCName());
                    }
                }
            }

            @Override
            public void onFailure(Call<ProblemCategory> call, Throwable t) {
                progress.dismiss();
            }
        });
    }
    public void getSubCategory(String ID){
        final Progress progress = new Progress(AskQuestionEntrepreneur.this);
        progress.show();
        Call<ProblemSubCategoryResponse> call = RetrofitClient.getInstance().getApi().problemSubCategory(ID);
        call.enqueue(new Callback<ProblemSubCategoryResponse>() {
            @Override
            public void onResponse(Call<ProblemSubCategoryResponse> call, Response<ProblemSubCategoryResponse> response) {
                progress.dismiss();
                if (response.isSuccessful()) {
                    problemSubCategories.addAll(response.body().getProblemSubCategory());
                    for (int i = 0 ; i < problemSubCategories.size() ;i++){
                        ProblemSubCategoryItem problemCategoryItem = problemSubCategories.get(i);
                        strSubProblem.add(problemCategoryItem.getPSCName());
                    }
                }

            }

            @Override
            public void onFailure(Call<ProblemSubCategoryResponse> call, Throwable t) {
                progress.dismiss();
            }
        });
    }

    public void askQuestion(String Id,String questionTitle,String question){
        final Progress progress = new Progress(AskQuestionEntrepreneur.this);
        progress.show();
        Call<AskQuestionResponse> call = RetrofitClient.getInstance().getApi().AskQuestion(LoginActivity.USER.getUMID(),Id,questionTitle,question);
        call.enqueue(new Callback<AskQuestionResponse>() {
            @Override
            public void onResponse(Call<AskQuestionResponse> call, Response<AskQuestionResponse> response) {
                progress.dismiss();
                if (response.isSuccessful()) {
                    if (response.body().isError()){
                        Constants.Alert(AskQuestionEntrepreneur.this,response.body().getErrormsg());
                    }else {
                        new MaterialAlertDialogBuilder(AskQuestionEntrepreneur.this)
                                .setTitle("Success")
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
            public void onFailure(Call<AskQuestionResponse> call, Throwable t) {
                progress.dismiss();
            }
        });
    }

}
