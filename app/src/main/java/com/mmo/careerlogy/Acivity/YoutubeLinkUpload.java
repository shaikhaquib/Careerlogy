package com.mmo.careerlogy.Acivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.mmo.careerlogy.Extra.Constants;
import com.mmo.careerlogy.Extra.Progress;
import com.mmo.careerlogy.Models.UploadTestimonialResponse;
import com.mmo.careerlogy.Network.RetrofitClient;
import com.mmo.careerlogy.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mmo.careerlogy.LoginActivity.USER;

public class YoutubeLinkUpload extends AppCompatActivity {

    TextInputEditText link;
    TextInputEditText title;
    TextInputEditText desc;
    Button btnSubmitvideo;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_link_upload);

        link = findViewById(R.id.videoLink);
        title = findViewById(R.id.videoTitle);
        desc = findViewById(R.id.videoDesc);
        btnSubmitvideo = findViewById(R.id.btnSubmitvideo);

        btnSubmitvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidForm()) {
                    uploadVideo();
                }
            }
        });
    }

    private void uploadVideo() {
        final Progress progress = new Progress(YoutubeLinkUpload.this);
        progress.show();
        Call<UploadTestimonialResponse> addYouTubeLink = RetrofitClient.getInstance().getApi().AddYouTubeLink(link.getText().toString(),
                title.getText().toString(),
                desc.getText().toString(),
                USER.getUMID());
        addYouTubeLink.enqueue(new Callback<UploadTestimonialResponse>() {
            @Override
            public void onResponse(Call<UploadTestimonialResponse> call, Response<UploadTestimonialResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body().isError()) {
                        Constants.Alert(YoutubeLinkUpload.this, response.body().getErrormsg());
                    } else {
                        new MaterialAlertDialogBuilder(YoutubeLinkUpload.this)
                                .setTitle("Alert")
                                .setMessage(response.body().getErrormsg())
                                .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                })
                                .show();
                    }
                }

            }

            @Override
            public void onFailure(Call<UploadTestimonialResponse> call, Throwable t) {
                progress.dismiss();
            }
        });
    }

    public Boolean isValidForm() {

        if (link.getText().toString().isEmpty()) {
            Constants.Alert(YoutubeLinkUpload.this, "Please Enter Youtube Video Link");
            link.setError("Please Enter Youtube Video Link");
            return false;
        } else if (title.getText().toString().isEmpty()) {
            Constants.Alert(YoutubeLinkUpload.this, "Please Enter Testimonial Title");
            title.setError("Please Enter Testimonial Title");
            return false;
        } else if (desc.getText().toString().isEmpty()) {
            Constants.Alert(YoutubeLinkUpload.this, "Please Enter Testimonial Description");
            desc.setError("Please Enter Testimonial Description");
            return false;
        } else {
            return true;
        }

    }
}
