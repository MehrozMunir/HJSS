package com.pse.hjss;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Manager {
    //Initialising the learnerID with a seven-digit number
    private static final AtomicInteger Learner_ID_GENERATOR = new AtomicInteger(1100000);
    private static final AtomicInteger BOOKING_ID_GENERATOR = new AtomicInteger(100100);
    public static HashMap<Integer, Learner> learnersHashMap = new HashMap<>();
    public static HashMap<Integer, Booking> bookingsHashMap = new HashMap<>();

    public static int generateLearnerID() {
        // Increment the counter for the next learner
        return BOOKING_ID_GENERATOR.getAndIncrement();
    }
    public static String generateBookingID() {
        return "B" + BOOKING_ID_GENERATOR.getAndIncrement();
    }

    public static void printLearnersList(){
        for (Learner learner : learnersHashMap.values()) {
            System.out.println(learner.toString());
        }
    }
    public static void printBookingsList(){
        for (Booking booking : bookingsHashMap.values()) {
            System.out.println(booking.toString());
        }
    }

    public static void registerLearner(String name, int age, String gender, int currentGradeLevel, String emergencyContactNumber){
            int id = generateLearnerID();
            learnersHashMap.put(id, new Learner(id, name, age, gender, currentGradeLevel, emergencyContactNumber));
            System.out.println("Learner is added successfully!");
    }
    public static void bookALesson(String name, int age, String gender, int currentGradeLevel, String emergencyContactNumber){
        int id = generateLearnerID();
        learnersHashMap.put(id, new Learner(id, name, age, gender, currentGradeLevel, emergencyContactNumber));
        System.out.println("Learner is added successfully!");
    }
}
