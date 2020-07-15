package com.DIS.careerlogy.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.DIS.careerlogy.Extra.AndroidMultiPartEntity;
import com.DIS.careerlogy.Extra.Constants;
import com.DIS.careerlogy.Extra.FileUtils;
import com.DIS.careerlogy.FileUtilKt;
import com.DIS.careerlogy.LoginActivity;
import com.DIS.careerlogy.Network.RetrofitClient;
import com.DIS.careerlogy.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class UploadQuaotes extends AppCompatActivity {

    private static final int REQUEST_WRITE_PERMISSION = 1001;
    private static final String TAG = "UploadQuaotes";
    MaterialCardView btnAttach;
    TextView filePath;
    Button btnSubmit;
    String uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_quaotes);
        btnAttach = findViewById(R.id.btnAttach);
        filePath = findViewById(R.id.filePath);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnAttach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermission()) {
                    openFilePicker();
                } else {
                    requestPermission();
                }
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (filePath.getText().toString().isEmpty())
                    Constants.Alert(UploadQuaotes.this, "Please Select File");
                else
                    new UploadQuaotes.UploadQuaotesToServer().execute(uri);
            }
        });

        initToolbar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


    private void initToolbar() {
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
        } else {
            openFilePicker();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[]
            permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_WRITE_PERMISSION:
                if (grantResults.length > 0 && permissions[0].equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    // check whether storage permission granted or not.
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        openFilePicker();
                    }
                }
                break;
            default:
                break;
        }
    }

    public Boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(UploadQuaotes.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }
    }

    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (null != data) { // checking empty selection
                Uri tturi = data.getData();
                uri = FileUtilKt.getPath(getApplication(), tturi);
                filePath.setText(uri);

            }
        }
    }

    private void UploadQuaotes(Uri fileUri) {
    }

    private class UploadQuaotesToServer extends AsyncTask<String, Integer, String> {

        ProgressDialog progress = new ProgressDialog(UploadQuaotes.this);

        @Override
        protected void onPreExecute() {
            progress.setMessage("Uploading files...");
            progress.setCancelable(false);
            progress.show();
        }

        @Override
        protected String doInBackground(String... uris) {
            //Log.d("file path", filePath);
            String responseString = null;
            try {

                String charset = "UTF-8";
                String requestURL = RetrofitClient.BASE_URL + "Qoutes";

                AndroidMultiPartEntity multipart = null;
                multipart = new AndroidMultiPartEntity(requestURL, charset);

                multipart.addFormField("option", "insert");
                multipart.addFormField("docType", "Document");
                multipart.addFilePart("document", FileUtils.getFile(UploadQuaotes.this, Uri.parse(uris[0])));

                responseString = multipart.finish(); // response from server.

            } catch (IOException e) {
                e.printStackTrace();
                responseString = e.toString();
            }

            return responseString;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progress.dismiss();
            if (s != null) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    if (jsonObject.getBoolean("error")) {
                        Constants.Alert(UploadQuaotes.this, jsonObject.getString("errormsg"));
                    } else {
                        new MaterialAlertDialogBuilder(UploadQuaotes.this)
                                .setTitle("Alert")
                                .setMessage(jsonObject.getString("errormsg"))
                                .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                })
                                .show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}