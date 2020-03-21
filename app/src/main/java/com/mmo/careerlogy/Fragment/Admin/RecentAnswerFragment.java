package com.mmo.careerlogy.Fragment.Admin;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mmo.careerlogy.Acivity.AdminQuestionList;
import com.mmo.careerlogy.Adapter.QuestionsHistAdapter;
import com.mmo.careerlogy.Adapter.RecentlyAnswerdAdapter;
import com.mmo.careerlogy.Extra.Constants;
import com.mmo.careerlogy.Extra.ItemClickListener;
import com.mmo.careerlogy.Extra.MyItemDecoration;
import com.mmo.careerlogy.Extra.Progress;
import com.mmo.careerlogy.Fragment.DialogFullscreenFragment;
import com.mmo.careerlogy.LoginActivity;
import com.mmo.careerlogy.Models.RecentResponse;
import com.mmo.careerlogy.Models.RecentlyAnsweredQuestionsItem;
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
public class RecentAnswerFragment extends Fragment {


    RecyclerView rvLearningSub;
    RecyclerView.Adapter adapter;
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
                    bundle.putString(DialogFullscreenFragment.questinedBy, LoginActivity.USER.getUMName());
                    bundle.putString(DialogFullscreenFragment.questinedOn, Constants.Date(recentlyAnsweredQuestionsItem2.getQAddedDateTime()));
                    bundle.putString(DialogFullscreenFragment.questinedAnswer,recentlyAnsweredQuestionsItem2.getAAnswer());
                    bundle.putString(DialogFullscreenFragment.questinedDesc,recentlyAnsweredQuestionsItem2.getQQuestion());
                    bundle.putString(DialogFullscreenFragment.questinedTitle,recentlyAnsweredQuestionsItem2.getQQuestionTitle());

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
        getRecentQuestions();
    }

    private void getRecentQuestions() {
        recentlyAnsweredQuestionsItems.clear();
        final Progress progress = new Progress(getActivity());
        progress.show();
        Activity activity = (AdminQuestionList) getActivity();
        String userType = "";
        if(activity.getIntent().getStringExtra("userType").equalsIgnoreCase("student")){
            userType = "Student";
        }else if(activity.getIntent().getStringExtra("userType").equalsIgnoreCase("entrepreneurship")){
            userType = "Entrepreneur";
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
                }
            }

            @Override
            public void onFailure(Call<RecentResponse> call, Throwable t) {
                progress.dismiss();
            }
        });
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
