package com.example.redme.whodoneit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;

/**
 * Created by redme on 12/19/2015.
 */
public class OffenseFragment extends android.support.v4.app.Fragment {
    private static final String ARG_OFFENSE_ID = "offense_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0 ;

    private Offense test_Offense;
    private EditText title_field;
    private EditText description_field;
    private Button date_button;
    private CheckBox check_box;

    //Below is how you can essentially staple a dictionary to a fragment
    //Create a bundle then put a key,value pair into it, followed by this set method
    public static OffenseFragment newInstance(UUID offenseId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_OFFENSE_ID, offenseId);

        OffenseFragment fragment = new OffenseFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID test_Offense_id = (UUID) getArguments().getSerializable(ARG_OFFENSE_ID);
        test_Offense = OffenseSingleton.get(getActivity()).getOffense(test_Offense_id);

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
        title_field.setText(test_Offense.getTitle());
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
        description_field.setText(test_Offense.getOffense_description());
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
        date_button.setText(test_Offense.getStringDate());

        //open a dialog on click, set new fragment as target and listen for response
        date_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(test_Offense.getDate());
                dialog.setTargetFragment(OffenseFragment.this,REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });

        check_box = (CheckBox) v.findViewById(R.id.offense_solved);

        //Holy ugly we need to fix this later
        //See if the checkbox is toggled if and set our test offense to that Bool
        check_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                test_Offense.setSolved(isChecked);
            }
        });








        return v;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            test_Offense.setDate(date);
            date_button.setText(test_Offense.getStringDate());
        }
    }
}
