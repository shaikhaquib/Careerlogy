package com.DIS.careerlogy.Fragment;


import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.DIS.careerlogy.MainActivity;
import com.DIS.careerlogy.Network.RetrofitClient;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.DIS.careerlogy.Extra.Progress;
import com.DIS.careerlogy.Extra.UpdateTitle;
import com.DIS.careerlogy.Models.GraphsResponse;
import com.DIS.careerlogy.Models.ProblemCategory;
import com.DIS.careerlogy.Models.ProblemCategoryItem;
import com.DIS.careerlogy.R;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Graph extends Fragment {


    private BarChart chart,chart2;
    float barWidth;
    float barSpace;
    float groupSpace;
    Button dropDownCategory;
    SwipeRefreshLayout swipeToRefresh;
    MainActivity activity;
    UpdateTitle updateTitle;
    List<ProblemCategoryItem> problemCategories = new ArrayList<>();
    List<String> strProblem = new ArrayList<>();

    public Graph() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        activity= (MainActivity) getActivity();
        updateTitle = activity.updateTitle;
        return inflater.inflate(R.layout.fragment_graph, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        barWidth = 0.3f;
        barSpace = 0f;
        groupSpace = 0.4f;

        dropDownCategory = view.findViewById(R.id.dropDownCategory);
        swipeToRefresh = view.findViewById(R.id.swipeToRefresh);

        swipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                /*if(problemCategories != null && problemCategories.size()>0){
                    fetchCategoryWiseGraph(problemCategories.get(0).getPCID());
                }else{*/
                    fetchCategoryWiseGraph("0");
                //}
                swipeToRefresh.setRefreshing(false);
            }
        });

        chart = view.findViewById(R.id.barChart);
        chart2 = view.findViewById(R.id.barChart2);
        getCategory();
        dropDownCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
                builder.setCancelable(true);
                builder.setTitle("Select Problem Category");
                String[] categoryArray = new String[0];
                if (!strProblem.isEmpty()) {
                    categoryArray = new String[strProblem.size()];
                    categoryArray = strProblem.toArray(categoryArray);
                }
                final String[] finalCategoryArray = categoryArray;
                builder.setItems(categoryArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        dropDownCategory.setText(finalCategoryArray[which]);
                        if (dropDownCategory.getError()!=null)
                            dropDownCategory.setError(null);
                        fetchCategoryWiseGraph(problemCategories.get(which).getPCID());

                    }
                });
                // Create the alert dialog
                AlertDialog dialog = builder.create();

                // Get the alert dialog ListView instance
                ListView listView = dialog.getListView();

                // Set the divider color of alert dialog list view
                listView.setDivider(new ColorDrawable(Color.GRAY));

                // Set the divider height of alert dialog list view
                listView.setDividerHeight(3);
                builder.show();
            }
        });

        fetchCategoryWiseGraph("0");

    }

    private void fetchCategoryWiseGraph(String pcid) {
        final Progress progress =new Progress(getActivity());
        progress.show();
        Call<GraphsResponse> call= RetrofitClient.getInstance().getApi().GetMainGraph(pcid);
        call.enqueue(new Callback<GraphsResponse>() {
            @Override
            public void onResponse(Call<GraphsResponse> call, Response<GraphsResponse> response) {
                progress.dismiss();
                GraphsResponse graphsResponse = response.body();
                if(graphsResponse.getMainGraph() != null && graphsResponse.getMainGraph().size()>0){

                    chart.setDescription(null);
                    chart.setPinchZoom(true);
                    chart.setScaleEnabled(true);
                    chart.setDrawBarShadow(false);
                    chart.setDrawGridBackground(false);

                    int groupCount = graphsResponse.getMainGraph().size();

                    ArrayList xVals = new ArrayList();
                    ArrayList yVals1 = new ArrayList();
                    ArrayList yVals2 = new ArrayList();

                    for (int k=0; k < graphsResponse.getMainGraph().size(); k++) {
                        xVals.add(graphsResponse.getMainGraph().get(k).getAgeGroup());
                        yVals1.add(new BarEntry(1, (float) Integer.parseInt(graphsResponse.getMainGraph().get(k).getMALE())));
                        yVals2.add(new BarEntry(1, (float) Integer.parseInt(graphsResponse.getMainGraph().get(k).getFEMALE())));
                    }

                    BarDataSet set1, set2;
                    set1 = new BarDataSet(yVals1, "Male");
                    set1.setColor(Color.parseColor("#007fed"));
                    set2 = new BarDataSet(yVals2, "Female");
                    set2.setColor(Color.parseColor("#FEAEC9"));
                    BarData data = new BarData(set1, set2);
                    data.setValueFormatter(new LargeValueFormatter());
                    chart.setData(data);
                    chart.getBarData().setBarWidth(barWidth);
                    chart.getXAxis().setAxisMinimum(0);
                    chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
                    chart.groupBars(0, groupSpace, barSpace);
                    chart.getData().setHighlightEnabled(false);
                    chart.invalidate();

                    Legend l = chart.getLegend();
                    l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
                    l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
                    l.setOrientation(Legend.LegendOrientation.VERTICAL);
                    l.setDrawInside(true);
                    l.setYOffset(20f);
                    l.setXOffset(0f);
                    l.setYEntrySpace(0f);
                    l.setTextSize(8f);

                    //X-axis
                    XAxis xAxis = chart.getXAxis();
                    xAxis.setGranularity(1f);
                    xAxis.setGranularityEnabled(true);
                    xAxis.setCenterAxisLabels(true);
                    xAxis.setDrawGridLines(false);
                    xAxis.setLabelRotationAngle(-45);
                    //xAxis.setAxisMaximum(6);
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    xAxis.setValueFormatter(new IndexAxisValueFormatter(xVals));
//Y-axis
                    chart.getAxisRight().setEnabled(false);
                    YAxis leftAxis = chart.getAxisLeft();
                    leftAxis.setGranularity(1f);
                    leftAxis.setGranularityEnabled(true);
                    leftAxis.setValueFormatter(new LargeValueFormatter());
                    leftAxis.setDrawGridLines(true);
                    leftAxis.setSpaceTop(35f);
                    leftAxis.setAxisMinimum(0);
                }

                if(graphsResponse.getCategoryGraph() != null && graphsResponse.getCategoryGraph().size()>0){

                    chart2.setDescription(null);
                    chart2.setPinchZoom(true);
                    chart2.setScaleEnabled(true);
                    chart2.setDrawBarShadow(false);
                    chart2.setDrawGridBackground(false);

                    int groupCount2 = graphsResponse.getCategoryGraph().size();

                    ArrayList xVals2 = new ArrayList();
                    ArrayList yVals3 = new ArrayList();
                    ArrayList yVals4 = new ArrayList();

                    for (int k=0; k < graphsResponse.getCategoryGraph().size(); k++) {
                        xVals2.add(graphsResponse.getCategoryGraph().get(k).getCategoryName());
                        yVals3.add(new BarEntry(1, (float) Integer.parseInt(graphsResponse.getCategoryGraph().get(k).getMale())));
                        yVals4.add(new BarEntry(1, (float) Integer.parseInt(graphsResponse.getCategoryGraph().get(k).getFemale())));
                    }

                    BarDataSet set3, set4;
                    set3 = new BarDataSet(yVals3, "Male");
                    set3.setColor(Color.parseColor("#007fed"));
                    set4 = new BarDataSet(yVals4, "Female");
                    set4.setColor(Color.parseColor("#FEAEC9"));
                    BarData data2 = new BarData(set3, set4);
                    data2.setValueFormatter(new LargeValueFormatter());
                    chart2.setData(data2);
                    chart2.getBarData().setBarWidth(barWidth);
                    chart2.getXAxis().setAxisMinimum(0);
                    chart2.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount2);
                    chart2.groupBars(0, groupSpace, barSpace);
                    chart2.getData().setHighlightEnabled(false);
                    chart2.invalidate();

                    Legend l2 = chart2.getLegend();
                    l2.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
                    l2.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
                    l2.setOrientation(Legend.LegendOrientation.VERTICAL);
                    l2.setDrawInside(true);
                    l2.setYOffset(20f);
                    l2.setXOffset(0f);
                    l2.setYEntrySpace(0f);
                    l2.setTextSize(8f);

                    //X-axis
                    XAxis xAxis2 = chart2.getXAxis();
                    xAxis2.setGranularity(1f);
                    xAxis2.setGranularityEnabled(true);
                    xAxis2.setCenterAxisLabels(true);
                    xAxis2.setDrawGridLines(false);
                    xAxis2.setLabelRotationAngle(-45);
                    //xAxis2.setAxisMaximum(6);
                    xAxis2.setPosition(XAxis.XAxisPosition.BOTTOM);
                    xAxis2.setValueFormatter(new IndexAxisValueFormatter(xVals2));
//Y-axis
                    chart2.getAxisRight().setEnabled(false);
                    YAxis leftAxis2 = chart2.getAxisLeft();
                    leftAxis2.setGranularity(1f);
                    leftAxis2.setGranularityEnabled(true);
                    leftAxis2.setValueFormatter(new LargeValueFormatter());
                    leftAxis2.setDrawGridLines(true);
                    leftAxis2.setSpaceTop(35f);
                    leftAxis2.setAxisMinimum(0f);
                }
            }

            @Override
            public void onFailure(Call<GraphsResponse> call, Throwable t) {
                progress.dismiss();
            }
        });
    }


    public void getCategory() {
        problemCategories.clear();
        strProblem.clear();
        final Progress progress =new Progress(getActivity());
        progress.show();
        Call<ProblemCategory> call= RetrofitClient.getInstance().getApi().problemCategory("ALL");
        call.enqueue(new Callback<ProblemCategory>() {
            @Override
            public void onResponse(Call<ProblemCategory> call, Response<ProblemCategory> response) {
                progress.dismiss();
                if (response.isSuccessful()) {
                    problemCategories.addAll( response.body().getProblemCategory());
                    for (int i = 0 ; i < problemCategories.size() ;i++){
                        ProblemCategoryItem problemCategoryItem = problemCategories.get(i);
                        strProblem.add(problemCategoryItem.getPCName());
                    }
                }
            }

            @Override
            public void onFailure(Call<ProblemCategory> call, Throwable t) {
                progress.dismiss();
            }
        });
    }

}
