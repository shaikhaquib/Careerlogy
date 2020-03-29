package com.mmo.careerlogy;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.mmo.careerlogy.Acivity.AdminQuestionList;
import com.mmo.careerlogy.Acivity.UploadFile;
import com.mmo.careerlogy.Acivity.YoutubeLinkUpload;
import com.mmo.careerlogy.Extra.Constants;
import com.mmo.careerlogy.Extra.PopSplash;
import com.mmo.careerlogy.Extra.SessionManager;
import com.mmo.careerlogy.Network.UserDatabase;

import static com.mmo.careerlogy.LoginActivity.USER;

public class AdminDash extends AppCompatActivity {

    UserDatabase userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dash);
        userDatabase = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, Constants.DATABASE_NAME).build();

        new PopSplash(AdminDash.this).show();

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
        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void[] objects) {
                        userDatabase.dbAccess().deleteUser(USER);
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
