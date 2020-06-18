package com.agh.trainingapp.database.async;

import android.os.AsyncTask;

import com.agh.trainingapp.MainActivity;

import java.util.List;

import com.agh.trainingapp.database.entities.Training;

public class GetTrainingsTask extends AsyncTask<Object, Object, List<Training>> {
    @Override
    protected List<Training> doInBackground(Object... objects) {
        return MainActivity.database.trainingDao().getTrainings();
    }
}
