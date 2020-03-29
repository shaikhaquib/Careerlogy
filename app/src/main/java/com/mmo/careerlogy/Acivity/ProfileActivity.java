package com.mmo.careerlogy.Acivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.mmo.careerlogy.Extra.Constants;
import com.mmo.careerlogy.LoginActivity;
import com.mmo.careerlogy.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initToolbar();
        TextView pName = findViewById(R.id.pName);
        TextView pMobile = findViewById(R.id.pMobile);
        TextView pEmail = findViewById(R.id.pEmail);
        TextView pDob = findViewById(R.id.pDob);
        TextView pState = findViewById(R.id.pState);
        TextView pCity = findViewById(R.id.pCity);
        TextView pGender = findViewById(R.id.pGender);
        TextView pUserType = findViewById(R.id.pUserType);


        pName.setText(Constants.capitalize(LoginActivity.USER.getUMName()));
        pMobile.setText(LoginActivity.USER.getUMMobileNo());
        pEmail.setText(LoginActivity.USER.getUMEmailID());
        pDob.setText(LoginActivity.USER.getUMDateOfBirth());
        pState.setText(LoginActivity.USER.getUMState());
        pCity.setText(LoginActivity.USER.getUMCity());
        pGender.setText(LoginActivity.USER.getUMGender());
        pUserType.setText(LoginActivity.USER.getUserCategoryName());

    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

}
