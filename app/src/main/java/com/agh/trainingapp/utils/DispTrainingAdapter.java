package com.agh.trainingapp.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.agh.trainingapp.R;
import com.agh.trainingapp.database.entities.Training;

import java.util.List;
import java.util.Locale;


public class DispTrainingAdapter extends RecyclerView.Adapter<DispTrainingAdapter.ViewHolder> {
    private List<Training> trainings;
    private OnTrainingListener onTrainingListener;

    public DispTrainingAdapter(List<Training> trainings, OnTrainingListener onTrainingListener) {
        this.trainings = trainings;
        this.onTrainingListener = onTrainingListener;
    }

    @Override
    public DispTrainingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.training_info, parent, false);

        // Return a new holder instance
        return new ViewHolder(contactView, onTrainingListener);
    }

    @Override
    public void onBindViewHolder(DispTrainingAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Training training = trainings.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.title;
        textView.setText(training.getTitle());
        viewHolder.time.setText(String.format(Locale.ENGLISH,"%.2f", training.getTime()));
    }

    @Override
    public int getItemCount() {
        return trainings.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        public TextView time;
        public OnTrainingListener onTrainingListener;
        public ImageView deleteIcon;

        public ViewHolder(View itemView, final OnTrainingListener onTrainingListener) {
            super(itemView);
            this.onTrainingListener = onTrainingListener;
            title = itemView.findViewById(R.id.trainingInfo);
            time = itemView.findViewById(R.id.time);
            deleteIcon = itemView.findViewById(R.id.deleteIcon);
            itemView.setOnClickListener(this);

            deleteIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onTrainingListener.onDeleteIconClick(getAdapterPosition());
                }
            });
        }

        @Override
        public void onClick(View view) {
            onTrainingListener.onTrainingClick(getAdapterPosition());
        }
    }

    public interface OnTrainingListener {
        void onTrainingClick(int position);
        void onDeleteIconClick(int position);
    }
}