package com.example.redme.whodoneit;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;

public class OffenseListActivity extends SingleFragmentActivity {

 @Override
 protected Fragment createFragment(){

     return new OffenseListFragment();
 }




}
