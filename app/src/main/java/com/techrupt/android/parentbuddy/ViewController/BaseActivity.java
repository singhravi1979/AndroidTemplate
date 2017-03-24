package com.techrupt.android.parentbuddy.ViewController;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.techrupt.android.parentbuddy.R;

import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by ravindersingh on 17/01/17.
 */

public abstract class BaseActivity extends AppCompatActivity  {

    public abstract void setContent() throws ParseException, IOException, JSONException;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContent();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
