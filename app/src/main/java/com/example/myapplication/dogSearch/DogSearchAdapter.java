package com.example.myapplication.dogSearch;

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
import com.example.myapplication.dashboard.DashboardAdapter;

import java.util.ArrayList;
import java.util.List;

public class DogSearchAdapter extends RecyclerView.Adapter<DogSearchAdapter.ViewHolder> {

    //Deklaration von Variablen
    private List<DogSearch> dogData;
    private List<DogSearch> limitedDogData;
    private LayoutInflater myInflator;

    public DogSearchAdapter(Context context, ArrayList<DogSearch> data) {
        this.myInflator = LayoutInflater.from(context);
        this.dogData = data;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //Deklaration von Variablen
        private final TextView searchDogName;
        private final TextView rasseTextView;
        private final TextView geschlechtTextView;
        private final TextView alterTextView;
        private final TextView papiereTextView;

        private Button likeButton;
        private Button dislikeButton;

        private ImageView searchDogImage;


        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            searchDogName = (TextView) view.findViewById(R.id.searchDogName);
            rasseTextView = (TextView) view.findViewById(R.id.rasseTextView);
            geschlechtTextView = (TextView) view.findViewById(R.id.geschlechtTextView);
            alterTextView = (TextView) view.findViewById(R.id.alterTextView);
            papiereTextView = (TextView) view.findViewById(R.id.papiereTextView);

            likeButton = (Button) view.findViewById(R.id.likeButton);
            dislikeButton = (Button) view.findViewById(R.id.dislikeButton);
        }

        public TextView getSearchDogName() {
            return searchDogName;
        }

        public TextView getRasseTextView() {
            return rasseTextView;
        }

        public TextView getGeschlechtTextView() {
            return geschlechtTextView;
        }

        public TextView getAlterTextView() {
            return alterTextView;
        }

        public TextView getPapiereTextView() {
            return papiereTextView;
        }

        public Button getLikeButton() {
            return likeButton;
        }

        public Button getDislikeButton() {
            return dislikeButton;
        }

        public ImageView getSearchDogImage() {
            return searchDogImage;
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.search, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(DogSearchAdapter.ViewHolder viewHolder, final int position) {


        //limitedDogData.add(dogData.get(0));
        Log.d("test", dogData.get(0).toString());

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        DogSearch items = limitedDogData.get(position);


        Log.d("test", viewHolder.getAlterTextView().toString());
        viewHolder.getAlterTextView().setText(items.getAlterTextView());
        viewHolder.getSearchDogName().setText(items.getSearchDogName());
        viewHolder.getRasseTextView().setText(items.getRasseTextView());
        viewHolder.getPapiereTextView().setText(items.getPapiereTextView());


        viewHolder.getLikeButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test", "gedrückt");
            }
        });

        viewHolder.getDislikeButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test", "gedrückt");
                dogData.remove(0);
                notifyDataSetChanged();
            }
        });


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dogData.size();
    }

}
