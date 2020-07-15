package com.DIS.careerlogy;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.DIS.careerlogy.Activity.UserVerification;
import com.DIS.careerlogy.Extra.Constants;
import com.DIS.careerlogy.Extra.Progress;
import com.DIS.careerlogy.Extra.SessionManager;
import com.DIS.careerlogy.Models.LoginResponse;
import com.DIS.careerlogy.Models.UserinfoItem;
import com.DIS.careerlogy.Network.RetrofitClient;
import com.DIS.careerlogy.Network.UserDatabase;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText loginUserName;
    TextInputEditText loginPassword;
    Progress progress;
    private static final String TAG = "LoginActivity";

    private UserDatabase userDatabase;
    SessionManager sessionManager;
    public static UserinfoItem USER = new UserinfoItem();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progress = new Progress(this);
        userDatabase = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, Constants.DATABASE_NAME).
                fallbackToDestructiveMigration().build();


        loginUserName = findViewById(R.id.loginUserName);
        loginPassword = findViewById(R.id.loginPassword);
        sessionManager = new SessionManager(this);

        if (sessionManager.isLoggedIn())
            new GetUsersAsyncTask().execute();






        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Registration.class));
            }
        });
        findViewById(R.id.signIN).setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
         //       startActivity(new Intent(getApplicationContext(), MainActivity.class));
                String strUserName = Objects.requireNonNull(loginUserName.getText()).toString();
                String strPassword = Objects.requireNonNull(loginPassword.getText()).toString();
                if (strUserName.isEmpty()){
                    Constants.Alert(LoginActivity.this,"Please Enter Registered Mobile Number or Email Id");
                }else if (strPassword.isEmpty()){
                    Constants.Alert(LoginActivity.this,"Please Enter Your Password");
                }
                else {
                    doLogin(strUserName,strPassword);
                }
            }
        });

    }

    private void doLogin(final String strUserName, final String strPassword) {
        progress.show();
        Call<LoginResponse> loginResponseCall = RetrofitClient.getInstance().getApi().Login(strUserName, strPassword);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, final Response<LoginResponse> response) {
                progress.dismiss();
                assert response.body() != null;
                if (response.body().isError()){
                    Constants.Alert(LoginActivity.this,response.body().getMessage());
                }else {
                    if (response.body().getMessage().equals("Your account is not verified !"))
                    {
                        new MaterialAlertDialogBuilder(LoginActivity.this)
                                .setTitle("Alert")
                                .setMessage(response.body().getMessage())
                                .setPositiveButton("Verify", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(LoginActivity.this, "Login to continue", Toast.LENGTH_SHORT).show();
                                        UserVerification.ResendOtp(LoginActivity.this, strUserName);
                                        startActivity(new Intent(getApplicationContext(), UserVerification.class).putExtra("mob",strUserName));
                                    }
                                }).show();

                    }else
                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... voids) {
                            UserinfoItem userinfoItem = response.body().getUserinfo().get(0);
                            userDatabase.dbAccess().insertUser(userinfoItem);
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);
                            sessionManager.setLogin(true);
                           new GetUsersAsyncTask().execute();
                        }
                    }.execute();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progress.dismiss();
                Constants.Alert(LoginActivity.this,t.getMessage());
            }
        });
    }

    private class GetUsersAsyncTask extends AsyncTask<Void, Void, List<UserinfoItem>>
    {
        Progress progress;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = new Progress(LoginActivity. this);
            progress.show();
        }

        @Override
        protected List<UserinfoItem> doInBackground(Void... url) {
            return userDatabase.dbAccess().getUserDetail();
        }

        @Override
        protected void onPostExecute(List<UserinfoItem> userinfoItems) {
            super.onPostExecute(userinfoItems);
            try {
               if (userinfoItems.get(0).getUMUserStatus().equals("Verified")){
                   Log.d(TAG, "onPostExecute: "+userinfoItems.get(0).getUMName());
                     USER = userinfoItems.get(0);
                     if (USER.getUMType().equalsIgnoreCase("U")){
                     startActivity(new Intent(getApplicationContext(),MainActivity.class));
                     finish();}else {
                         startActivity(new Intent(getApplicationContext(),AdminDash.class));
                         finish();
                     }
               }else {
                   startActivity(new Intent(getApplicationContext(), UserVerification.class).putExtra("mob",userinfoItems.get(0).getUMName()));
               }
            } catch (Exception e) {
                e.printStackTrace();
            }
            progress.dismiss();
        }

    }

}
