package com.example.redme.whodoneit;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

/**
 * Created by redme on 12/19/2015.
 */
public class OffenseFragment extends android.support.v4.app.Fragment {
    private Offense test_Offense;
    private EditText title_field;
    private EditText description_field;
    private Button date_button;
    private CheckBox check_box;



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        test_Offense= new Offense();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){


        //Creates a view that is "inflated" with the fragment_offense
        //Need to learn more about this later
        View v =inflater.inflate(R.layout.fragment_offense, container, false);


        //offense_title is the actual name of the editable text field displayed in a offense_fragment
        //we get this field by id name and then add listeners to this text field that detect text changes
        //if we did not do this we would have no idea when someone entered or did not enter text
        title_field = (EditText)v.findViewById(R.id.offense_title);
        title_field.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                test_Offense.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });
        description_field = (EditText)v.findViewById(R.id.offense_description);
        description_field.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                test_Offense.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });

        //Remember when R.id.whatever are really just numbered int resources
        //we need a pointer to our actual insntantiated button and this is what this line does
        date_button = (Button) v.findViewById(R.id.offense_date);

        //use our dummy test_Offense instance and pass the string into our button
        date_button.setText(test_Offense.getDate());
        date_button.setEnabled(false);

        check_box = (CheckBox) v.findViewById(R.id.offense_solved);

        //Holy ugly we need to fix this later
        //See if the checkbox is toggled if and set our test offense to that Bool
        check_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){@Override
                                                 public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                     test_Offense.setSolved(isChecked);}});








        return v;

    }
}
