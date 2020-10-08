package com.DIS.careerlogy;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.Toast;

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
import com.DIS.careerlogy.Extra.Progress;
import com.DIS.careerlogy.Extra.SessionManager;
import com.DIS.careerlogy.Models.CheckSubscribtion;
import com.DIS.careerlogy.Models.CouponGenrateResponse;
import com.DIS.careerlogy.Models.CouponsListItem;
import com.DIS.careerlogy.Network.RetrofitClient;
import com.DIS.careerlogy.Network.UserDatabase;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Parameter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.DIS.careerlogy.Extra.Constants.Alert;
import static com.DIS.careerlogy.LoginActivity.USER;

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
        findViewById(R.id.multipleCoupon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupCoupon();
            }
        });
        findViewById(R.id.singleCoupon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GenrateCouponTask().execute("1");
                //        genareteCoupon("1");
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

    public void groupCoupon() {

        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialoge_groupcoupon);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(lp);
        dialog.show();
        ElegantNumberButton CouponCode = dialog.findViewById(R.id.textInputLayout1);
        dialog.findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(CouponCode.getNumber()) > 0) {
                    dialog.dismiss();
                    new GenrateCouponTask().execute(CouponCode.getNumber());
//                    genareteCoupon(CouponCode.getNumber());
                } else {
                    Toast.makeText(getApplicationContext(), "Value must be greater than 0", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.findViewById(R.id.closeDiloge).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    private void genareteCoupon(String number) {
        final Progress progress = new Progress(this);
        progress.show();
        Call<ResponseBody> call = RetrofitClient.getInstance().getApi().GenerateCoupons(number);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progress.dismiss();
                String s = response.body().toString();
                //     Log.d(TAG, "onResponse: ");
/*
                if (response.isSuccessful()) {
                    if (!response.body().isError())
                    {
                        StringBuilder stringBuilder = new StringBuilder();
                        List<CouponsListItem> couponsList = response.body().getCouponsList();
                        for (int i = 0; i < couponsList.size(); i++) {
                            CouponsListItem couponsListItem =response.body().getCouponsList().get(i);
                            stringBuilder.append("Coupon Code :"+couponsListItem.getCouponCode()+"\n");
                        }
                        new MaterialAlertDialogBuilder(AdminDash.this)
                                .setTitle("Coupon Codes")
                                .setCancelable(false)
                                .setMessage("Your Coupon Code has been Generated Successfully.\n\n\n"+stringBuilder)
                                .setPositiveButton("Share", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                                        sharingIntent.setType("text/plain");
                                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name)+" Joining Coupon Code");
                                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, stringBuilder.toString());
                                        startActivity(Intent.createChooser(sharingIntent, "Share text via"));

                                    }
                                })
                                .show();
                    }else {
                        Constants.Alert(AdminDash.this,"Something went wrong!Please try after some time.");
                    }
                }
*/

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progress.dismiss();
                Alert(AdminDash.this, "Something went wrong!Please try after some time.");
            }
        });
    }

    public class GenrateCouponTask extends AsyncTask<String, Void, String> {
        String server_response;
        String Json;
        HttpURLConnection conn;
        URL url = null;
        Progress progress = new Progress(AdminDash.this);

        @Override
        protected void onPreExecute() {
            progress.show();
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                url = new URL(RetrofitClient.BASE_URL + "GenerateCoupons");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("coupon_count", params[0]);
                String query = builder.build().getEncodedQuery();
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

            } catch (IOException e) {
                e.printStackTrace();
            }
            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());


                } else {

                    return ("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            } finally {
                conn.disconnect();
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progress.dismiss();

            if (!s.equals("unsuccessful")) {
                Gson gson = new Gson();

                CouponGenrateResponse response = gson.fromJson(s, CouponGenrateResponse.class);

                if (!response.isError()) {
                    StringBuilder stringBuilder = new StringBuilder();

                    try {
                        JSONArray jsonArray = new JSONObject(s).getJSONArray("CouponsList");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            stringBuilder.append("Coupon Code :" + object.getString("Coupon_Code") + "\n");
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    new MaterialAlertDialogBuilder(AdminDash.this)
                            .setTitle("Coupon Codes")
                            .setCancelable(false)
                            .setMessage("Your Coupon Code has been Generated Successfully.\n\n\n" + stringBuilder)
                            .setPositiveButton("Share", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                                    sharingIntent.setType("text/plain");
                                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name) + " Joining Coupon Code");
                                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, stringBuilder.toString());
                                    startActivity(Intent.createChooser(sharingIntent, "Share text via"));

                                }
                            })
                            .show();
                } else {
                    Alert(AdminDash.this, "Something went wrong!Please try after some time.");
                }

            }
        }
    }


}
