package com.example.redme.whodoneit;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;

public class OffenseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offense);


        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        //The purpose of a fragment managers mediate between activity and fragments
        //Since we "shove" the fragments into this activity we need this mediator.
        //We need to test if our instance of fragment is null and if so instantiate a new fragment

        //Remember that the id.fragment_container corresponds to activity_offense.xml's id
        //We set that id up manually in that xml file
        if (fragment == null) {
            fragment = new OffenseFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }

    }




}
