package com.DIS.careerlogy.Fragment;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.DIS.careerlogy.Extra.Constants;
import com.DIS.careerlogy.MainActivity;
import com.DIS.careerlogy.Network.RetrofitClient;
import com.DIS.careerlogy.Activity.AskQuestionStudent;
import com.DIS.careerlogy.Activity.StudentSubCategory;
import com.DIS.careerlogy.Adapter.StudentAdapter;
import com.DIS.careerlogy.Extra.ItemClickListener;
import com.DIS.careerlogy.Extra.MyItemDecoration;
import com.DIS.careerlogy.Extra.Progress;
import com.DIS.careerlogy.Extra.UpdateTitle;
import com.DIS.careerlogy.Models.ProblemCategory;
import com.DIS.careerlogy.Models.ProblemCategoryItem;
import com.DIS.careerlogy.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Student extends Fragment {


    private MainActivity activity;
    private UpdateTitle updateTitle;
    private RecyclerView rvStudentCategory;
    RecyclerView.Adapter studentAdapter;
    private List<ProblemCategoryItem> problemCategories = new ArrayList<>();
    private static final String TAG = "Student";
    View view;

    public Student() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        activity= (MainActivity) getActivity();
        updateTitle = activity.updateTitle;
     //   updateTitle.updateData("Student");
        return inflater.inflate(R.layout.fragment_student, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        getActivity().registerReceiver(mNotificationReceiver, new IntentFilter("subscribed"));
        rvStudentCategory = view.findViewById(R.id.rvStudentCategory);
        view.findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AskQuestionStudent.class));
            }
        });
        rvStudentCategory.setHasFixedSize(true);
        rvStudentCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvStudentCategory.addItemDecoration(new MyItemDecoration());

        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                ProblemCategoryItem categoryItem = problemCategories.get(pos);
                Intent intent = new Intent(getActivity(),StudentSubCategory.class);
                intent.putExtra("problemCategoryId", categoryItem.getPCID());
                intent.putExtra("title", categoryItem.getPCName());
                startActivity(intent);
            }
        };

        studentAdapter = new StudentAdapter(getActivity(), problemCategories, itemClickListener);
        rvStudentCategory.setAdapter(studentAdapter);
        getCategory();

        view.findViewById(R.id.getSubscription).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.startPayment();
            }
        });

        view.findViewById(R.id.haveCoupon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.redeemCouponDialoge();
            }
        });


    }

    public void getCategory() {
        final Progress progress =new Progress(getActivity());
        progress.show();
        Call<ProblemCategory> call= RetrofitClient.getInstance().getApi().problemCategory("student");
        call.enqueue(new Callback<ProblemCategory>() {
            @Override
            public void onResponse(Call<ProblemCategory> call, Response<ProblemCategory> response) {
                progress.dismiss();
                if (response.isSuccessful()) {
                   problemCategories .addAll( response.body().getProblemCategory());
                   studentAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ProblemCategory> call, Throwable t) {
                progress.dismiss();
            }
        });
    }

    private BroadcastReceiver mNotificationReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive: ");
            if (Constants.isSubscribed) {
                view.findViewById(R.id.subscribed).setVisibility(View.VISIBLE);
                view.findViewById(R.id.floatingActionButton).setVisibility(View.VISIBLE);
                view.findViewById(R.id.notsubscribed).setVisibility(View.GONE);

            } else {
                view.findViewById(R.id.subscribed).setVisibility(View.GONE);
                view.findViewById(R.id.floatingActionButton).setVisibility(View.GONE);
                view.findViewById(R.id.notsubscribed).setVisibility(View.VISIBLE);
                TextView textView = view.findViewById(R.id.textView);
                if (Constants.isHasSubscription) {
                    textView.setText("Your subscription has been expired, please get a subscription to continue");
                }
            }

            //  updateUi();
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(mNotificationReceiver, new IntentFilter("subscribed"));
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(mNotificationReceiver);
    }

}
