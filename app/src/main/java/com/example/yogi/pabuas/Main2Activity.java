package com.example.yogi.pabuas;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,FragmentListener {

    protected FirstFragment fragment1;
    protected SecondFragment fragment2;
    protected ThirdFragment fragment3;
    protected FrameLayout frameLayout;
    private android.support.v4.app.FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        this.fragment1 = FirstFragment.newInstance("New Fragment 1");
        this.fragment2 = SecondFragment.newInstance("New Fragment 2");
        this.fragment3 = ThirdFragment.newInstance("New Fragment 3");
        this.fragmentManager = this.getSupportFragmentManager();

        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        ft.add(R.id.fragment_container,this.fragment1).addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_highScore) {
            // Handle the camera action
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_exit) {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void changePage(int page) {
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if(page==1){
            if(this.fragment2.isAdded()){
                ft.remove(this.fragment2);
            }
            if(this.fragment3.isAdded()){
                ft.remove(this.fragment3);
            }
            if(this.fragment1.isAdded()){
                ft.show(this.fragment1);
            }
            else if(!this.fragment1.isAdded()){
                ft.replace(R.id.fragment_container,this.fragment1);
            }

        }
        else if(page==2){
            if(this.fragment1.isAdded()){
                ft.remove(this.fragment1);
            }
//            if(this.fragment3.isAdded()){
//                ft.hide(this.fragment3);
//            }
            if(this.fragment2.isAdded()){
                ft.show(this.fragment2);
            }
            else if (!this.fragment2.isAdded()){
                ft.replace(R.id.fragment_container,this.fragment2).addToBackStack(null);
            }

        }
        else if(page==3){
            if(this.fragment1.isAdded()){
                ft.hide(this.fragment1);
            }
            if(this.fragment2.isAdded()){
                ft.hide(this.fragment2);
            }
            if(this.fragment3.isAdded()){
                ft.show(this.fragment3);
            }
            else if(!this.fragment3.isAdded()){
                ft.replace(R.id.fragment_container,this.fragment3);
            }

            Bundle args = new Bundle();
            args.putString("value",fragment2.hasilWaktu);
            this.fragment2.setArguments(args);

        }
        ft.commit();
    }

}
