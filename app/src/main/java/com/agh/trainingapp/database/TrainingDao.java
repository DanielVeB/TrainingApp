package com.agh.trainingapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import com.agh.trainingapp.database.entities.Training;

@Dao
public interface TrainingDao {
    @Insert
    public void addTraining(Training training);

    @Query("SELECT * FROM training")
    public List<Training> getTrainings();

    @Delete
    public void deleteTraining(Training training);
}
