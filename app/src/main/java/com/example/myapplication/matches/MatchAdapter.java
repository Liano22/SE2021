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

//Nils Behrens

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

        //Deklaration der Viewholder Elemente
        private final TextView nameText;
        private final TextView raceText;
        private final TextView ageText;
        private final TextView priceText;

        //Konstruktor der Klasse ViewHolder
        public ViewHolder(View view){
            super(view);

            //Zuweisung der Viewholder Elemente zu den Elementen des Item Views
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

    //Erstellung eines neuen Views für den Viewholder
    //Dem Viewholder wird hierbei der entsprechende XML File (dog_matches_item) zugewiesen.
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.dog_matches_item, viewGroup, false);

        return new ViewHolder(view);
    }

    //Befüllung des Views mit den Daten aus der matchesList
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        Match item = matchesList.get(position);

        viewHolder.getNameText().setText(item.getDog_name()+ ",");

        viewHolder.getRaceText().setText(item.getDog_race());

        viewHolder.getAgeText().setText("Alter: " + item.getDog_age() + " Jahre");

        viewHolder.getPriceText().setText("Preis: " + item.getDog_price() + "€");

    }

    //Größe bzw. Umfang der matchesList abfragen
    @Override
    public int getItemCount() {
        return matchesList.size();
    }
}

//Nils Behrens
