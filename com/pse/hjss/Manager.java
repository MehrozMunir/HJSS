package com.pse.hjss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.*;

import static java.time.LocalTime.now;

public class Manager {
    //Initialising the learnerID with a seven-digit number
    private static final AtomicInteger LEARNER_ID_GENERATOR = new AtomicInteger(11001);
    private static final AtomicInteger LESSON_ID_GENERATOR = new AtomicInteger(20100);
    private static final AtomicInteger Booking_ID_GENERATOR = new AtomicInteger(300101);
    public static HashMap<Integer, Learner> learnersHashMap = new HashMap<>();
    public static HashMap<Integer, Lesson> lessonsHashMap = new HashMap<>();

    public static int generateLearnerID() {
        // Increment the counter for the next learner
        return LEARNER_ID_GENERATOR.getAndIncrement();
    }
    public static int generateLessonID() {
        return LESSON_ID_GENERATOR.getAndIncrement();
    }
    public static String generateBookingID() {
        return "B"+Booking_ID_GENERATOR.getAndIncrement();
    }

    public static void printLearnersList(){
        for (Learner learner : learnersHashMap.values()) {
            System.out.println(learner.toString());
        }
    }
    public static void printLessonsList(){
        for (Lesson lesson : lessonsHashMap.values()) {
            System.out.println(lesson.toString());
        }
    }
    public static void printBookingsList(int learnerID){
        try {
            String filePath = "Learner_Data" + File.separator + "03"+ File.separator + learnerID+".txt";
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                // Process each booking data
                String[] parts = line.split(";");
                for (String part : parts) {
                    String[] keyValue = part.split(":");
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();
                    System.out.println(key + ": " + value);
                }
                System.out.println(); // Separate bookings
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void registerLearner(String name, int age, String gender, int currentGradeLevel, String emergencyContactNumber){
            int id = generateLearnerID();
            learnersHashMap.put(id, new Learner(id, name, age, gender, currentGradeLevel, emergencyContactNumber));
            System.out.println("Learner is added successfully!");
    }
    public static void bookALesson( int lessonID, int learnerID){
        Lesson lesson = lessonsHashMap.get(lessonID);
        if(lesson.getAvailableSeats()<=0){
            System.out.println("There are already 4 bookings for this time slot!");
        }
        else{
            String bookingID = generateBookingID();
            lesson.decrementAvailableSeats();
            String content = "bookingID:"+bookingID+";date_time:"+now()+";booking_status:booked;grade_level:2;coach_name:"+lesson.getCoachName()+";";
            try {
                String filePath = "Learner_Data" + File.separator + "03"+ File.separator + learnerID+".txt";
                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));

                // Write the new line to the file
                writer.write(content);
                writer.newLine(); // Add a newline character after the new line

                // Close the file
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Your booking for the lesson id: "+lessonID+" is booked successfully!"+
                    "Your booking ID is: "+ bookingID);
        }
        //System.out.println("Lesson is added successfully!");
    }

   public static void cancelABooking( String bookingIdToRemove, String month, String learnerID){
       try {
           // Read the content of the file
           String filePath = "Learner_Data" + File.separator + month+ File.separator + learnerID+".txt";
           File inputFile = new File(filePath);
           BufferedReader reader = new BufferedReader(new FileReader(inputFile));
           StringBuilder content = new StringBuilder();

           String currentLine;
           while ((currentLine = reader.readLine()) != null) {
               // Split the line by semicolon
               String[] parts = currentLine.split(";");
               // Check if the line contains the booking ID to remove
               if (!parts[0].contains("bookingID:" + bookingIdToRemove)) {
                   content.append(currentLine).append(System.getProperty("line.separator"));
               }
           }
           reader.close();

           // Write the modified content back to the file
           BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
           writer.write(content.toString());
           writer.close();

           System.out.println("Booking with ID " + bookingIdToRemove + " removed successfully.");

       } catch (IOException e) {
           e.printStackTrace();
       }
   }
}
