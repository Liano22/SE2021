package com.example.myapplication.dogSearch;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.likes.Like;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DogSearchAdapter extends RecyclerView.Adapter<DogSearchAdapter.ViewHolder> {

    //Deklaration von Variablen
    private List<DogSearch> dogData;
    private List<DogSearch> limitedDogData;
    private LayoutInflater myInflator;
    private DogSearchView myDogSearchView;
    private DatabaseReference mDatabase;
    private Integer matchId;
    private Context context;


    public DogSearchAdapter(Context context, ArrayList<DogSearch> data) {
        this.myInflator = LayoutInflater.from(context);
        this.dogData = data;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //Deklaration von Variablen
        private final TextView searchDogName;
        private final TextView rasseTextView;
        private final TextView geschlechtTextView;
        private final TextView alterTextView;
        private final TextView papiereTextView;
        private final ImageView searchDogImage;

        private final TextView priceTextView;

        private Button likeButton;
        private Button dislikeButton;


        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            searchDogName = (TextView) view.findViewById(R.id.searchDogName);
            rasseTextView = (TextView) view.findViewById(R.id.rasseTextView);
            geschlechtTextView = (TextView) view.findViewById(R.id.geschlechtTextView);
            alterTextView = (TextView) view.findViewById(R.id.alterTextView);
            papiereTextView = (TextView) view.findViewById(R.id.papiereTextView);
            searchDogImage = (ImageView) view.findViewById(R.id.searchDogImage);
            priceTextView = (TextView) view.findViewById(R.id.priceTextView);

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

        public TextView getPriceTextView() {
            return priceTextView;
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
        DogSearch items = dogData.get(position);

        viewHolder.getAlterTextView().setText(items.getAlterTextView());
        viewHolder.getSearchDogName().setText(items.getSearchDogName());
        viewHolder.getRasseTextView().setText(items.getRasseTextView());
        viewHolder.getPapiereTextView().setText(items.getPapiereTextView());
        viewHolder.getGeschlechtTextView().setText(items.getGeschlechtTextView());
        viewHolder.getPriceTextView().setText(items.getPriceTextView());

        Picasso.get().load(items.getImage()).fit().centerCrop().into(viewHolder.searchDogImage);

        viewHolder.getLikeButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogData.remove(viewHolder.getAdapterPosition());
                notifyDataSetChanged();

                // add a like
                LikeClass newLike = new LikeClass(items.getSearchDogId(), items.getDogId(), "true", "false", items.getSearchUser(), items.getDogUser());
                SaveLike saveLike = new SaveLike(context);

                mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("nextMatchId").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (!task.isSuccessful()) {
                            Log.e("firebase", "Error getting data", task.getException());
                        }
                        else {
                            Log.d("firebase", String.valueOf(task.getResult().getValue()));
                            matchId = Integer.parseInt(String.valueOf(task.getResult().getValue()));
                            saveLike.writeLikeToDatabase(newLike, String.valueOf(matchId));
                            saveLike.writeNextMatchID(matchId + 1);
                            //write like at dog
                            //mDatabase.child("dogs").child(items.getDogId()).child("likes").setValue(items.getSearchDogId());
                            saveLike.changeUserDogList(items.getDogId(), items.getSearchDogId());
                            saveLike.checkForLike(items.getSearchDogId(), items.getDogId());
                        }
                    }
                });


            }
        });

        viewHolder.getDislikeButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogData.remove(viewHolder.getAdapterPosition());
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
