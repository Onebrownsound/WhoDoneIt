package com.example.redme.whodoneit;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by redme on 12/21/2015.
 */

/*The purpose of this class is to implement the single factory design pattern to ensure only a single
 collection of offenses */

public class OffenseSingleton {
    private List<Offense> offense_list;
    private static OffenseSingleton offense_singleton;




    //method loops through offense_list searching for an id, return null on failure
    public Offense getOffense(UUID desired_offense_id){
        for (Offense i : offense_list){
            if (desired_offense_id.equals(i.getUserId())){
                return i;
            }
        }
        return null;
    }
    public List<Offense> getOffenses(){
        return offense_list;
    }
    public static OffenseSingleton get (Context context){
        if (offense_singleton==null){
            offense_singleton = new OffenseSingleton(context);
        }
        return offense_singleton;
    }

    //o0o0o a private constructor, this needs to be like this so you ensure you cannot just keep calling
    //new constructor, the only way to get access it via the above static method
    //which in turn assures that it will return a pointer to a new OffenseSingleton if null
    //interesting how you can cleverly utilize OOP private/public on constructors to achieve the goal
    private OffenseSingleton(Context context){
        offense_list = new ArrayList<>();
        for(int i = 0; i<100; i++){
            Offense dummy_offense = new Offense();
            dummy_offense.setSolved(i % 2 == 0);
            dummy_offense.setTitle(Integer.toString(i));
            dummy_offense.setOffense_description("Dummy Description");
            offense_list.add(dummy_offense);
        }

    }
}
