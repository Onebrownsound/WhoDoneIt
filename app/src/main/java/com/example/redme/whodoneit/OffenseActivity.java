package com.example.redme.whodoneit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;

import java.util.UUID;


//Now that we have the abstract class SingleFragmentActivity we can just use it to generically
//instantiate whatever fragment we want
//Just override the createFragment() method and return the actual fragment you desire
//in this case it is OffenseFragment
public class OffenseActivity extends SingleFragmentActivity {
    public static final String EXTRA_OFFENSE_ID="com.example.redme.whodoneit.offense_id";

    public static Intent newIntent(Context packageContext, UUID offenseId){
        Intent intent = new Intent (packageContext,OffenseActivity.class);
        intent.putExtra(EXTRA_OFFENSE_ID,offenseId);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        return new OffenseFragment();
    }


}
