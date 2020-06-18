package com.agh.trainingapp.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(tableName = "training")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Training {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;
    public String description;
    public double time;

    public Training(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
