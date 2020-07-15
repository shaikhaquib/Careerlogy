package com.DIS.careerlogy.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.DIS.careerlogy.Network.RetrofitClient;
import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.DIS.careerlogy.Extra.Constants;
import com.DIS.careerlogy.Extra.Progress;
import com.DIS.careerlogy.LoginActivity;
import com.DIS.careerlogy.Models.OTPResponse;
import com.DIS.careerlogy.Models.RegisterResponse;
import com.DIS.careerlogy.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserVerification extends AppCompatActivity {

    PinEntryEditText pinEntryEditText;
    TextView resendOtp;
    Progress progress;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otplayout);
        pinEntryEditText = findViewById(R.id.txt_pin_entry);
        resendOtp = findViewById(R.id.resendOtp);
        progress = new Progress(UserVerification.this);
        pinEntryEditText.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
            @Override
            public void onPinEntered(CharSequence str) {
                VerifyUser(String.valueOf(str));
            }
        });
        resendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResendOtp(UserVerification.this, getIntent().getStringExtra("mob"));
            }
        });

    }

    private void VerifyUser(String otp) {
        progress.show();
        Call<RegisterResponse>call= RetrofitClient.getInstance().getApi().VerifyUser(getIntent().getStringExtra("mob"),otp);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                progress.dismiss();
                if (response.isSuccessful()) {
                    if (response.body().isError()) {
                        Constants.Alert(UserVerification.this, response.body().getMessage());
                    } else {
                        new MaterialAlertDialogBuilder(UserVerification.this)
                                .setTitle("Verified")
                                .setMessage(response.body().getMessage())
                                .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(UserVerification.this, "Login to continue", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP));
                                    }
                                })
                        .show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                progress.dismiss();
            }
        });
    }


    public static void ResendOtp(final Context context,String mob) {
        final ProgressDialog progress = new ProgressDialog(context);
        progress.setMessage("Sending OTP...");
       progress.show();
        Call<OTPResponse>call= RetrofitClient.getInstance().getApi().ResendOTP(mob);
        call.enqueue(new Callback<OTPResponse>() {
            @Override
            public void onResponse(Call<OTPResponse> call, Response<OTPResponse> response) {
                    progress.dismiss();
                if (response.isSuccessful()) {
                    if (response.body().isError()) {
                        Constants.Alert(context, response.body().getErrormsg());
                    } else {
                        Toast.makeText(context, response.body().getErrormsg(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<OTPResponse> call, Throwable t) {
                progress.dismiss();
            }
        });
    }

}
