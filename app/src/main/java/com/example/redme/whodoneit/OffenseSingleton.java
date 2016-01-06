package com.example.redme.whodoneit;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.redme.whodoneit.database.OffenseBaseHelper;
import com.example.redme.whodoneit.database.OffenseDbSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by redme on 12/21/2015.
 */

/*The purpose of this class is to implement the single factory design pattern to ensure only a single
 collection of offenses */

public class OffenseSingleton {

    private static OffenseSingleton offense_singleton;
    private Context mContext;
    private SQLiteDatabase database;



    //method loops through offense_list searching for an id, return null on failure
    public Offense getOffense(UUID desired_offense_id){

        return null;
    }

    public List<Offense> getOffenses(){
        return null;
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
        mContext= context.getApplicationContext();
        database = new OffenseBaseHelper(mContext).getWritableDatabase();




    }

    public void addOffense(Offense o){
        //When writing to a SQLite db it accepts the name of the table you want to write to and
        //a hashtable (ContentValues) of each column key,value pairing.
        //It's just how it is done for this example
        ContentValues values = getContentValues(o);
        database.insert(OffenseDbSchema.OffenseTable.NAME,null,values);

    }

    private static ContentValues getContentValues(Offense offense) {
        //values is basically a hashtable of key,value pairs
        ContentValues values = new ContentValues();

        values.put(OffenseDbSchema.OffenseTable.Cols.UUID, offense.getUserId().toString());
        values.put(OffenseDbSchema.OffenseTable.Cols.TITLE, offense.getTitle());
        values.put(OffenseDbSchema.OffenseTable.Cols.DATE, offense.getStringDate());
        values.put(OffenseDbSchema.OffenseTable.Cols.SOLVED, offense.isSolved() ? 1 : 0);
        return values;
    }

    public void updateOffense (Offense o){
        String uuidString = o.getUserId().toString();
        ContentValues values = getContentValues(o);

        database.update(OffenseDbSchema.OffenseTable.NAME,values,
                OffenseDbSchema.OffenseTable.Cols.UUID + " = ?",
                new String [] {uuidString});
    }
}
