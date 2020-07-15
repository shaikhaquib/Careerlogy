package com.DIS.careerlogy.Extra;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;

import com.DIS.careerlogy.Network.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class GetDownloadLink extends AsyncTask<String, Integer, String> {

    ProgressDialog progress;
    Context context;

    public GetDownloadLink(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progress = new ProgressDialog(context);
        progress.setMessage("Downloading files...");
        progress.setCancelable(false);
        progress.show();
    }

    @Override
    protected String doInBackground(String... strings) {
        {
            //Log.d("file path", filePath);
            String responseString = null;
            try {

                String charset = "UTF-8";
                String requestURL = RetrofitClient.BASE_URL + "DocumentURL";

                AndroidMultiPartEntity multipart = null;
                multipart = new AndroidMultiPartEntity(requestURL, charset);
                multipart.addFormField("docType", "Document");

                responseString = multipart.finish(); // response from server.

            } catch (IOException e) {
                e.printStackTrace();
                responseString = e.toString();
            }

            return responseString;
        }

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            JSONObject jsonObject = new JSONObject(s);
            File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File pdfFile = new File(folder, "careerlogy_documets");
            if (FileDownloader.downloadFile(jsonObject.getString("PolicyDocumentUrl"), pdfFile)) {
                progress.dismiss();
            } else {
                progress.dismiss();
                Constants.Alert(context, "Some thing went wrong while downloading file.\n Please Check your Internet connection or contact admin");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
