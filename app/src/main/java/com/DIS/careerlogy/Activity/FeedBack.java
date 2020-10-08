package com.DIS.careerlogy.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.DIS.careerlogy.Extra.Constants;
import com.DIS.careerlogy.LoginActivity;
import com.DIS.careerlogy.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class FeedBack extends AppCompatActivity {

    EditText f1, f2, f3, f4, f5, f6, f7;
    RadioGroup radioButton;
    String subject = "Careerlogy Feedback";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        setContentView(R.layout.activity_feed_back);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        f1 = findViewById(R.id.f1);
        f2 = findViewById(R.id.f2);
        f3 = findViewById(R.id.f3);
        f4 = findViewById(R.id.f4);
        f5 = findViewById(R.id.f5);
        f6 = findViewById(R.id.f6);
        f7 = findViewById(R.id.f7);

        radioButton = findViewById(R.id.radioGroup);
        TextView txtType = findViewById(R.id.txtType);

        radioButton.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton radioButton = findViewById(checkedId);
                if (radioButton.getTag().equals("1")) {
                    txtType.setText("What are the educational problems not included in these application?");
                    f7.setTag("What are the educational problems not included in these application?");
                    subject = "Careerlogy Feedback for Student";
                } else {
                    txtType.setText("What are the entrepreneurial problems not included in these application?");
                    f7.setTag("What are the entrepreneurial problems not included in these application?");
                    subject = "Careerlogy Feedback for Entrepreneurship";
                }

            }
        });


        setSupportActionBar(toolbar);


        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle("Feedback");
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");//careful there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
           /* Global.interstitialAd(getApplicationContext());
            Global.CLICKCOUNT++;*/
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void feedback(View view) {

       /* Global.interstitialAd(getApplicationContext());
        Global.CLICKCOUNT++;*/
        if (f1.getText().toString().isEmpty()) {
            Toast.makeText(this, "This field is required", Toast.LENGTH_SHORT).show();
            f1.setError("Email field is required");
        } else if (f2.getText().toString().isEmpty()) {
            Toast.makeText(this, "This field is required", Toast.LENGTH_SHORT).show();
            f2.setError("Email field is required");
        } else if (f3.getText().toString().isEmpty()) {
            Toast.makeText(this, "This field is required", Toast.LENGTH_SHORT).show();
            f3.setError("Email field is required");
        } else if (f4.getText().toString().isEmpty()) {
            Toast.makeText(this, "This field is required", Toast.LENGTH_SHORT).show();
            f4.setError("Email field is required");
        } else if (f5.getText().toString().isEmpty()) {
            Toast.makeText(this, "This field is required", Toast.LENGTH_SHORT).show();
            f5.setError("Email field is required");
        } else if (f6.getText().toString().isEmpty()) {
            Toast.makeText(this, "This field is required", Toast.LENGTH_SHORT).show();
            f6.setError("Email field is required");
        } else if (f7.getText().toString().isEmpty()) {
            Toast.makeText(this, "This field is required", Toast.LENGTH_SHORT).show();
            f7.setError("Email field is required");
        } else {

            String mailBody =
                    "User Details :" + "\n\n" +
                            "User Name :" + LoginActivity.USER.getUMName() + "\n" +
                            "Mobile No :" + LoginActivity.USER.getUMMobileNo() + "\n" +
                            "Email Id  :" + LoginActivity.USER.getUMEmailID() + "\n\n" +
                            "Question :" + f1.getTag().toString() + "\n" + "Answer :" + f1.getText().toString() + "\n\n" +
                            "Question :" + f2.getTag().toString() + "\n" + "Answer :" + f2.getText().toString() + "\n\n" +
                            "Question :" + f3.getTag().toString() + "\n" + "Answer :" + f3.getText().toString() + "\n\n" +
                            "Question :" + f4.getTag().toString() + "\n" + "Answer :" + f4.getText().toString() + "\n\n" +
                            "Question :" + f5.getTag().toString() + "\n" + "Answer :" + f5.getText().toString() + "\n\n" +
                            "Question :" + f6.getTag().toString() + "\n" + "Answer :" + f6.getText().toString() + "\n\n" +
                            "Question :" + f7.getTag().toString() + "\n" + "Answer :" + f7.getText().toString() + "\n\n";


            new SendEmail().execute(subject, mailBody);
        }


    }

    public class SendEmail extends AsyncTask<String, Integer, Integer> {

        ProgressDialog progressDialog;
        private StringBuilder all_email;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(FeedBack.this);
            progressDialog.setMessage("Recording Your feedback...");
            progressDialog.show();
        }

        @Override
        protected Integer doInBackground(String... strings) {

            Properties props = new Properties();
            props.put("mail.smtp.host", "mail.careerlogy.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication("no-reply@careerlogy.com", "(hV)Ee}&s{78");
                        }
                    });

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("no-reply@careerlogy.com"));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse("contact.careerlogy@gmail.com"));
                message.setSubject(strings[0]);
                message.setText(strings[1]);

                Transport.send(message);

                System.out.println("Done");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            return 1;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            progressDialog.dismiss();

            new MaterialAlertDialogBuilder(FeedBack.this)
                    .setTitle("Alert")
                    .setMessage("Your feedback has been recorded")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .show();

        }
    }
}
