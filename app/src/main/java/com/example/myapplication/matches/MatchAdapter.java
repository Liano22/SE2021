package com.example.myapplication.matches;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.likes.Like;


import java.util.ArrayList;
import java.util.List;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {

    //Deklaration von Variablen
    private List<Match> matchesList;
    private LayoutInflater mInflater;

    //Konstruktor der Klasse Matchadapter
    public MatchAdapter(Context context, ArrayList<Match> matchesList){
        this.mInflater = LayoutInflater.from(context);
        this.matchesList = matchesList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView nameText;
        private final TextView raceText;
        private final TextView ageText;
        private final TextView priceText;

        public ViewHolder(View view){
            super(view);

            nameText = (TextView) view.findViewById(R.id.dog_name);
            raceText = (TextView) view.findViewById(R.id.dog_race);
            ageText = (TextView) view.findViewById(R.id.dog_age);
            priceText = (TextView) view.findViewById(R.id.dog_price);
        }

        public TextView getNameText() {
            return nameText;
        }

        public TextView getRaceText() {
            return raceText;
        }

        public TextView getAgeText() {
            return ageText;
        }

        public TextView getPriceText() {
            return priceText;
        }
    }


    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.dog_matches_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        /*
        String name = matchesList.get(position).getDog_name();
        viewHolder.nameText.setText(name + ",");

        String race = matchesList.get(position).getDog_race();
        viewHolder.raceText.setText(race);

        String age = matchesList.get(position).getDog_age();
        viewHolder.ageText.setText(age);

        String price = matchesList.get(position).getDog_price();
        viewHolder.priceText.setText(price);
         */

        Match items = matchesList.get(position);

        viewHolder.getNameText().setText(items.getDog_name()+ ",");

        viewHolder.getRaceText().setText(items.getDog_race());

        viewHolder.getAgeText().setText(items.getDog_age());

        viewHolder.getPriceText().setText(items.getDog_price());

    }

    @Override
    public int getItemCount() {
        return matchesList.size();
    }
}
