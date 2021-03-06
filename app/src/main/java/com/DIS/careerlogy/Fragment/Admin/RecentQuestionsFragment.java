package com.DIS.careerlogy.Fragment.Admin;

import android.app.Activity;
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

import com.DIS.careerlogy.Network.RetrofitClient;
import com.DIS.careerlogy.Activity.AdminQuestionList;
import com.DIS.careerlogy.Activity.AnswertheQuestionActivity;
import com.DIS.careerlogy.Adapter.AskedQuestionListAdapter;
import com.DIS.careerlogy.Extra.ItemClickListener;
import com.DIS.careerlogy.Extra.MyItemDecoration;
import com.DIS.careerlogy.Extra.Progress;
import com.DIS.careerlogy.Models.AskQuestionByUserResponse;
import com.DIS.careerlogy.Models.AskedQuestionListItem;
import com.DIS.careerlogy.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecentQuestionsFragment extends Fragment {

    public RecentQuestionsFragment() {
        // Required empty public constructor
    }

    RecyclerView rvLearningSub;
    RecyclerView.Adapter entrepreneursSubAdapter;
    private List<AskedQuestionListItem> problemSubCategories = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recent_questions, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                AskedQuestionListItem categoryItem = problemSubCategories.get(pos);
                Intent intent = new Intent(getActivity(), AnswertheQuestionActivity.class);
                intent.putExtra("categoryItem", categoryItem);
                startActivity(intent);
            }
        };

        rvLearningSub = view.findViewById(R.id.rvRecentQuestions);
        rvLearningSub.setHasFixedSize(true);
        rvLearningSub.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvLearningSub.addItemDecoration(new MyItemDecoration());
        entrepreneursSubAdapter = new AskedQuestionListAdapter(getActivity(), problemSubCategories, itemClickListener);
        rvLearningSub.setAdapter(entrepreneursSubAdapter);
        getRecentQuestions(view);
    }

    private void getRecentQuestions(View view) {
        problemSubCategories.clear();
        final Progress progress = new Progress(getActivity());
        progress.show();
        Activity activity = (AdminQuestionList) getActivity();
        Call<AskQuestionByUserResponse> call = RetrofitClient.getInstance().getApi().GetAskQuestionByUser(activity.getIntent().getStringExtra("userType"), "0");
        call.enqueue(new Callback<AskQuestionByUserResponse>() {
            @Override
            public void onResponse(Call<AskQuestionByUserResponse> call, Response<AskQuestionByUserResponse> response) {
                {
                    progress.dismiss();
                    if (response.isSuccessful()) {
                        problemSubCategories.addAll(response.body().getAskedQuestionList());
                        entrepreneursSubAdapter.notifyDataSetChanged();
                    }


                    if (problemSubCategories.isEmpty()) {
                        view.findViewById(R.id.rvError).setVisibility(View.VISIBLE);
                    } else {
                        view.findViewById(R.id.rvError).setVisibility(View.GONE);
                    }

                }
            }

            @Override
            public void onFailure(Call<AskQuestionByUserResponse> call, Throwable t) {
                progress.dismiss();
            }
        });
    }
}
