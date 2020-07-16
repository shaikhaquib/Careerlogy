package com.DIS.careerlogy.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.DIS.careerlogy.Adapter.LearningSubAdapter;
import com.DIS.careerlogy.Adapter.SubCatAdapt;
import com.DIS.careerlogy.Extra.Constants;
import com.DIS.careerlogy.Extra.ItemClickListener;
import com.DIS.careerlogy.Extra.MyItemDecoration;
import com.DIS.careerlogy.Extra.Progress;
import com.DIS.careerlogy.LoginActivity;
import com.DIS.careerlogy.Models.CategoryOperationsEditResponse;
import com.DIS.careerlogy.Models.ProbSubCatLstItem;
import com.DIS.careerlogy.Models.ProblemSubCategoryItem;
import com.DIS.careerlogy.Models.ResponseSubCategoryAdmin;
import com.DIS.careerlogy.Network.RetrofitClient;
import com.DIS.careerlogy.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProblemSubCategoryAdmin extends AppCompatActivity {
    RecyclerView rvLearningSub;
    RecyclerView.Adapter learningSubAdapter;
    private List<ProbSubCatLstItem> problemSubCategories = new ArrayList<>();

    private BottomSheetBehavior mBehavior;
    private BottomSheetDialog mBottomSheetDialog;
    private View bottom_sheet;
    private String catId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_sub_category_admin);
        initoolBar();
        init();

    }

    private void initoolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String s = Objects.requireNonNull(getIntent().getStringExtra("title")).trim();

        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
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
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");//careful there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(s);
    }

    private void init() {

        catId = getIntent().getStringExtra("problemCategoryId");

        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                updateCategory("Update " + getIntent().getStringExtra("title") + " Category", problemSubCategories.get(pos));
            }
        };

        rvLearningSub = findViewById(R.id.rvLearningSub);
        rvLearningSub.setHasFixedSize(true);
        rvLearningSub.setLayoutManager(new LinearLayoutManager(this));
        rvLearningSub.addItemDecoration(new MyItemDecoration());

        learningSubAdapter = new SubCatAdapt(ProblemSubCategoryAdmin.this, problemSubCategories, itemClickListener);
        rvLearningSub.setAdapter(learningSubAdapter);
        getData();
        bottom_sheet = findViewById(R.id.bottom_sheet);
        mBehavior = BottomSheetBehavior.from(bottom_sheet);
    }

    private void updateCategory(String title, ProbSubCatLstItem problemSubCategoryItem) {
        if (mBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

        final View view;
        mBottomSheetDialog = new BottomSheetDialog(this);
        mBottomSheetDialog.setCancelable(false);
        view = getLayoutInflater().inflate(R.layout.update_subcategories, null);
        mBottomSheetDialog.setContentView(view);

        TextView txtTitle = view.findViewById(R.id.txtTitle);
        TextInputEditText category = view.findViewById(R.id.category);
        TextInputEditText url = view.findViewById(R.id.url);
        txtTitle.setText(title);
        category.setText(problemSubCategoryItem.getPSCName());
        url.setText(problemSubCategoryItem.getPSCAnswerDocumentURL());


        view.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (category.getText().toString().isEmpty()) {
                    category.setError("Field required");
                } else if (url.getText().toString().isEmpty()) {
                    url.setError("Field required");
                } else {
                    getUpdateCategory(problemSubCategoryItem.getPSCProblemCategoryFK(), problemSubCategoryItem.getPSCID(), category.getText().toString(), url.getText().toString());
                }
            }
        });

        mBottomSheetDialog.show();
        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mBottomSheetDialog = null;
            }
        });

        view.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss();
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

    public void getData() {
        final Progress progress = new Progress(this);
        progress.show();
        Call<ResponseSubCategoryAdmin> call = RetrofitClient.getInstance().getApi().SubCategoryOperationslist("list");
        call.enqueue(new Callback<ResponseSubCategoryAdmin>() {
            @Override
            public void onResponse(Call<ResponseSubCategoryAdmin> call, Response<ResponseSubCategoryAdmin> response) {
                progress.dismiss();
                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().getProbSubCatLst().size(); i++) {
                        ProbSubCatLstItem probSubCatLstItem = response.body().getProbSubCatLst().get(i);
                        if (probSubCatLstItem.getPSCProblemCategoryFK().equals(catId)) {
                            problemSubCategories.add(probSubCatLstItem);
                            learningSubAdapter.notifyDataSetChanged();
                        }


                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseSubCategoryAdmin> call, Throwable t) {
                progress.dismiss();
            }
        });
    }

    public void getUpdateCategory(String catid, String pscid, String name, String url) {
        final Progress progress = new Progress(this);
        progress.show();
        Call<CategoryOperationsEditResponse> call = RetrofitClient.getInstance().getApi().SubCategoryOperations("edit", url, name, catid, pscid, LoginActivity.USER.getUMID());
        call.enqueue(new Callback<CategoryOperationsEditResponse>() {
            @Override
            public void onResponse(Call<CategoryOperationsEditResponse> call, Response<CategoryOperationsEditResponse> response) {
                progress.dismiss();
                if (response.isSuccessful()) {
                    if (response.body().isError()) {
                        Constants.Alert(ProblemSubCategoryAdmin.this, response.body().getErrormsg());
                    } else {
                        new MaterialAlertDialogBuilder(ProblemSubCategoryAdmin.this)
                                .setTitle("Alert")
                                .setMessage(response.body().getErrormsg())
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        problemSubCategories.clear();
                                        learningSubAdapter.notifyDataSetChanged();
                                        mBottomSheetDialog.dismiss();
                                        getData();
                                    }
                                })
                                .show();
                    }
                }
            }

            @Override
            public void onFailure(Call<CategoryOperationsEditResponse> call, Throwable t) {
                progress.dismiss();
            }
        });
    }

}
