package com.DIS.careerlogy.Acivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.DIS.careerlogy.Fragment.Admin.RecentAnswerFragment;
import com.DIS.careerlogy.Fragment.Admin.RecentQuestionsFragment;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.DIS.careerlogy.R;

public class AdminQuestionList extends AppCompatActivity {

    TextView title;
    Boolean isSelectedQuestions = true;
    TextView tabQuestions;
    TextView tabAnswer;
    RecentAnswerFragment answerFragment;
    RecentQuestionsFragment questionsFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_question_list);
        title = findViewById(R.id.title);

        answerFragment = new RecentAnswerFragment();
        questionsFragment = new RecentQuestionsFragment();

        frgTransaction(questionsFragment);

        tabQuestions = findViewById(R.id.tabQuestions);
        tabAnswer    = findViewById(R.id.tabAnswer);
        tabQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isSelectedQuestions){
                    tabQuestions.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    tabAnswer.setBackgroundColor(getResources().getColor(android.R.color.white));

                    tabQuestions.setTextColor(getResources().getColor(android.R.color.white));
                    tabAnswer.setTextColor(getResources().getColor(android.R.color.black));

                    frgTransaction(questionsFragment);
                    isSelectedQuestions = true ;
                }
            }
        });
        tabAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSelectedQuestions){
                    tabAnswer.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    tabQuestions.setBackgroundColor(getResources().getColor(android.R.color.white));

                    tabAnswer.setTextColor(getResources().getColor(android.R.color.white));
                    tabQuestions.setTextColor(getResources().getColor(android.R.color.black));

                    frgTransaction(answerFragment);
                    isSelectedQuestions = false ;
                }
            }
        });



        initToolbar();
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


    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final CollapsingToolbarLayout collapsingToolbarLayout =  findViewById(R.id.collapsing_toolbar);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle("");
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");//careful there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });


    }

    public void frgTransaction(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frgHolder, fragment);
        transaction.commit();
    }


}
