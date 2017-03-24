package com.techrupt.android.parentbuddy.ViewController;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;

import com.techrupt.android.parentbuddy.R;
import com.techrupt.android.parentbuddy.ViewController.Events.Activity.EventActivityList;

import java.util.Calendar;
import java.util.Date;

public class MainNavigationActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
private FloatingActionButton mEventButton;

    @Override
    public void setContent() {
        setContentView(R.layout.activity_main_navigation);
        mEventButton=(FloatingActionButton) findViewById(R.id.main_activity_btn_Events);
        mEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ScaleAnimation scaleAnimation=new ScaleAnimation(0,1,
                        0,1);
                scaleAnimation.setInterpolator(new LinearInterpolator());
                scaleAnimation.setDuration(1000);
                mEventButton.setAnimation(scaleAnimation);
                mEventButton.animate();
                Calendar calendar=Calendar.getInstance();
                Date fromdate;
                calendar.add(Calendar.DAY_OF_MONTH,-50);
                fromdate=calendar.getTime();
              Intent eventIntent= EventActivityList.newInstance(MainNavigationActivity.this,
                      fromdate,Calendar.getInstance().getTime());
                //eventIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               // startActivity(eventIntent);


            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            // Handle the camera action
        } else if (id == R.id.nav_myevents) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
