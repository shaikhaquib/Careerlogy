package com.mmo.careerlogy.Acivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.mmo.careerlogy.Adapter.QuestionsHistAdapter;
import com.mmo.careerlogy.Extra.Constants;
import com.mmo.careerlogy.Extra.ItemClickListener;
import com.mmo.careerlogy.Extra.MyItemDecoration;
import com.mmo.careerlogy.Extra.Progress;
import com.mmo.careerlogy.Fragment.DialogFullscreenFragment;
import com.mmo.careerlogy.LoginActivity;
import com.mmo.careerlogy.Models.HistoryResponse;
import com.mmo.careerlogy.Models.QuestionsHistoryItem;
import com.mmo.careerlogy.Network.RetrofitClient;
import com.mmo.careerlogy.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionHistory extends AppCompatActivity {
    RecyclerView rvLearningSub;
    RecyclerView.Adapter entrepreneursSubAdapter;
    List<QuestionsHistoryItem> questions = new ArrayList<>();
    int DIALOG_QUEST_CODE = 9001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);

        initoolBar();
        init();

        findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AskQuestionEntrepreneur.class));
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
        title.setText(getIntent().getStringExtra("title"));
    }

    private void init() {

        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                QuestionsHistoryItem categoryItem = questions.get(pos);
                int id = v.getId();
                if (id == R.id.viewAnswer)
                {
                    Bundle bundle = new Bundle();
                    bundle.putString(DialogFullscreenFragment.questinedBy,LoginActivity.USER.getUMName());
                    bundle.putString(DialogFullscreenFragment.questinedOn, Constants.Date(categoryItem.getQAddedDateTime()));
                    bundle.putString(DialogFullscreenFragment.questinedAnswer,categoryItem.getAAnswer());
                    bundle.putString(DialogFullscreenFragment.questinedDesc,categoryItem.getQQuestion());
                    bundle.putString(DialogFullscreenFragment.questinedTitle,categoryItem.getQQuestionTitle());

                    showDialogFullscreen(bundle,getSupportFragmentManager());
                }

            }
        };

        rvLearningSub =findViewById(R.id.rvQuestionList);
        rvLearningSub.setHasFixedSize(true);
        rvLearningSub.setLayoutManager(new LinearLayoutManager(this));
        rvLearningSub.addItemDecoration(new MyItemDecoration());
        entrepreneursSubAdapter = new QuestionsHistAdapter(this,questions, itemClickListener);
        rvLearningSub.setAdapter(entrepreneursSubAdapter);
            getCategoryHistory();

    }

    private void getCategoryHistory() {
        final Progress progress = new Progress(QuestionHistory.this);
        progress.show();
        Call<HistoryResponse> call = RetrofitClient.getInstance().getApi().QuestionHistoryList(LoginActivity.USER.getUMID(),"0");
        call.enqueue(new Callback<HistoryResponse>() {
            @Override
            public void onResponse(Call<HistoryResponse> call, Response<HistoryResponse> response) {
                progress.dismiss();
                if (response.isSuccessful()) {
                    questions.addAll(response.body().getQuestionsHistory());
                    entrepreneursSubAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<HistoryResponse> call, Throwable t) {
                progress.dismiss();
            }
        });
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

    public static void showDialogFullscreen(Bundle args, FragmentManager fragmentManager ) {
        DialogFullscreenFragment newFragment = new DialogFullscreenFragment();
        newFragment.setRequestCode(9003);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        newFragment.setArguments(args);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, newFragment).addToBackStack(null).commit();
        newFragment.setOnCallbackResult(new DialogFullscreenFragment.CallbackResult() {
            @Override
            public void sendResult(int requestCode, Object obj) {
                if (requestCode == 9003) {
                }
            }
        });
    }

}