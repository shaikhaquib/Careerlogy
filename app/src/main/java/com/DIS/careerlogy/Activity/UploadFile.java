package com.DIS.careerlogy.Activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.DIS.careerlogy.Extra.AndroidMultiPartEntity;
import com.DIS.careerlogy.FileUtilKt;
import com.DIS.careerlogy.Network.RetrofitClient;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.DIS.careerlogy.Extra.Constants;
import com.DIS.careerlogy.Extra.FileUtils;
import com.DIS.careerlogy.LoginActivity;
import com.DIS.careerlogy.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class UploadFile extends AppCompatActivity {

    private static final int REQUEST_WRITE_PERMISSION = 1001;
    private static final String TAG = "UploadFile";
    MaterialCardView btnAttach;
    TextView filePath;
    Button btnSubmit;
    String uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_file);
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
                    Constants.Alert(UploadFile.this, "Please Select File");
                else
                    new UploadFileToServer().execute(uri);
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
        if (ContextCompat.checkSelfPermission(UploadFile.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }
    }

    private void openFilePicker() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("*/*");
            startActivityForResult(intent, 1);
        } else {
            Intent intent = new Intent();
            intent.setType("*/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, 1);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (null != data) { // checking empty selection
                Uri tturi = data.getData();
                uri = FileUtilKt.getPath(UploadFile.this, tturi);
                filePath.setText(uri);
            }
        }
    }

    private void uploadFile(Uri fileUri) {
    }

    private class UploadFileToServer extends AsyncTask<String, Integer, String> {

        ProgressDialog progress = new ProgressDialog(UploadFile.this);

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
                String requestURL = RetrofitClient.BASE_URL + "UploadDocument";

                AndroidMultiPartEntity multipart = null;
                multipart = new AndroidMultiPartEntity(requestURL, charset);

                multipart.addFormField("userId", LoginActivity.USER.getUMID());
                multipart.addFormField("docType", "Document");
                multipart.addFilePart("document", FileUtils.getFile(UploadFile.this, Uri.parse(uris[0])));

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
                        Constants.Alert(UploadFile.this, jsonObject.getString("errormsg"));
                    } else {
                        new MaterialAlertDialogBuilder(UploadFile.this)
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
