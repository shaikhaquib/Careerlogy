package com.mmo.careerlogy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.mmo.careerlogy.Models.StateModel;
import com.mmo.careerlogy.Network.RetrofitClient;

import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration extends AppCompatActivity {
    private static final String TAG = "Registration";

    List<String> state = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        state = getStateList();

        ((Button) findViewById(R.id.material_text_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoiceDialog((Button) v, new String[]{"Male", "Female"});
            }
        });
        ((Button) findViewById(R.id.dropDownAge)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoiceDialog((Button) v, new String[]{"18 - 20", "21 - 30", "31 - 40", "41 - 50"});
            }
        });
        ((Button) findViewById(R.id.dropDownState)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] stockArr = new String[0];
                if (!state.isEmpty()) {
                    stockArr = new String[state.size()];
                    stockArr = state.toArray(stockArr);
                } else {
                    state = getStateList();
                    stockArr = new String[state.size()];
                    stockArr = state.toArray(stockArr);
                }
                showChoiceDialog((Button) v, stockArr);
            }
        });
        ((Button) findViewById(R.id.dropDownCity)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoiceDialog((Button) v, new String[]{"Mumbai", "Kolhapur", "Pune", "Nagpur"});
            }
        });

    }

    private List<String> getStateList() {
        final List<String> list = new ArrayList<>();
        Call<StateModel> stateModelCall = RetrofitClient.getInstance().getApi().stateList();
        stateModelCall.enqueue(new Callback<StateModel>() {
            @Override
            public void onResponse(Call<StateModel> call, Response<StateModel> response) {
                Log.d(TAG, "onResponse: ");
                assert response.body() != null;
                List<StateModel.StateListItem> stateLists = response.body().getStateList();
                for (int i = 0; i < stateLists.size(); i++) {
                    Log.d(TAG, "onResponse: " + stateLists.get(i).getStatename());
                    list.add(stateLists.get(i).getStatename());
                }
            }

            @Override
            public void onFailure(Call<StateModel> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
        return list;

    }

    private void showChoiceDialog(final Button v, final String[] strings) {
        final MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setCancelable(true);
        builder.setItems(strings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                v.setText(strings[which]);
            }
        });
        builder.show();
    }
}
