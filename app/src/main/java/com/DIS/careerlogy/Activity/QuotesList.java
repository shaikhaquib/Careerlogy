package com.DIS.careerlogy.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.DIS.careerlogy.Extra.MyItemDecoration;
import com.DIS.careerlogy.Extra.Progress;
import com.DIS.careerlogy.Models.RecentQoutesItem;
import com.DIS.careerlogy.Models.QoutesResponse;
import com.DIS.careerlogy.Models.YouTubeVideoListItem;
import com.DIS.careerlogy.Network.RetrofitClient;
import com.DIS.careerlogy.R;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuotesList extends AppCompatActivity {

    RecyclerView rvQuotes;
    List<RecentQoutesItem> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes_list);
        initToolbar();

        rvQuotes = findViewById(R.id.rvQuotes);
        rvQuotes.setHasFixedSize(true);
        rvQuotes.setLayoutManager(new LinearLayoutManager(QuotesList.this));
        rvQuotes.addItemDecoration(new MyItemDecoration());
        rvQuotes.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new VideoHolder(LayoutInflater.from(QuotesList.this).inflate(R.layout.quotesitem, parent, false));
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                VideoHolder videoHolder = (VideoHolder) holder;
                Glide.with(QuotesList.this).load(list.get(position).getQoutesUrl()).into(videoHolder.vdThumb);
            }

            @Override
            public int getItemCount() {
                return list.size();
            }

            class VideoHolder extends RecyclerView.ViewHolder {
                ImageView vdThumb;

                public VideoHolder(@NonNull View itemView) {
                    super(itemView);
                    vdThumb = itemView.findViewById(R.id.quote);
                }
            }
        });
        loadVideo();
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
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void loadVideo() {
        final Progress progress = new Progress(this);
        progress.show();
        Call<QoutesResponse> call = RetrofitClient.getInstance().getApi().Qouteslist("list");
        call.enqueue(new Callback<QoutesResponse>() {
            @Override
            public void onResponse(Call<QoutesResponse> call, Response<QoutesResponse> response) {
                progress.dismiss();
                if (response.isSuccessful()) {
                    list.addAll(response.body().getRecentQoutes());
                    rvQuotes.getAdapter().notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<QoutesResponse> call, Throwable t) {
                progress.dismiss();
            }
        });
    }

}