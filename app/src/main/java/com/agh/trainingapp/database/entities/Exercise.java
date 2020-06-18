package com.agh.trainingapp.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Entity(tableName = "exercise")
public class Exercise {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String description;
    private String bodyPart;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(String bodyPart) {
        this.bodyPart = bodyPart;
    }

    public Exercise(String name, String description, String bodyPart) {
        this.name = name;
        this.description = description;
        this.bodyPart = bodyPart;
    }

    public static List<Exercise> getExercises() {
        return new LinkedList<>(Arrays.asList(
                new Exercise("squat", "The squat is performed by squatting down with a weight held across the upper back under neck and standing up straight again. This is a compound exercise that also involves the glutes (buttocks) and, to a lesser extent, the hamstrings, calves, and the lower back. Lifting belts are sometimes used to help support the lower back.", "lower body"),
                new Exercise("Upright row", "Sit on the top end of the glideboard and hold the handles above your knees, arms extended and palms facing down.", "Shoulders"),
                new Exercise("Deadlift", "Deadlift refers to the lifting of dead weight (weight without momentum), such as weights lying on the ground. It is one of the few standard weight training exercises in which all repetitions begin with dead weight. In most other lifts there is an eccentric (lowering of the weight) phase followed by the concentric (lifting of the weight) phase. During these exercises, a small amount of energy is stored in the stretched muscles and tendons in the eccentric phase if the lifter is not flexible beyond the range of motion.\n" +
                        "\n" +
                        "There are several positions one can approach when performing the deadlift, which include the conventional deadlift, squat, and sumo-deadlift.\n" +
                        "\n" +
                        "Although this exercise uses the hips and legs as the primary movers, it can just as easily be considered a back exercise.", "Legs"),
                new Exercise("Triceps", "The pushdown is performed while standing by pushing down on a bar held at the level of the upper chest. It is important to keep the elbows at shoulder width and in line with shoulder/legs. In other words, elbows position should not change while moving the forearm pushes down the bar. This is an isolation exercise for the triceps.\n" +
                        "Equipment: cable machine or pulldown machine.", "Upper body")


        ));
    }
}
