package com.DIS.careerlogy;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import com.DIS.careerlogy.Activity.FeedBack;
import com.DIS.careerlogy.Activity.ProfileActivity;
import com.DIS.careerlogy.Activity.QuotesList;
import com.DIS.careerlogy.Extra.EntrepreneursInstruction;
import com.DIS.careerlogy.Extra.PopSplash;
import com.DIS.careerlogy.Extra.StudentInstruction;
import com.DIS.careerlogy.Models.DownloadlinksModel;
import com.DIS.careerlogy.Models.UserinfoItem;
import com.DIS.careerlogy.Network.RetrofitClient;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.DIS.careerlogy.Activity.AskQuestionEntrepreneur;
import com.DIS.careerlogy.Activity.AskQuestionStudent;
import com.DIS.careerlogy.Activity.QuestionHistory;
import com.DIS.careerlogy.Adapter.ViewPagerAdapter;
import com.DIS.careerlogy.Extra.Constants;
import com.DIS.careerlogy.Extra.SessionManager;
import com.DIS.careerlogy.Extra.UpdateTitle;
import com.DIS.careerlogy.Fragment.Article;
import com.DIS.careerlogy.Fragment.Business;
import com.DIS.careerlogy.Fragment.Graph;
import com.DIS.careerlogy.Fragment.Student;
import com.DIS.careerlogy.Fragment.Testimonial;
import com.DIS.careerlogy.Network.UserDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.DIS.careerlogy.LoginActivity.USER;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    BottomNavigationView bottomNav;
    ViewPager viewPager;
    public UpdateTitle updateTitle;
    TextView title;
    ImageView icon;
    UserDatabase userDatabase;
    UserinfoItem userinfoItem = new UserinfoItem();
    MenuItem prevMenuItem;
    ExtendedFloatingActionButton floatingActionButton;
    private AppBarConfiguration mAppBarConfiguration;

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
                String[] arrTitle = {"Learner", "Entrepreneurship", "Graphs", "Articles", "Testimonial Videos"};
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

                    findViewById(R.id.Instructions).setVisibility(View.VISIBLE);
                    findViewById(R.id.Instructions).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            new StudentInstruction(MainActivity.this).show();
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
                    findViewById(R.id.Instructions).setVisibility(View.VISIBLE);
                    findViewById(R.id.Instructions).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            new EntrepreneursInstruction(MainActivity.this).show();
                        }
                    });
                }else {
                    floatingActionButton.setVisibility(View.GONE);
                    findViewById(R.id.Instructions).setVisibility(View.GONE);

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
                                findViewById(R.id.Instructions).setVisibility(View.VISIBLE);
                                findViewById(R.id.Instructions).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        new StudentInstruction(MainActivity.this).show();
                                    }
                                });
                                break;
                            case R.id.entrepreneurFragment:
                                viewPager.setCurrentItem(1);
                                updateTitle.updateData(1);
                                findViewById(R.id.Instructions).setVisibility(View.VISIBLE);
                                findViewById(R.id.Instructions).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        new EntrepreneursInstruction(MainActivity.this).show();
                                    }
                                });
                                break;
                            case R.id.graphFragment:
                                findViewById(R.id.Instructions).setVisibility(View.GONE);
                                viewPager.setCurrentItem(2);
                                updateTitle.updateData(2);
                                break;
                            case R.id.articleFragment:
                                findViewById(R.id.Instructions).setVisibility(View.GONE);
                                viewPager.setCurrentItem(3);
                                updateTitle.updateData(3);
                                break;
                            case R.id.videoFragment:
                                findViewById(R.id.Instructions).setVisibility(View.GONE);
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
        new PopSplash(MainActivity.this).show();

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
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final CollapsingToolbarLayout collapsingToolbarLayout =  findViewById(R.id.collapsing_toolbar);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(title.getText().toString());
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");//careful there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });


        initNavigationMenu(toolbar);
    }

    private void initNavigationMenu(Toolbar toolbar) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder()
                .setDrawerLayout(drawer)
                .build();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
         toggle.setHomeAsUpIndicator(R.drawable.ic_menu);
         toggle.getDrawerArrowDrawable().setColor(Color.BLACK);

      //  toggle.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_menu));
      //  toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();


        final View headerLayout = navigationView.inflateHeaderView(R.layout.nav_header_main);
        TextView userName = headerLayout.findViewById(R.id.hdUName);
        userName.setText(Constants.capitalize(USER.getUMName()));

        // open drawer at start
        // drawer.openDrawer(GravityCompat.START);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.logout:
                new SessionManager(this).setLogin(false);
                finish();
                new AsyncTask<Void,Void,Void>() {
                    @Override
                    protected Void doInBackground(Void[] objects) {
                        userDatabase.dbAccess().deleteUser(USER);
                        return null;
                    }
                }.execute();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                break;
            case R.id.history:
                Intent intent = new Intent(getApplicationContext(), QuestionHistory.class);
                intent.putExtra("title", "History");
                intent.putExtra("type", "0");
                startActivity(intent);
                break;
            case R.id.quote:
                startActivity(new Intent(getApplicationContext(), QuotesList.class));
                break;
            case R.id.profile:
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                break;
            case R.id.download:
                downloadFiles();
                break;
            case R.id.feedback:
                startActivity(new Intent(getApplicationContext(), FeedBack.class));
                break;
            case R.id.about:
                startActivity(new Intent(getApplicationContext(), Disclamer.class));
                break;

        }
        return true;
    }


    public void downloadFiles() {
        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Downloading files...");
        progress.setCancelable(false);
        progress.show();
        Call<DownloadlinksModel> downloadlinksModelCall = RetrofitClient.getInstance().getApi().DocumentURL("Document");
        downloadlinksModelCall.enqueue(new Callback<DownloadlinksModel>() {
            @Override
            public void onResponse(Call<DownloadlinksModel> call, Response<DownloadlinksModel> response) {
                progress.dismiss();
                if (response.isSuccessful()) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(response.body().getPolicyDocumentUrl()));
                    startActivity(browserIntent);
                }
            }

            @Override
            public void onFailure(Call<DownloadlinksModel> call, Throwable t) {

            }
        });


    }

    @Override
    public void onBackPressed() {
        new MaterialAlertDialogBuilder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit " + getString(R.string.app_name))
                .setMessage("Are you sure you want to close the app?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog1, int which) {

                        final Dialog dialog = new Dialog(MainActivity.this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
                        dialog.setContentView(R.layout.dialog_image);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        dialog.setCancelable(false);
                        dialog.show();

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // Do something after 5s = 5000ms
                                dialog.dismiss();
                                finish();
                            }
                        }, 1000);
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}