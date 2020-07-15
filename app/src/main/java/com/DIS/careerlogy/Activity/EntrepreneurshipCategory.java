package com.DIS.careerlogy.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.DIS.careerlogy.Adapter.EntrepreneursAdapter;
import com.DIS.careerlogy.Extra.Constants;
import com.DIS.careerlogy.Extra.ItemClickListener;
import com.DIS.careerlogy.Extra.MyItemDecoration;
import com.DIS.careerlogy.Extra.Progress;
import com.DIS.careerlogy.LoginActivity;
import com.DIS.careerlogy.Models.CategoryOperationsEditResponse;
import com.DIS.careerlogy.Models.ProblemCategory;
import com.DIS.careerlogy.Models.ProblemCategoryItem;
import com.DIS.careerlogy.Network.RetrofitClient;
import com.DIS.careerlogy.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntrepreneurshipCategory extends AppCompatActivity {

    private RecyclerView rvEntrepreneursCategory;
    RecyclerView.Adapter entrepreneursAdapter;
    private List<ProblemCategoryItem> problemCategories = new ArrayList<>();

    private BottomSheetBehavior mBehavior;
    private BottomSheetDialog mBottomSheetDialog;
    private View bottom_sheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrepreneurship_category);
        initToolbar();


        rvEntrepreneursCategory = findViewById(R.id.rvEntrepreneursCategory);
        rvEntrepreneursCategory.setHasFixedSize(true);
        rvEntrepreneursCategory.setLayoutManager(new LinearLayoutManager(this));
        rvEntrepreneursCategory.addItemDecoration(new MyItemDecoration());


        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

            }
        };

        ItemClickListener longClicklistener = new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                updateCategory("Update Entrepreneurship's Category", problemCategories.get(pos));
            }
        };


        entrepreneursAdapter = new EntrepreneursAdapter(this, problemCategories, itemClickListener, longClicklistener);
        rvEntrepreneursCategory.setAdapter(entrepreneursAdapter);
        getCategory();


        bottom_sheet = findViewById(R.id.bottom_sheet);
        mBehavior = BottomSheetBehavior.from(bottom_sheet);
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
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Entrepreneurship");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void getCategory() {
        final Progress progress = new Progress(this);
        progress.show();
        Call<ProblemCategory> call = RetrofitClient.getInstance().getApi().problemCategory("entrepreneurship");
        call.enqueue(new Callback<ProblemCategory>() {
            @Override
            public void onResponse(Call<ProblemCategory> call, Response<ProblemCategory> response) {
                progress.dismiss();
                if (response.isSuccessful()) {
                    problemCategories.addAll(response.body().getProblemCategory());
                    entrepreneursAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ProblemCategory> call, Throwable t) {
                progress.dismiss();
            }
        });
    }

    public void getUpdateCategory(String id, String name) {
        final Progress progress = new Progress(this);
        progress.show();
        Call<CategoryOperationsEditResponse> call = RetrofitClient.getInstance().getApi().CategoryOperationsEdit("edit", "entrepreneurship", name, id, LoginActivity.USER.getUMID());
        call.enqueue(new Callback<CategoryOperationsEditResponse>() {
            @Override
            public void onResponse(Call<CategoryOperationsEditResponse> call, Response<CategoryOperationsEditResponse> response) {
                progress.dismiss();
                if (response.isSuccessful()) {
                    if (response.body().isError()) {
                        Constants.Alert(EntrepreneurshipCategory.this, response.body().getErrormsg());
                    } else {
                        new MaterialAlertDialogBuilder(EntrepreneurshipCategory.this)
                                .setTitle("Alert")
                                .setMessage(response.body().getErrormsg())
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        problemCategories.clear();
                                        entrepreneursAdapter.notifyDataSetChanged();
                                        getCategory();
                                        mBottomSheetDialog.dismiss();
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

    private void updateCategory(String title, ProblemCategoryItem problemCategoryItem) {
        if (mBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

        final View view;
        mBottomSheetDialog = new BottomSheetDialog(this);
        mBottomSheetDialog.setCancelable(false);
        view = getLayoutInflater().inflate(R.layout.update_category, null);
        mBottomSheetDialog.setContentView(view);

        TextView txtTitle = view.findViewById(R.id.txtTitle);
        TextInputEditText category = view.findViewById(R.id.category);
        txtTitle.setText(title);
        category.setText(problemCategoryItem.getPCName());


        view.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (category.getText().toString().isEmpty()) {
                    category.setError("Field required");
                } else
                    getUpdateCategory(problemCategoryItem.getPCID(), category.getText().toString());
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

}