package com.DIS.careerlogy.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.DIS.careerlogy.Fragment.AnswerFragment;
import com.DIS.careerlogy.Network.RetrofitClient;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.DIS.careerlogy.Adapter.QuestionsSubAdapter;
import com.DIS.careerlogy.Extra.Constants;
import com.DIS.careerlogy.Extra.ItemClickListener;
import com.DIS.careerlogy.Extra.MyItemDecoration;
import com.DIS.careerlogy.Extra.Progress;
import com.DIS.careerlogy.LoginActivity;
import com.DIS.careerlogy.Models.QuestionListResponse;
import com.DIS.careerlogy.Models.QuestionsOfProblemSubCategoryItem;
import com.DIS.careerlogy.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.DIS.careerlogy.Activity.QuestionHistory.showDialogFullscreen;

public class QuestionList extends AppCompatActivity {

    RecyclerView rvLearningSub;
    RecyclerView.Adapter entrepreneursSubAdapter;
    List<QuestionsOfProblemSubCategoryItem> questions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);

        initoolBar();
        init();

        findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().getStringExtra("type").equals("0"))
                    startActivity(new Intent(getApplicationContext(), AskQuestionStudent.class));
                else
                    startActivity(new Intent(getApplicationContext(), AskQuestionEntrepreneur.class));
            }
        });

    }

    private void initoolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final CollapsingToolbarLayout collapsingToolbarLayout =  findViewById(R.id.toolbar_layout );
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(getIntent().getStringExtra("title"));
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");//careful there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });

        TextView title= (TextView)findViewById(R.id.title);
        title.setText(getIntent().getStringExtra("title").trim());
    }

    private void init() {

        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                QuestionsOfProblemSubCategoryItem categoryItem = questions.get(pos);
                int id = v.getId();
                if (id == R.id.viewAnswer)
                {
                    Bundle bundle = new Bundle();
                    bundle.putString(AnswerFragment.questinedBy, LoginActivity.USER.getUMName());
                    bundle.putString(AnswerFragment.questinedOn, Constants.Date(categoryItem.getQAddedDateTime()));
                    bundle.putString(AnswerFragment.questinedAnswer, categoryItem.getAAnswer());
                    bundle.putString(AnswerFragment.questinedDesc, categoryItem.getQQuestion());
                    bundle.putString(AnswerFragment.questinedTitle, categoryItem.getQQuestionTitle());

                    showDialogFullscreen(bundle,getSupportFragmentManager());
                }


            }
        };

        rvLearningSub =findViewById(R.id.rvQuestionList);
        rvLearningSub.setHasFixedSize(true);
        rvLearningSub.setLayoutManager(new LinearLayoutManager(this));
        rvLearningSub.addItemDecoration(new MyItemDecoration());
        entrepreneursSubAdapter = new QuestionsSubAdapter(this,questions, itemClickListener);
        rvLearningSub.setAdapter(entrepreneursSubAdapter);
            getCategoryProblem();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void getCategoryProblem() {
        final Progress progress = new Progress(QuestionList.this);
        progress.show();
        Call<QuestionListResponse> call = RetrofitClient.getInstance().getApi().QuestionListOfProbSubCategory(getIntent().getStringExtra("probSubCategory"),"0");
        call.enqueue(new Callback<QuestionListResponse>() {
            @Override
            public void onResponse(Call<QuestionListResponse> call, Response<QuestionListResponse> response) {
                progress.dismiss();
                if (response.isSuccessful()) {
                    questions.addAll(response.body().getQuestionsOfProblemSubCategory());
                    entrepreneursSubAdapter.notifyDataSetChanged();

                    if (questions.isEmpty()) {
                        findViewById(R.id.rvError).setVisibility(View.VISIBLE);
                    } else {
                        findViewById(R.id.rvError).setVisibility(View.GONE);
                    }
                }

            }

            @Override
            public void onFailure(Call<QuestionListResponse> call, Throwable t) {
                progress.dismiss();
            }
        });
    }
}

