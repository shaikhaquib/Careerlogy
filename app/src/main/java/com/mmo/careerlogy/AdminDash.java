package com.mmo.careerlogy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.mmo.careerlogy.Acivity.AdminQuestionList;
import com.mmo.careerlogy.Acivity.UploadFile;
import com.mmo.careerlogy.Acivity.YoutubeLinkUpload;

public class AdminDash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dash);

        findViewById(R.id.adminStudent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AdminQuestionList.class).putExtra("userType", "student"));
            }
        });
        findViewById(R.id.adminEntrepreneur).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AdminQuestionList.class).putExtra("userType", "entrepreneurship"));
            }
        });
        findViewById(R.id.adminUploadDocument).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UploadFile.class));
            }
        });
        findViewById(R.id.adminUploadTestimonial).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), YoutubeLinkUpload.class));
            }
        });

    }
}
