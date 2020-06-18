package com.agh.trainingapp.database.async;

import android.os.AsyncTask;

import com.agh.trainingapp.MainActivity;
import com.agh.trainingapp.database.entities.Training;


public class InsertTrainingTask extends AsyncTask<Object, Object, Training> {
    private Training training;

    public InsertTrainingTask(Training training){
        this.training = training;
    }

    @Override
    protected Training doInBackground(Object... objects) {
        MainActivity.database.trainingDao().addTraining(training);
        return null;
    }
}
