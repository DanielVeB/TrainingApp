package com.agh.trainingapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.agh.trainingapp.R;
import com.agh.trainingapp.database.async.InsertTrainingTask;
import com.agh.trainingapp.database.entities.Training;

public class HomeFragment extends Fragment {

    private EditText training;
    private EditText description;
    private EditText time;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        training = view.findViewById(R.id.training);
        description = view.findViewById(R.id.description);
        time = view.findViewById(R.id.time);

        Button saveTrainingButton = view.findViewById(R.id.button);

        saveTrainingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trainingText = training.getText().toString();
                String descriptionText = description.getText().toString();
                double timeValue = Double.parseDouble(time.getText().toString());

                Training newTraining = new Training();
                newTraining.setTitle(trainingText);
                newTraining.setDescription(descriptionText);
                newTraining.setTime(timeValue);

                new InsertTrainingTask(newTraining).execute();
                Toast.makeText(getActivity(), "Training added successfully", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}