package com.example.redme.whodoneit.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.redme.whodoneit.Offense;

import java.util.Date;
import java.util.UUID;

/**
 * Created by redme on 1/6/2016.
 */
public class OffenseCursorWrapper extends CursorWrapper {
    public OffenseCursorWrapper(Cursor cursor){
        super(cursor);

    }

    public Offense getOffense(){
        String uuidString = getString(getColumnIndex(OffenseDbSchema.OffenseTable.Cols.UUID));
        String title = getString(getColumnIndex(OffenseDbSchema.OffenseTable.Cols.TITLE));
        String description = getString(getColumnIndex(OffenseDbSchema.OffenseTable.Cols.DESCRIPTION));
        long date = getLong(getColumnIndex(OffenseDbSchema.OffenseTable.Cols.DATE));
        int isSolved = getInt(getColumnIndex(OffenseDbSchema.OffenseTable.Cols.SOLVED));

        Offense offense = new Offense(UUID.fromString(uuidString));
        offense.setTitle(title);
        offense.setOffense_description(description);
        offense.setDate(new Date(date));
        offense.setSolved(isSolved !=0);



        return offense;
    }
}
