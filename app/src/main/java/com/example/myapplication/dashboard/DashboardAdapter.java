package com.example.myapplication.dashboard;


import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DashboardAdapter extends RecyclerView.Adapter {

    private ArrayList<Dashboard> items;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView name;
        public final Button matchesName;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            name = view.findViewById(R.id.name);
            description = view.findViewById(R.id.description);
            image = view.findViewById(R.id.image);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (items != null) {
            return items.size();
        } else {
            return 0;
        }
    }
}
