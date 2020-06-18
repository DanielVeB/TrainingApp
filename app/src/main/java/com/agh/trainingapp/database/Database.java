package com.agh.trainingapp.database;

import androidx.room.RoomDatabase;

import com.agh.trainingapp.database.entities.Training;


@androidx.room.Database(entities = {Training.class}, version = 1)
public abstract class Database extends RoomDatabase {
    public abstract TrainingDao trainingDao();
}
