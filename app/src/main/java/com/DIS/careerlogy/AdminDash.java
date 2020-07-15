package com.DIS.careerlogy;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.DIS.careerlogy.Activity.AdminQuestionList;
import com.DIS.careerlogy.Activity.EntrepreneurshipCategory;
import com.DIS.careerlogy.Activity.LearnerCategory;
import com.DIS.careerlogy.Activity.UploadFile;
import com.DIS.careerlogy.Activity.UploadQuaotes;
import com.DIS.careerlogy.Activity.YoutubeLinkUpload;
import com.DIS.careerlogy.Extra.Constants;
import com.DIS.careerlogy.Extra.PopSplash;
import com.DIS.careerlogy.Extra.SessionManager;
import com.DIS.careerlogy.Network.UserDatabase;

public class AdminDash extends AppCompatActivity {

    UserDatabase userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dash);
        userDatabase = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, Constants.DATABASE_NAME).build();

        //  new PopSplash(AdminDash.this).show();

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
        findViewById(R.id.adminUploadQuotes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UploadQuaotes.class));
            }
        });
        findViewById(R.id.studentCategory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LearnerCategory.class));
            }
        });
        findViewById(R.id.entrepreneurCategory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), EntrepreneurshipCategory.class));
            }
        });
        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void[] objects) {
                        userDatabase.dbAccess().deleteUser(LoginActivity.USER);
                        return null;
                    }
                }.execute();
                new SessionManager(AdminDash.this).setLogin(false);
                finish();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

    }
}
