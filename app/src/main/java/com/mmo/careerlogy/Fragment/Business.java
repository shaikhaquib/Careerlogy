package com.mmo.careerlogy.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mmo.careerlogy.Acivity.AskQuestionEntrepreneur;
import com.mmo.careerlogy.Acivity.EntrepreneurSubCategory;
import com.mmo.careerlogy.Adapter.EntrepreneursAdapter;
import com.mmo.careerlogy.Extra.ItemClickListener;
import com.mmo.careerlogy.Extra.MyItemDecoration;
import com.mmo.careerlogy.Extra.Progress;
import com.mmo.careerlogy.Extra.UpdateTitle;
import com.mmo.careerlogy.MainActivity;
import com.mmo.careerlogy.Models.ProblemCategory;
import com.mmo.careerlogy.Models.ProblemCategoryItem;
import com.mmo.careerlogy.Network.RetrofitClient;
import com.mmo.careerlogy.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Business extends Fragment {



    MainActivity activity;
    UpdateTitle updateTitle;

    private RecyclerView rvEntrepreneursCategory;
    RecyclerView.Adapter entrepreneursAdapter;
    private List<ProblemCategoryItem> problemCategories = new ArrayList<>();

    public Business() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        activity= (MainActivity) getActivity();
        updateTitle = activity.updateTitle;
    //    updateTitle.updateData("Entrepreneurs");

        return inflater.inflate(R.layout.fragment_buisness, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvEntrepreneursCategory = view.findViewById(R.id.rvEntrepreneursCategory);
        rvEntrepreneursCategory.setHasFixedSize(true);
        rvEntrepreneursCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvEntrepreneursCategory.addItemDecoration(new MyItemDecoration());

        view.findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AskQuestionEntrepreneur.class));
            }
        });

        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                ProblemCategoryItem categoryItem = problemCategories.get(pos);
                Intent intent = new Intent(getActivity(), EntrepreneurSubCategory.class);
                intent.putExtra("problemCategoryId",categoryItem.getPCID());
                intent.putExtra("title",categoryItem.getPCName());
                startActivity(intent);
            }
        };

        entrepreneursAdapter = new EntrepreneursAdapter(getActivity(),problemCategories,itemClickListener);
        rvEntrepreneursCategory.setAdapter(entrepreneursAdapter);
        getCategory();
    }

    public void getCategory() {
        final Progress progress = new Progress(getActivity());
        progress.show();
        Call<ProblemCategory> call= RetrofitClient.getInstance().getApi().problemCategory("entrepreneurship");
        call.enqueue(new Callback<ProblemCategory>() {
            @Override
            public void onResponse(Call<ProblemCategory> call, Response<ProblemCategory> response) {
                progress.dismiss();
                if (response.isSuccessful()) {
                    problemCategories .addAll( response.body().getProblemCategory());
                    entrepreneursAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ProblemCategory> call, Throwable t) {
                progress.dismiss();
            }
        });
    }


}
