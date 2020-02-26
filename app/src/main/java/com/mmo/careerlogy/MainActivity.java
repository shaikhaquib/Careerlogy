package com.mmo.careerlogy;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mmo.careerlogy.Extra.UpdateTitle;

public class MainActivity extends AppCompatActivity {

    NavController navController;
    BottomNavigationView bottomNav;
    public UpdateTitle updateTitle;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();

        //Getting the Navigation Controller
        navController = Navigation.findNavController(this, R.id.fragment);
        bottomNav = findViewById(R.id.navigation);
        title = findViewById(R.id.title);
        //Setting the navigation controller to Bottom Nav
        NavigationUI.setupWithNavController(bottomNav, navController);

        updateTitle = new UpdateTitle() {
            @Override
            public void updateData(String Data) {
                title.setText(Data);
            }
        };

    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
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