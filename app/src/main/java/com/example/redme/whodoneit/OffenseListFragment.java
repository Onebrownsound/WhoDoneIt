package com.example.redme.whodoneit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by redme on 12/21/2015.
 */
public class OffenseListFragment extends android.support.v4.app.Fragment{

    private RecyclerView offense_recycle_view;
    private OffenseAdapter offense_adapter;

    @Override
    public void onResume(){
        super.onResume();
        updateUI();


    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){


        //Creates a view that is "inflated" with the fragment_offense
        //Need to learn more about this later
        View v =inflater.inflate(R.layout.fragment_offense_list, container, false);



        offense_recycle_view = (RecyclerView) v.findViewById(R.id.offense_recycler_view);
        //Not sure whats going on here need to figure it out
        offense_recycle_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();



        return v;

    }

    //private class definitions required for utilizing recylerview
    private class OffenseHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Offense binding_offense;
        public TextView title;
        public TextView date;
        public CheckBox solved;




        public OffenseHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.list_item_offense_title_text);
            date = (TextView) itemView.findViewById(R.id.list_item_offense_date_text);
            solved = (CheckBox) itemView.findViewById(R.id.list_item_offense_check_box);

        }

        @Override
        public void onClick(View v){

            
            Intent intent = OffenseActivity.newIntent(getActivity(),binding_offense.getUserId());
            startActivity(intent);
        }

        //the below function will bind an offense instance to the "right" view slots
        public void bindOffense(Offense o){
            binding_offense = o;


            title.setText(binding_offense.getTitle());
            date.setText(binding_offense.getDate());
            solved.setChecked(binding_offense.isSolved());

        }

    }

    private class OffenseAdapter extends RecyclerView.Adapter<OffenseHolder>{
        private List<Offense> offense_list;

        public OffenseAdapter(List<Offense> input){
            offense_list = input;
        }

        //what????
        @Override
        public OffenseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_offense,parent,false);
            return new OffenseHolder(view);
        }

        @Override
        public void onBindViewHolder(OffenseHolder holder, int position) {
            Offense offense = offense_list.get(position);
            holder.bindOffense(offense);

        }

        @Override
        public int getItemCount() {
            return offense_list.size();
        }
    }

    private void updateUI(){


        OffenseSingleton offense_singleton = OffenseSingleton.get(getActivity());
        List<Offense> offense_list = offense_singleton.getOffenses();

        //This if/else is to update the adapter in the event we changed something in a child-fragment
        if (offense_adapter==null) {
            offense_adapter = new OffenseAdapter(offense_list);
            offense_recycle_view.setAdapter(offense_adapter);
        }
        else {
            offense_adapter.notifyDataSetChanged();
        }



    }
}
