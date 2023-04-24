package com.example.sparkle;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;

import com.example.sparkle.ui.HirerReview.HirerReview;
import com.example.sparkle.ui.HirerReview.ViewHirerReviewFragment;
import com.example.sparkle.ui.HirerReview.ViewHirerReviewViewModel;
import com.example.sparkle.ui.Job.ClientJob;
import com.example.sparkle.ui.Job.JobFragment;
import com.example.sparkle.ui.Job.ViewJobFragment;
import com.example.sparkle.ui.Login.LoginFragment;
import com.example.sparkle.ui.Login.RegisterFragment;
import com.example.sparkle.ui.backload.BlankFragment;
import com.example.sparkle.ui.backload.ClnBlankFragment;
import com.example.sparkle.ui.backload.FrontLoginFragment;
import com.example.sparkle.ui.backload.FrontRegisterFragment;
import com.example.sparkle.ui.cleanerLogin.ViewCleanerFragment;
import com.example.sparkle.ui.jobApplyCleaner.ApplyJobFragment;
import com.example.sparkle.ui.jobApplyCleaner.ViewAppliedJobsFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sparkle.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {






    public ClientJob job;
    public HirerReview hReview;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    boolean register = false;
    boolean status = false;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,R.id.nav_job)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);


        getSupportFragmentManager().popBackStack();
        FragmentTransaction trans =getSupportFragmentManager().beginTransaction();


        SharedPreference preference= new SharedPreference();
        status = preference.GetBoolean(getApplicationContext(),SharedPreference.KEY_STATUS);
        boolean Customer = preference.GetBoolean(getApplicationContext(),SharedPreference.CUSTOMER);
        boolean Cleaner = preference.GetBoolean(getApplicationContext(),SharedPreference.CLEANER);
        String  name =  preference.GetString(getApplicationContext(),SharedPreference.KEY_USER_NAME);
        if (name!=null)
        {
            register= true;
        }


        if (register){

            if(status) {
                BlankFragment fragment = new BlankFragment();
                trans.replace(R.id.nav_host_fragment_content_main, fragment);
                trans.addToBackStack(null);
                trans.commit();

                if (Customer){

                    Menu menu = navigationView.getMenu();
                    MenuItem item=menu.findItem(R.id.nav_home);
                    item.setVisible(true);
                    item=menu.findItem(R.id.cln_home);
                    item.setVisible(false);
                    item=menu.findItem(R.id.nav_job);
                    item.setVisible(true);
                    item=menu.findItem(R.id.cln_job);
                    item.setVisible(false);
                    item=menu.findItem(R.id.nav_viewJob);
                    item.setVisible(true);
                    item=menu.findItem(R.id.nav_freelan);
                    item.setVisible(true);
                    item=menu.findItem(R.id.nav_logout);
                    item.setVisible(true);
                    item=menu.findItem(R.id.nav_exit);
                    item.setVisible(true);
                    item=menu.findItem(R.id.nav_accepted_job);
                    item.setVisible(true);
                    item=menu.findItem(R.id.h_review);
                    item.setVisible(true);

                }
                else if(Cleaner){
                    Menu menu = navigationView.getMenu();
                    MenuItem item=menu.findItem(R.id.nav_home);
                    item.setVisible(false);
                    item=menu.findItem(R.id.cln_home);
                    item.setVisible(true);
                    item=menu.findItem(R.id.nav_job);
                    item.setVisible(false);
                    item=menu.findItem(R.id.cln_job);
                    item.setVisible(true);
                    item=menu.findItem(R.id.nav_viewJob);
                    item.setVisible(false);
                    item=menu.findItem(R.id.nav_freelan);
                    item.setVisible(false);
                    item=menu.findItem(R.id.nav_logout);
                    item.setVisible(true);
                    item=menu.findItem(R.id.nav_exit);
                    item.setVisible(true);
                    item=menu.findItem(R.id.nav_accepted_job);
                    item.setVisible(false);
                    item=menu.findItem(R.id.h_review);
                    item.setVisible(true);


                }

            }
            else {
                LoginFragment fragment = new LoginFragment();
                trans.replace(R.id.nav_host_fragment_content_main, fragment);
                trans.addToBackStack(null);
                trans.commit();

                Menu menu = navigationView.getMenu();
                MenuItem item=menu.findItem(R.id.nav_home);
                item.setVisible(false);
                item=menu.findItem(R.id.cln_home);
                item.setVisible(false);
                item=menu.findItem(R.id.nav_job);
                item.setVisible(false);
                item=menu.findItem(R.id.cln_job);
                item.setVisible(false);
                item=menu.findItem(R.id.nav_viewJob);
                item.setVisible(false);
                item=menu.findItem(R.id.nav_freelan);
                item.setVisible(false);
                item=menu.findItem(R.id.nav_logout);
                item.setVisible(true);
                item=menu.findItem(R.id.nav_exit);
                item.setVisible(true);
                item=menu.findItem(R.id.nav_accepted_job);
                item.setVisible(false);
                item=menu.findItem(R.id.h_review);
                item.setVisible(false);



            }
        }
        else {
            FrontRegisterFragment fragment = new FrontRegisterFragment();
            trans.replace(R.id.nav_host_fragment_content_main,fragment);
            trans.addToBackStack(null);
            trans.commit();


            Menu menu = navigationView.getMenu();
            MenuItem item=menu.findItem(R.id.nav_home);
            item.setVisible(false);
            item=menu.findItem(R.id.cln_home);
            item.setVisible(false);
            item=menu.findItem(R.id.nav_job);
            item.setVisible(false);
            item=menu.findItem(R.id.cln_job);
            item.setVisible(false);
            item=menu.findItem(R.id.nav_viewJob);
            item.setVisible(false);
            item=menu.findItem(R.id.nav_freelan);
            item.setVisible(false);
            item=menu.findItem(R.id.nav_logout);
            item.setVisible(true);
            item=menu.findItem(R.id.nav_exit);
            item.setVisible(true);

        }




        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int menuID =item.getItemId();
                getSupportFragmentManager().popBackStack();
                FragmentTransaction trans = getSupportFragmentManager().beginTransaction();

                if(menuID==R.id.nav_home){
                    BlankFragment fragment = new BlankFragment();
                    trans.replace(R.id.nav_host_fragment_content_main,fragment);
                }

                else if (menuID==R.id.nav_job){
                    JobFragment fragment = new JobFragment();
                    trans.replace(R.id.nav_host_fragment_content_main,fragment);
                }
                else if (menuID==R.id.nav_viewJob){
                    ViewJobFragment fragment = new ViewJobFragment();
                    trans.replace(R.id.nav_host_fragment_content_main,fragment);
                }

                else if (menuID==R.id.h_review){
                    ViewHirerReviewFragment fragment = new  ViewHirerReviewFragment();
                    trans.replace(R.id.nav_host_fragment_content_main,fragment);
                }
                else if (menuID==R.id.nav_accepted_job){
                    ViewAppliedJobsFragment fragment = new    ViewAppliedJobsFragment();
                    trans.replace(R.id.nav_host_fragment_content_main,fragment);
                }

                else if (menuID==R.id.cln_home){
                    ClnBlankFragment fragment = new ClnBlankFragment();
                    trans.replace(R.id.nav_host_fragment_content_main,fragment);
                }
                else if (menuID==R.id.cln_job){
                    ApplyJobFragment fragment = new ApplyJobFragment();
                    trans.replace(R.id.nav_host_fragment_content_main,fragment);
                }
                else if (menuID==R.id.nav_freelan){
                    ViewCleanerFragment fragment = new  ViewCleanerFragment();
                    trans.replace(R.id.nav_host_fragment_content_main,fragment);
                }


                else if (menuID==R.id.nav_logout){
                    FrontLoginFragment fragment = new FrontLoginFragment();
                    trans.replace(R.id.nav_host_fragment_content_main,fragment);
                    trans.addToBackStack(null);
                    preference.SaveBool(getApplicationContext(),false,SharedPreference.KEY_STATUS);
                    preference.SaveBool(getApplicationContext(),false,SharedPreference.CUSTOMER);
                    preference.SaveBool(getApplicationContext(),false,SharedPreference.CLEANER);
//

                }


                else if (menuID==R.id.nav_exit){
                finish();
            }
                trans.addToBackStack(null);
                trans.commit();
                drawer.closeDrawer(GravityCompat.START);
                return false;
        }
    });


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}





























