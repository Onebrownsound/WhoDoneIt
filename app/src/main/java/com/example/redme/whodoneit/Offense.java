package com.example.redme.whodoneit;

import java.util.Date;
import java.util.UUID;
import android.text.format.DateFormat;

/**
 * Created by redme on 12/19/2015.
 */
public class Offense {
    private UUID user_id;


    private String title;
    private String offense_description;
    private Date date;
    private Boolean solved;

    //Getters and setters everywhere
    public Boolean isSolved() {
        return solved;
    }

    public void setSolved(Boolean solved) {
        this.solved = solved;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getStringDate() {
        //Dateformat is a library that takes in a string + Date object
        // returns a parsed representation that .toString may be called upon.
        DateFormat df = new DateFormat();
        //So when we want the date it will return the Day Date and time
        return df.format("EEEE MM-dd-yyyy HH:mm",date).toString();
    }

    public Date getDate (){
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Offense(){
        //Upon instantiation of new offense auto add date and user_id
         this(UUID.randomUUID());
    }
    public Offense(UUID id){
        user_id = id;
        date = new Date();
    }

    public UUID getUserId(){
        return user_id;
    }
    public String getOffense_description(){
        return offense_description;
    }

    public void setOffense_description(String description){
        this.offense_description = description;
    }
}
