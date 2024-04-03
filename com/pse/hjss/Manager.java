package com.pse.hjss;

import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

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
    public static void printLessonsList(String filterKey, String filterValue){
        for (Lesson lesson : lessonsHashMap.values()) {
            if(filterKey.equals("day")) {
                LocalDateTime lessonDateTime = lesson.getLessonDateTimeLDF();
                DayOfWeek dayOfWeek = lessonDateTime.getDayOfWeek();
                if (filterValue.equalsIgnoreCase("M") && dayOfWeek == DayOfWeek.MONDAY) {
                    System.out.println(lesson.toString());
                }
                else if (filterValue.equalsIgnoreCase("W") && dayOfWeek == DayOfWeek.WEDNESDAY){
                    System.out.println(lesson.toString());
                }
                else if (filterValue.equalsIgnoreCase("F") && dayOfWeek == DayOfWeek.FRIDAY){
                    System.out.println(lesson.toString());
                }
                else if (filterValue.equalsIgnoreCase("S") && dayOfWeek == DayOfWeek.SATURDAY){
                    System.out.println(lesson.toString());
                }
            } else if (filterKey.equals("grade_level")) {
                if (filterValue.equalsIgnoreCase(lesson.getGradeLevel()+"")){
                    System.out.println(lesson.toString());
                }
            }else if (filterKey.equals("coach_name")) {
                if (filterValue.equalsIgnoreCase(lesson.getCoachName())){
                    System.out.println(lesson.toString());
                }
            }
            else{
                System.out.println(lesson.toString());
            }
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
                String[] keyValue = parts[5].split("#");
                Lesson lesson = Manager.lessonsHashMap.get(Integer.parseInt(keyValue[1]));
                String lessonDateTime = lesson.getLessonDateTime();
                System.out.println();
                for (String part : parts) {
                    keyValue = part.split("#");
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();
                    System.out.println(key + ": " + value);
                }
                System.out.println("lesson_date_time" + ": " + lessonDateTime);
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
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy hh:mm a");
            String booking_date_time = myFormatObj.format(LocalDateTime.now());
            String content = "bookingID#"+bookingID+";booking_date_time#"+booking_date_time+
                    ";booking_status#booked;grade_level#"+lesson.getGradeLevel()+";coach_name#"+
                    lesson.getCoachName()+";lesson_id#"+lesson.getLessonID()+";";
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
            lesson.decrementAvailableSeats();
            System.out.println("Your booking for the lesson id: "+lessonID+" is booked successfully! "+
                    "Your booking ID is: "+ bookingID);
        }
        //System.out.println("Lesson is added successfully!");
    }

   public static void cancelBooking( String bookingIdToRemove, String month, String learnerID){
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
               if (parts[0].contains("bookingID#" + bookingIdToRemove)) {
                   parts[2] = "booking_status:cancelled";
                   content.append(parts[0]+";"+parts[1]+";"+parts[2]+";"+
                           parts[3]+";"+parts[4]+";"+parts[5]).append(System.getProperty("line.separator"));
                   String[] keyValue = parts[5].split("#");
                   Lesson lesson = Manager.lessonsHashMap.get(Integer.parseInt(keyValue[1]));
                   lesson.incrementAvailableSeats();
               }
               else{
                   content.append(currentLine).append(System.getProperty("line.separator"));
               }
           }
           reader.close();
           // Write the modified content back to the file
           BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
           writer.write(content.toString());
           writer.close();
           System.out.println("Booking with ID: " + bookingIdToRemove + " is cancelled successfully!");
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
    public static void changeBooking( String bookingId, String month, String learnerID, int lessonID){
        Lesson lesson = lessonsHashMap.get(lessonID);
        if(lesson.getAvailableSeats()<=0){
            System.out.println("There are already 4 bookings for this time slot!");
        }
        else {
            try {
                String filePath = "Learner_Data" + File.separator + month + File.separator + learnerID + ".txt";
                File inputFile = new File(filePath);
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                StringBuilder content = new StringBuilder();

                String currentLine;
                while ((currentLine = reader.readLine()) != null) {
                    // Split the line by semicolon
                    String[] parts = currentLine.split(";");
                    // Check if the line contains the booking ID to remove
                    if (parts[0].contains("bookingID#" + bookingId)) {
                        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy hh:mm a");
                        String booking_date_time = myFormatObj.format(LocalDateTime.now());
                        content.append("bookingID#"+bookingId+";booking_date_time#"+booking_date_time+
                                ";booking_status#booked;grade_level#"+lesson.getGradeLevel()+";coach_name#"+
                                lesson.getCoachName()+";lesson_id#"+lesson.getLessonID()+";");
                        lesson.decrementAvailableSeats();
                    } else {
                        content.append(currentLine).append(System.getProperty("line.separator"));
                    }
                }
                reader.close();
                // Write the modified content back to the file
                BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
                writer.write(content.toString());
                writer.close();
                System.out.println("Your Booking ID: " + bookingId + " is now updated for the lesson: "+lessonID);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
