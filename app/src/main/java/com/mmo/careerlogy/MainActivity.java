package com.mmo.careerlogy;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.mmo.careerlogy.Acivity.AskQuestionEntrepreneur;
import com.mmo.careerlogy.Acivity.AskQuestionStudent;
import com.mmo.careerlogy.Adapter.ViewPagerAdapter;
import com.mmo.careerlogy.Extra.Constants;
import com.mmo.careerlogy.Extra.SessionManager;
import com.mmo.careerlogy.Extra.UpdateTitle;
import com.mmo.careerlogy.Fragment.Article;
import com.mmo.careerlogy.Fragment.Business;
import com.mmo.careerlogy.Fragment.Graph;
import com.mmo.careerlogy.Fragment.Student;
import com.mmo.careerlogy.Fragment.Testimonial;
import com.mmo.careerlogy.Models.UserinfoItem;
import com.mmo.careerlogy.Network.UserDatabase;

import static com.mmo.careerlogy.LoginActivity.USER;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;
    ViewPager viewPager;
    public UpdateTitle updateTitle;
    TextView title;
    ImageView icon;
    UserDatabase userDatabase;
    UserinfoItem userinfoItem = new UserinfoItem();
    MenuItem prevMenuItem;
    ExtendedFloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        userDatabase = Room.databaseBuilder(getApplicationContext(),UserDatabase.class, Constants.DATABASE_NAME).build();

        //Getting the Navigation Controller
       // navController = Navigation.findNavController(this, R.id.fragment);
        bottomNav = findViewById(R.id.navigation);
        viewPager = findViewById(R.id.viewpager);
        title = findViewById(R.id.title);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setVisibility(View.GONE);
        icon = findViewById(R.id.icon);
        viewPager.setOffscreenPageLimit(5);

        updateTitle = new UpdateTitle() {
            @Override
            public void updateData(int position) {
                String[] arrTitle = {"Student","Entrepreneur","Graphs","Articles","Testimonial Videos"};
                int[] arrIcon = {R.drawable.ic_student,R.drawable.ic_business,R.drawable.ic_graph,R.drawable.ic_articles,R.drawable.ic_video};
                title.setText(arrTitle[position]);
                icon.setImageResource(arrIcon[position]);
                if (position == 0){
                    floatingActionButton.setVisibility(View.VISIBLE);
                    floatingActionButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(getApplicationContext(), AskQuestionStudent.class));
                        }
                    });
                }else if (position == 1){
                    floatingActionButton.setVisibility(View.VISIBLE);
                    floatingActionButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(getApplicationContext(), AskQuestionEntrepreneur.class));
                        }
                    });
                }else {
                    floatingActionButton.setVisibility(View.GONE);
                }

            }
        };
        //Setting the navigation controller to Bottom Nav
        bottomNav.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.studentFragment:
                                viewPager.setCurrentItem(0);
                                updateTitle.updateData(0);
                                break;
                            case R.id.entrepreneurFragment:
                                viewPager.setCurrentItem(1);
                                updateTitle.updateData(1);
                                break;
                            case R.id.graphFragment:
                                viewPager.setCurrentItem(2);
                                updateTitle.updateData(2);
                                break;
                            case R.id.articleFragment:
                                viewPager.setCurrentItem(3);
                                updateTitle.updateData(3);
                                break;
                            case R.id.videoFragment:
                                viewPager.setCurrentItem(4);
                                updateTitle.updateData(4);
                                break;
                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    bottomNav.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: "+position);
                bottomNav.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNav.getMenu().getItem(position);
                updateTitle.updateData(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setupViewPager(viewPager);
        bottomNav.setSelectedItemId(R.id.studentFragment);

    }

    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        Student student =new Student();
        Business business =new Business();
        Graph graph =new Graph();
        Article article =new Article();
        Testimonial testimonial =new Testimonial();

        adapter.addFragment(student);
        adapter.addFragment(business);
        adapter.addFragment(graph);
        adapter.addFragment(article);
        adapter.addFragment(testimonial);

        viewPager.setAdapter(adapter);
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