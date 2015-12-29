package com.example.redme.whodoneit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;


//Now that we have the abstract class SingleFragmentActivity we can just use it to generically
//instantiate whatever fragment we want
//Just override the createFragment() method and return the actual fragment you desire
//in this case it is OffenseFragment
public class OffensePagerActivity extends AppCompatActivity {

    private ViewPager view_pager;
    private List<Offense> offense_list;
    private static final String EXTRA_OFFENSE_ID="com.example.redme.whodoneit.offense_id";

    public static Intent newIntent(Context packageContext, UUID offense_id){
        Intent intent = new Intent(packageContext,OffensePagerActivity.class);
        intent.putExtra(EXTRA_OFFENSE_ID,offense_id);
        return intent;
    }



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offense_pager);


        UUID offense_id = (UUID) getIntent().getSerializableExtra(EXTRA_OFFENSE_ID);


        //link up the view_paper to the layout by id
        //then get a pointer to the data list
        view_pager = (ViewPager) findViewById(R.id.activity_offense_pager_viewpager);
        offense_list = OffenseSingleton.get(this).getOffenses();

        //as usuall all fragments need a fragment manager
        FragmentManager fm = getSupportFragmentManager();

        //view_pager adapter require logic regarding how to get an item by index and how to get the size
        //of the list of data it will be handling. Luckily those were previously coded in our model.
        view_pager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Offense offense = offense_list.get(position);
                return OffenseFragment.newInstance(offense.getUserId());
            }

            @Override
            public int getCount() {
                return offense_list.size();
            }
        });

        //get the index of the desired offense and set it in the view pager
        for(int i=0; i<offense_list.size(); i++){
            if (offense_list.get(i).getUserId().equals(offense_id)){
                view_pager.setCurrentItem(i);
                break;
            }
        }
    }


}
