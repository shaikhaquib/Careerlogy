package com.mmo.careerlogy.Acivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.mmo.careerlogy.Adapter.EntrepreneursSubAdapter;
import com.mmo.careerlogy.Adapter.LearningSubAdapter;
import com.mmo.careerlogy.Extra.MyItemDecoration;
import com.mmo.careerlogy.R;

public class EntrepreneurSubCategory extends AppCompatActivity {


    RecyclerView rvLearningSub;
    RecyclerView.Adapter entrepreneursSubAdapter;
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
    }

    private void init() {
        rvLearningSub =findViewById(R.id.rvLearningSub);
        rvLearningSub.setHasFixedSize(true);
        rvLearningSub.setLayoutManager(new LinearLayoutManager(this));
        rvLearningSub.addItemDecoration(new MyItemDecoration());
        entrepreneursSubAdapter = new EntrepreneursSubAdapter(this);
        rvLearningSub.setAdapter(entrepreneursSubAdapter);
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
}
