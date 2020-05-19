package com.DIS.careerlogy.Fragment.Admin;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.DIS.careerlogy.Fragment.AnswerFragment;
import com.DIS.careerlogy.Network.RetrofitClient;
import com.DIS.careerlogy.Acivity.AdminQuestionList;
import com.DIS.careerlogy.Adapter.RecentlyAnswerdAdapter;
import com.DIS.careerlogy.Extra.Constants;
import com.DIS.careerlogy.Extra.ItemClickListener;
import com.DIS.careerlogy.Extra.MyItemDecoration;
import com.DIS.careerlogy.Extra.Progress;
import com.DIS.careerlogy.LoginActivity;
import com.DIS.careerlogy.Models.RecentResponse;
import com.DIS.careerlogy.Models.RecentlyAnsweredQuestionsItem;
import com.DIS.careerlogy.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecentAnswerFragment extends Fragment {

    RecyclerView rvLearningSub;
    RecyclerView.Adapter adapter;
    EditNameDialogListener listener;
    private List<RecentlyAnsweredQuestionsItem> recentlyAnsweredQuestionsItems = new ArrayList<>();


    public RecentAnswerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recent_answer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                RecentlyAnsweredQuestionsItem recentlyAnsweredQuestionsItem2 = recentlyAnsweredQuestionsItems.get(pos);
                int id = v.getId();
                if (id == R.id.viewAnswer)
                {
                    Bundle bundle = new Bundle();
                    bundle.putString(AnswerFragment.questinedBy, LoginActivity.USER.getUMName());
                    bundle.putString(AnswerFragment.questinedOn, Constants.Date(recentlyAnsweredQuestionsItem2.getQAddedDateTime()));
                    bundle.putString(AnswerFragment.questinedAnswer, recentlyAnsweredQuestionsItem2.getAAnswer());
                    bundle.putString(AnswerFragment.questinedDesc, recentlyAnsweredQuestionsItem2.getQQuestion());
                    bundle.putString(AnswerFragment.questinedTitle, recentlyAnsweredQuestionsItem2.getQQuestionTitle());
                    bundle.putString(AnswerFragment.AnswerID, recentlyAnsweredQuestionsItem2.getAID());

                    showDialogFullscreen(bundle,getFragmentManager());
                }
            }
        };

        rvLearningSub = view.findViewById(R.id.rvRecentAnswered);
        rvLearningSub.setHasFixedSize(true);
        rvLearningSub.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvLearningSub.addItemDecoration(new MyItemDecoration());
        adapter = new RecentlyAnswerdAdapter(getActivity(), recentlyAnsweredQuestionsItems, itemClickListener);
        rvLearningSub.setAdapter(adapter);
        getRecentQuestions(view);

        listener = new EditNameDialogListener() {
            @Override
            public void onFinishEditDialog(boolean isRefresh) {
                if (isRefresh) {
                    getRecentQuestions(view);
                }
            }
        };
    }

    private void getRecentQuestions(View view) {
        recentlyAnsweredQuestionsItems.clear();
        final Progress progress = new Progress(getActivity());
        progress.show();
        Activity activity = (AdminQuestionList) getActivity();
        String userType = "";
        if(activity.getIntent().getStringExtra("userType").equalsIgnoreCase("student")){
            userType = "student";
        }else if(activity.getIntent().getStringExtra("userType").equalsIgnoreCase("entrepreneurship")){
            userType = "entrepreneurship";
        }
        Call<RecentResponse> call = RetrofitClient.getInstance().getApi().GetRecentlyAnsweredQuestions("0", userType);
        call.enqueue(new Callback<RecentResponse>() {
            @Override
            public void onResponse(Call<RecentResponse> call, Response<RecentResponse> response) {
                {
                    progress.dismiss();
                    RecentResponse recentResponse = response.body();
                    if (response.isSuccessful()) {
                        if(recentResponse.getRecentlyAnsweredQuestions() != null && recentResponse.getRecentlyAnsweredQuestions().size()>0){
                            recentlyAnsweredQuestionsItems.addAll(response.body().getRecentlyAnsweredQuestions());
                            adapter.notifyDataSetChanged();
                        }
                    }

                    if (recentlyAnsweredQuestionsItems.isEmpty()) {
                        view.findViewById(R.id.rvError).setVisibility(View.VISIBLE);
                    } else {
                        view.findViewById(R.id.rvError).setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<RecentResponse> call, Throwable t) {
                progress.dismiss();
            }
        });
    }


    public void showDialogFullscreen(Bundle args, FragmentManager fragmentManager) {
        AnswerFragment newFragment = new AnswerFragment(listener);
        newFragment.setRequestCode(9003);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        newFragment.setArguments(args);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, newFragment).addToBackStack(null).commit();
        newFragment.setOnCallbackResult(new AnswerFragment.CallbackResult() {
            @Override
            public void sendResult(int requestCode, Object obj) {
                if (requestCode == 9003) {

                }
            }
        });
    }

    public interface EditNameDialogListener {
        void onFinishEditDialog(boolean inputText);
    }

}
