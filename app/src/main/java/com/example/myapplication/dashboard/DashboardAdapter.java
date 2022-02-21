package com.example.myapplication.dashboard;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Für die Funktionen der einzelnen Items in der Dashboard RecyclerView zuständig
 * initialisiert die Elemente der Item View und fügt ihnen die entsprechenden Eigenschaften zu
 *
 * @author Kilian Mauson
 */


public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ViewHolder> {


    private List<DashboardModel> mData;
    private LayoutInflater mInflater;


    public DashboardAdapter(Context context, ArrayList<DashboardModel> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    /**
     * Der Viewholder beinhaltet alle Views des jeweiligen Listeneintrags
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView dogName;
        private final Button matchesButton;
        private final Button likesButton;
        private final Button searchButton;

        private final ImageView dogPicture;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            dogName = (TextView) view.findViewById(R.id.hundenameTextView);
            matchesButton = (Button) view.findViewById(R.id.matchesButton);
            likesButton = (Button) view.findViewById(R.id.likesButton);
            searchButton = (Button) view.findViewById(R.id.searchButton);
            dogPicture = (ImageView) view.findViewById(R.id.dogPicture);
        }

        public TextView getDogName() {
            return dogName;
        }

        public ImageView getDogPicture() {return dogPicture;}

        public Button getMatchesButton() {
            return matchesButton;
        }

        public Button getLikesButton() {
            return likesButton;
        }

        public Button getSearchButton() {
            return searchButton;
        }
    }

    /**
     * setzt für jeden neuen Eintrag item_dashboard als Layout
     *
     * @param viewGroup
     * @param viewType
     * @return
     */
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_dashboard, viewGroup, false);

        return new ViewHolder(view);
    }

    /**
     * definiert was gemacht werden soll, wenn der Serach, Likes oder Matches Button gedrückt wird
     * füllt die Views mit konkreten Inhalten aus der übergebenen Liste mData
     *
     * @param viewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        DashboardModel items = mData.get(position);

        viewHolder.getDogPicture().setImageResource(R.drawable.a_portrait_of_a_beagle_that_was_a_rescued_dog_2);
        viewHolder.getDogName().setText(items.getDogName());
        Picasso.get().load(items.getImage()).fit().centerCrop().into(viewHolder.dogPicture);

        viewHolder.getMatchesButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.goToMatches(v.getContext());
            }
        });

        viewHolder.getLikesButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.goToLikes(v.getContext());
            }
        });

        viewHolder.getSearchButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.goToSearch(v.getContext());
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mData.size();
    }
}




