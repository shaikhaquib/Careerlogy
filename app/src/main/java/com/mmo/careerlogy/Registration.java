package com.mmo.careerlogy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ((Button) findViewById(R.id.material_text_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoiceDialog((Button) v ,new String[]{"Male" , "Female"});
            }
        });
        ((Button) findViewById(R.id.dropDownAge)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoiceDialog((Button) v ,new String[]{"18 - 20" , "21 - 30","31 - 40" , "41 - 50"});
            }
        });
        ((Button) findViewById(R.id.dropDownState)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoiceDialog((Button) v ,new String[]{"Maharashtra" , "Delhi" ,"Punjab","Other"});
            }
        });
        ((Button) findViewById(R.id.dropDownCity)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoiceDialog((Button) v ,new String[]{"Mumbai" , "Kolhapur","Pune" , "Nagpur"});
            }
        });
    }

    private void showChoiceDialog(final Button v, final String[] strings) {
        final MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setCancelable(true);
        builder.setItems(strings,  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                v.setText(strings[which]);
            }
        });
        builder.show();
    }
}
