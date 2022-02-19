package com.example.myapplication.likes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dashboard.Dashboard;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

//Nils Behrens

public class LikesAdapter extends RecyclerView.Adapter<LikesAdapter.ViewHolder> {

    //Deklaration von Variablen
    private List<Like> likesList;
    private LayoutInflater mInflater;

    //Konstruktor der Klasse LikesAdapter
    public LikesAdapter(Context context, ArrayList<Like> likesList){
        this.mInflater = LayoutInflater.from(context);
        this.likesList = likesList;
        Log.d("start", String.valueOf(likesList));
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        //Deklaration der Viewholder Elemente
        private final TextView nameText;
        private final TextView raceText;
        private final TextView ageText;
        private final TextView priceText;
        private final Button tossButton;
        private final Button takeButton;
        private final ImageView dogImage;

        //Konstruktor der Klasse ViewHolder
        public ViewHolder(View view){
            super(view);

            //Zuweisung der Viewholder Elemente zu den Elementen des Item Views
            nameText = (TextView) view.findViewById(R.id.dog_name);
            raceText = (TextView) view.findViewById(R.id.dog_race);
            ageText = (TextView) view.findViewById(R.id.dog_age);
            priceText = (TextView) view.findViewById(R.id.dog_price);
            tossButton = view.findViewById(R.id.likesTossButton);
            takeButton = view.findViewById(R.id.likesTakeButton);
            dogImage = view.findViewById(R.id.dogPicture);
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

        public Button getTossButton(){return tossButton;};

        public Button getTakeButton(){return takeButton;};

        public ImageView getDogImage(){return dogImage;}


    }

    //Erstellung eines neuen Views für den Viewholder
    //Dem Viewholder wird hierbei der entsprechende XML File (dog_matches_item) zugewiesen.
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.dog_likes_item, viewGroup, false);

        return new ViewHolder(view);
    }

    //Befüllung des Views mit den Daten aus der likesList
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        Like items = likesList.get(position);

        viewHolder.getNameText().setText(items.getDog_name() + ",");

        viewHolder.getRaceText().setText(items.getDog_race());

        viewHolder.getAgeText().setText(items.getDog_age() + " Jahre");

        viewHolder.getPriceText().setText(items.getDog_price() + "€");

        Picasso.get().load(items.getImage()).fit().centerCrop().into(viewHolder.dogImage);

        viewHolder.getTossButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    //Größe bzw. Umfang der matchesList abfragen
    @Override
    public int getItemCount() {
        return likesList.size();
    }
}

//Nils Behrens
