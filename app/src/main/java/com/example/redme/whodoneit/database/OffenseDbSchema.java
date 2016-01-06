package com.example.redme.whodoneit.database;

/**
 * Created by redme on 1/6/2016.
 */
public class OffenseDbSchema {
    public static final class OffenseTable {
        public static final String NAME = "offenses";
        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DESCRIPTION = "description";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";
        }


    }


}

