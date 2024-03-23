package com.pse.hjss;

import java.util.HashMap;
public class Manager {
    //Initialising the learnerID with a seven-digit number
    private static int learnerCounter = 1100000;
    public static HashMap<Integer, Learner> learnersHashMap = new HashMap<>();

    public static int generateLearnerID() {
        // Increment the counter for the next learner
        learnerCounter++;
        return learnerCounter;
    }

    public static void printLearnersList(){
        for (Learner learner : learnersHashMap.values()) {
            System.out.println(learner.toString());
        }
    }

    public static void registerLearner(String name, int age, String gender, int currentGradeLevel, String emergencyContactNumber){
            int id = generateLearnerID();
            learnersHashMap.put(id, new Learner(id, name, age, gender, currentGradeLevel, emergencyContactNumber));
    }
}
