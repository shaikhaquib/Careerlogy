package com.mmo.careerlogy;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mmo.careerlogy.Extra.Constants;
import com.mmo.careerlogy.Extra.SessionManager;
import com.mmo.careerlogy.Extra.UpdateTitle;
import com.mmo.careerlogy.Models.UserinfoItem;
import com.mmo.careerlogy.Network.UserDatabase;

import static com.mmo.careerlogy.LoginActivity.USER;

public class MainActivity extends AppCompatActivity {

    NavController navController;
    BottomNavigationView bottomNav;
    public UpdateTitle updateTitle;
    TextView title;
    UserDatabase userDatabase;
    UserinfoItem userinfoItem = new UserinfoItem();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        userDatabase = Room.databaseBuilder(getApplicationContext(),UserDatabase.class, Constants.DATABASE_NAME).build();

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
        Toast.makeText(this, USER.getUMName(), Toast.LENGTH_SHORT).show();

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater =  getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mnLogout) {
            new AsyncTask<Void,Void,Void>() {
                @Override
                protected Void doInBackground(Void[] objects) {
                    userDatabase.dbAccess().deleteUser(USER);
                    return null;
                }
            }.execute();
            new SessionManager(this).setLogin(false);
            finish();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

}