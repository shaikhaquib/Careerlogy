package com.DIS.careerlogy.Acivity;

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

import com.DIS.careerlogy.Adapter.LearningSubAdapter;
import com.DIS.careerlogy.Network.RetrofitClient;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.DIS.careerlogy.Extra.ItemClickListener;
import com.DIS.careerlogy.Extra.MyItemDecoration;
import com.DIS.careerlogy.Extra.Progress;
import com.DIS.careerlogy.Models.ProblemSubCategoryItem;
import com.DIS.careerlogy.Models.ProblemSubCategoryResponse;
import com.DIS.careerlogy.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentSubCategory extends AppCompatActivity {
    
    RecyclerView rvLearningSub;
    RecyclerView.Adapter learningSubAdapter;
    private List<ProblemSubCategoryItem> problemSubCategories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_subcategory);
        findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AskQuestionStudent.class));
            }
        });
        initoolBar();
        init();
        
    }

    private void initoolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String s = Objects.requireNonNull(getIntent().getStringExtra("title")).trim();

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
                    collapsingToolbarLayout.setTitle(s);
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");//careful there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });
        TextView title= (TextView)findViewById(R.id.title);
        title.setText(s);
    }

    private void init() {

        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                ProblemSubCategoryItem categoryItem = problemSubCategories.get(pos);
                Intent intent = new Intent(getApplicationContext(), QuestionList.class);
                intent.putExtra("probSubCategory",categoryItem.getPSCID());
                intent.putExtra("title",categoryItem.getPSCName());
                intent.putExtra("type", "0");
                startActivity(intent);
            }
        };

        rvLearningSub =findViewById(R.id.rvLearningSub);
        rvLearningSub.setHasFixedSize(true);
        rvLearningSub.setLayoutManager(new LinearLayoutManager(this));
        rvLearningSub.addItemDecoration(new MyItemDecoration());
        learningSubAdapter = new LearningSubAdapter(this,problemSubCategories,itemClickListener);
        rvLearningSub.setAdapter(learningSubAdapter);
        getData();
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

    public void getData(){
        final Progress progress = new Progress(StudentSubCategory.this);
        progress.show();
        Call<ProblemSubCategoryResponse> call = RetrofitClient.getInstance().getApi().problemSubCategory(getIntent().getStringExtra("problemCategoryId"));
        call.enqueue(new Callback<ProblemSubCategoryResponse>() {
            @Override
            public void onResponse(Call<ProblemSubCategoryResponse> call, Response<ProblemSubCategoryResponse> response) {
                progress.dismiss();
                if (response.isSuccessful()) {
                    problemSubCategories.addAll(response.body().getProblemSubCategory());
                    learningSubAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<ProblemSubCategoryResponse> call, Throwable t) {
                progress.dismiss();
            }
        });
    }
}
