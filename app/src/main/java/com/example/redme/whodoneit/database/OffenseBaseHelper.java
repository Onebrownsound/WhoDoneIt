package com.example.redme.whodoneit.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.redme.whodoneit.Offense;
import com.example.redme.whodoneit.database.OffenseDbSchema.OffenseTable;

/**
 * Created by redme on 1/6/2016.
 */

//Designed to make it easy to open and connect to the DB
public class OffenseBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "offenseBase.db";
    public OffenseBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + OffenseTable.NAME + "(" +
            " _id integer primary key autoincrement, " +
            OffenseTable.Cols.UUID + ", " +
            OffenseTable.Cols.TITLE + ", " +
            OffenseTable.Cols.DATE + ", " +
            OffenseTable.Cols.SOLVED +
            ")");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
