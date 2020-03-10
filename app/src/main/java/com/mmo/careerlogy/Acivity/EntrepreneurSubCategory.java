package com.mmo.careerlogy.Acivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.mmo.careerlogy.Adapter.EntrepreneursSubAdapter;
import com.mmo.careerlogy.Extra.MyItemDecoration;
import com.mmo.careerlogy.Extra.Progress;
import com.mmo.careerlogy.Models.ProblemSubCategoryItem;
import com.mmo.careerlogy.Models.ProblemSubCategoryResponse;
import com.mmo.careerlogy.Network.RetrofitClient;
import com.mmo.careerlogy.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntrepreneurSubCategory extends AppCompatActivity {


    RecyclerView rvLearningSub;
    RecyclerView.Adapter entrepreneursSubAdapter;
    private List<ProblemSubCategoryItem> problemSubCategories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrepreneur_subcategory);
        initoolBar();
        init();

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
        title.setText(getIntent().getStringExtra("title"));
    }

    private void init() {
        rvLearningSub =findViewById(R.id.rvLearningSub);
        rvLearningSub.setHasFixedSize(true);
        rvLearningSub.setLayoutManager(new LinearLayoutManager(this));
        rvLearningSub.addItemDecoration(new MyItemDecoration());
        entrepreneursSubAdapter = new EntrepreneursSubAdapter(this,problemSubCategories);
        rvLearningSub.setAdapter(entrepreneursSubAdapter);
        getCategory();
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

    public void getCategory() {
        final Progress progress = new Progress(EntrepreneurSubCategory.this);
        progress.show();
        Call<ProblemSubCategoryResponse> call = RetrofitClient.getInstance().getApi().problemSubCategory(getIntent().getStringExtra("problemCategoryId"));
        call.enqueue(new Callback<ProblemSubCategoryResponse>() {
            @Override
            public void onResponse(Call<ProblemSubCategoryResponse> call, Response<ProblemSubCategoryResponse> response) {
                progress.dismiss();
                if (response.isSuccessful()) {
                    problemSubCategories.addAll(response.body().getProblemSubCategory());
                    entrepreneursSubAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<ProblemSubCategoryResponse> call, Throwable t) {
                progress.dismiss();
            }
        });
    }
}
