package com.pse.hjss;

import com.pse.hjss.Utils.CustomValidationException;

import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.*;

public class Manager {
    //Initialising the learnerID with a seven-digit number
    private static final AtomicInteger LEARNER_ID_GENERATOR = new AtomicInteger(11001);
    private static final AtomicInteger LESSON_ID_GENERATOR = new AtomicInteger(20100);
    private static final AtomicInteger Booking_ID_GENERATOR = new AtomicInteger(300101);
    public static HashMap<Integer, Learner> learnersHashMap = new HashMap<>();
    public static HashMap<Integer, Lesson> lessonsHashMap = new HashMap<>();
    public static ArrayList<String> coachesNamesArrayList = new ArrayList<>();
    public static final String BOOKING_MONTH ="05";
    public static final String REPORT_MONTH ="04";

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
        TreeMap<Integer, Learner> sortedMap = new TreeMap<>(learnersHashMap);
        for (Map.Entry<Integer, Learner> entry : sortedMap.entrySet()) {
            Learner learner = entry.getValue();
            System.out.println(learner.toString());
        }
    }
    public static void printLessonsList(String filterKey, String filterValue){
        for (Lesson lesson : lessonsHashMap.values()) {
            switch (filterKey) {
                case "day" -> {
                    LocalDateTime lessonDateTime = lesson.getLessonDateTimeLDF();
                    DayOfWeek dayOfWeek = lessonDateTime.getDayOfWeek();
                    if (filterValue.equalsIgnoreCase("M") && dayOfWeek == DayOfWeek.MONDAY) {
                        System.out.println(lesson.toString());
                    } else if (filterValue.equalsIgnoreCase("W") && dayOfWeek == DayOfWeek.WEDNESDAY) {
                        System.out.println(lesson.toString());
                    } else if (filterValue.equalsIgnoreCase("F") && dayOfWeek == DayOfWeek.FRIDAY) {
                        System.out.println(lesson.toString());
                    } else if (filterValue.equalsIgnoreCase("S") && dayOfWeek == DayOfWeek.SATURDAY) {
                        System.out.println(lesson.toString());
                    }
                }
                case "grade_level" -> {
                    if (filterValue.equalsIgnoreCase(lesson.getGradeLevel() + "")) {
                        System.out.println(lesson.toString());
                    }
                }
                case "coach_name" -> {
                    if (filterValue.equalsIgnoreCase(lesson.getCoachName())) {
                        System.out.println(lesson.toString());
                    }
                }
                default -> System.out.println(lesson.toString());
            }
        }
    }
    public static void printLearnerReport(){
        try {
            TreeMap<Integer, Learner> sortedMap = new TreeMap<>(learnersHashMap);
            for (Map.Entry<Integer, Learner> entry : sortedMap.entrySet()) {
                Learner learner = entry.getValue();
                System.out.println("--------------------------------------------------------------");
                System.out.println("Learner ID: "+learner.getID()+"   Learner name: "+learner.getFirstName() + " "+learner.getLastName());
                System.out.println("Current grade level: "+learner.getCurrentGradeLevel()+"    Emergency contact number: "+learner.getEmergencyContactNumber());
                String filePath = "learner_data" + File.separator + REPORT_MONTH + File.separator + learner.getID() + ".txt";
                File file = new File(filePath);
                file.createNewFile();
                BufferedReader br = new BufferedReader(new FileReader(filePath));
                String line;
                int booked = 0, cancelled = 0, attended = 0;
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
                        if (key.equals("booking_status") && value.equals("booked"))
                            booked++;
                        else if (key.equals("booking_status") && value.equals("cancelled"))
                            cancelled++;
                        else if (key.equals("booking_status") && value.equals("attended"))
                            attended++;
                        System.out.println(key + ": " + value);
                    }
                    System.out.println("lesson_date_time" + ": " + lessonDateTime);
                }
                System.out.println("\nTotal lessons: "+(attended+booked+cancelled)+"\nbooked = " + booked + "   cancelled = " + cancelled + "   attended = " + attended+"\n");
            }
            } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printCoachReport( int month){
        try {
            for (String s : coachesNamesArrayList) {
                System.out.println("--------------------------------------------------------------");
                System.out.println("Coach name: " + s);
                String filePath = "coach_data" + File.separator + REPORT_MONTH + File.separator + s + ".txt";
                File file = new File(filePath);
                file.createNewFile();
                BufferedReader br = new BufferedReader(new FileReader(filePath));
                String line;
                StringBuilder content = new StringBuilder(s + " received ratings of ");
                int numberOfRatings = 0, sumOfRatings = 0;
                while ((line = br.readLine()) != null) {
                    // Process each booking data
                    numberOfRatings++;
                    String[] parts = line.split(";");
                    String[] keyValue = parts[1].split("#");
                    content.append(keyValue[1]).append(", ");
                    sumOfRatings += Integer.parseInt(keyValue[1]);
                }
                double averageRating = 0.0;
                if (numberOfRatings > 0)
                    averageRating = ((double) sumOfRatings / numberOfRatings);
                content.deleteCharAt(content.length() - 2);
                content.append("for ").append(numberOfRatings).append(" lessons.\n").append("Average Rating = ").append(averageRating);

                if (numberOfRatings > 0)
                    System.out.println(content + "\n");
                else
                    System.out.println("There are no ratings for " + s + " for this month.\n");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void registerLearner(String firstName, String lastName, int age, String gender, int currentGradeLevel, String emergencyContactNumber){
            int id = generateLearnerID();
            learnersHashMap.put(id, new Learner(id, firstName, lastName, age, gender, currentGradeLevel, emergencyContactNumber));
            System.out.println("Learner is added successfully!");
    }
    public static boolean bookALesson( int lessonID, int learnerID){
        boolean booked = false;
        Lesson lesson = lessonsHashMap.get(lessonID);
        Learner learner = learnersHashMap.get(learnerID);
        String status = isLessonBookedOrAttended(learnerID, lesson);
        if(status.equals("booked"))
        {
            System.out.println("\nThe lesson with id: "+lessonID+ " is already booked against the learner id: "+learnerID);
        }
        else if(status.equals("attended"))
        {
            System.out.println("\nThe lesson with id: "+lessonID+ " is already attended against the learner id: "+learnerID);
        }
        else if(status.equals("error"))
        {
            System.out.println("\nThere is some error in retrieving the data. Try again!");
        }
        else {
            if(learner.getCurrentGradeLevel() != lesson.getGradeLevel() && (learner.getCurrentGradeLevel()+1) != lesson.getGradeLevel()){
                System.out.println("\nThe learner's current grade level is "+learner.getCurrentGradeLevel()+
                                "\nThe learner can book a lesson only of his/her current grade level"+
                        " or a grade level +1.");
            }
            else if (lesson.getAvailableSeats() <= 0) {
                System.out.println("\nThere are already 4 bookings for this time slot!");
            } else {
                String bookingID = generateBookingID();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy hh:mm a");
                String booking_date_time = myFormatObj.format(LocalDateTime.now());
                String content = "bookingID#" + bookingID + ";booking_date_time#" + booking_date_time +
                        ";booking_status#booked;grade_level#" + lesson.getGradeLevel() + ";coach_name#" +
                        lesson.getCoachName() + ";lesson_id#" + lesson.getLessonID() + ";";
                try {
                    String filePath = "learner_data" + File.separator + BOOKING_MONTH + File.separator + learnerID + ".txt";
                    File file = new File(filePath);
                    file.createNewFile();
                    BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
                    // Write the new line to the file
                    writer.write(content);
                    writer.newLine(); // Add a newline character after the content
                    writer.close();
                    booked = true;
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                lesson.decrementAvailableSeats();
                System.out.println("\nYour booking for the lesson id: " + lessonID + " is booked successfully! " +
                        "Your booking ID is: " + bookingID);
            }
        }
        return booked;
    }
   public static String cancelBooking( String bookingIdToRemove, String month, String learnerID){
       String cancelled = "error";
        try {
           // Read the content of the file
           String filePath = "learner_data" + File.separator + BOOKING_MONTH + File.separator + learnerID+".txt";
           File inputFile = new File(filePath);
            inputFile.createNewFile();
           BufferedReader reader = new BufferedReader(new FileReader(inputFile));
           StringBuilder content = new StringBuilder();
           String currentLine;
           while ((currentLine = reader.readLine()) != null) {
               // Split the line by semicolon
               String[] parts = currentLine.split(";");
               // Check if the line contains the booking ID to remove
               if (parts[0].equals("bookingID#" + bookingIdToRemove)) {
                   if(parts[2].equals("booking_status#cancelled")){
                       cancelled = "already_cancelled";
                       content.append(currentLine).append(System.lineSeparator());
                   }
                   else if(parts[2].equals("booking_status#attended")){
                       cancelled = "already_attended";
                       content.append(currentLine).append(System.lineSeparator());
                   }
                   else {
                       parts[2] = "booking_status#cancelled";
                       content.append(parts[0]).append(";").append(parts[1]).append(";").append(parts[2]).
                               append(";").append(parts[3]).append(";").append(parts[4]).append(";").
                               append(parts[5]).append(System.lineSeparator());
                       String[] keyValue = parts[5].split("#");
                       Manager.lessonsHashMap.get(Integer.parseInt(keyValue[1])).incrementAvailableSeats();
                       cancelled = "cancelled";
                   }
               }
               else{
                   content.append(currentLine).append(System.lineSeparator());
               }
           }
           reader.close();
           // Write the modified content back to the file
           BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
           writer.write(content.toString());
           writer.close();
           return cancelled;
       } catch (IOException e) {
            System.out.println(e.getMessage());
           return  cancelled;
       }
   }
    public static String changeBooking( String bookingId, String learnerID, int lessonID){
        String changed = "error";
        Lesson lesson = lessonsHashMap.get(lessonID);
        Learner learner = learnersHashMap.get(Integer.parseInt(learnerID));

        if(learner.getCurrentGradeLevel() != lesson.getGradeLevel() && (learner.getCurrentGradeLevel()+1) != lesson.getGradeLevel()){
            changed = "grade_level_error";
            return changed;
        }
        else if(lesson.getAvailableSeats()<=0){
            changed = "full_booked";
            return changed;
        }
        else {
            try {
                String filePath = "learner_data" + File.separator + BOOKING_MONTH + File.separator + learnerID + ".txt";
                File inputFile = new File(filePath);
                inputFile.createNewFile();
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                StringBuilder content = new StringBuilder();

                String currentLine;
                while ((currentLine = reader.readLine()) != null) {
                    // Split the line by semicolon
                    String[] parts = currentLine.split(";");
                    // Check if the line contains the booking ID to remove
                    if (parts[0].equals("bookingID#" + bookingId) ) {
                        if(parts[2].equals("booking_status#cancelled")){
                            changed="already_cancelled";
                            content.append(currentLine).append(System.lineSeparator());
                        }
                        else if(parts[2].equals("booking_status#booked")){
                            changed="already_booked";
                            content.append(currentLine).append(System.lineSeparator());
                        }
                        else if(parts[2].equals("booking_status#attended")){
                            changed="already_attended";
                            content.append(currentLine).append(System.lineSeparator());
                        }
                        else{
                            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy hh:mm a");
                            String booking_date_time = myFormatObj.format(LocalDateTime.now());
                            content.append("bookingID#").append(bookingId).append(";booking_date_time#").
                                    append(booking_date_time).append(";booking_status#booked;grade_level#").
                                    append(lesson.getGradeLevel()).append(";coach_name#").
                                    append(lesson.getCoachName()).append(";lesson_id#").append(lesson.getLessonID()).
                                    append(";").append(System.lineSeparator());
                            lesson.decrementAvailableSeats();
                            changed = "updated";
                        }
                    } else {
                        content.append(currentLine).append(System.lineSeparator());
                    }
                }
                reader.close();
                // Write the modified content back to the file
                BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
                writer.write(content.toString());
                writer.close();
                return changed;
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return  changed;
            }
        }
    }

    public static void attendBooking( String bookingId,  String learnerID){
        String attended = "error";
        String coachName =""; int lessonGradeLevel =0;
        Learner learner = Manager.learnersHashMap.get(Integer.parseInt(learnerID));
            try {
                String filePath = "learner_data" + File.separator + BOOKING_MONTH + File.separator + learnerID + ".txt";
                File inputFile = new File(filePath);
                inputFile.createNewFile();
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                StringBuilder content = new StringBuilder();
                String currentLine;
                while ((currentLine = reader.readLine()) != null) {
                    // Split the line by semicolon
                    String[] parts = currentLine.split(";");
                    // Check if the line contains the booking ID to remove
                    if (parts[0].equals("bookingID#" + bookingId)) {
                        if(parts[2].equals("booking_status#cancelled")){
                            attended="cancelled";
                            content.append(currentLine).append(System.lineSeparator());
                        }
                        else if(parts[2].equals("booking_status#attended")){
                            attended="already_attended";
                            content.append(currentLine).append(System.lineSeparator());
                        }
                        else {
                            attended = "attended";
                            parts[2] = "booking_status#attended";
                            content.append(parts[0]).append(";").append(parts[1]).append(";").
                                    append(parts[2]).append(";").append(parts[3]).append(";").
                                    append(parts[4]).append(";").append(parts[5])
                                    .append(System.lineSeparator());
                            String[] keyValue = parts[4].split("#");
                            coachName = keyValue[1];
                            keyValue = parts[3].split("#");
                            lessonGradeLevel = Integer.parseInt(keyValue[1]);
                        }
                    } else {
                        content.append(currentLine).append(System.lineSeparator());
                    }
                }
                reader.close();
                // Write the modified content back to the file
                BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
                writer.write(content.toString());
                writer.close();
                if(attended.equals("attended")){
                    System.out.println("Your lesson for Booking ID: " + bookingId + " is attended successfully!");
                    if(learner.getCurrentGradeLevel() < lessonGradeLevel) {
                        learner.upGrade();
                        cancelBelowLevelLessons(learner.getID(), learner.getCurrentGradeLevel() - 1);
                    }
                    reviewAndRating(coachName);
                }
                else if(attended.equals("cancelled")){
                    System.out.println("The lesson with Booking ID: "+ bookingId+" was cancelled." +
                            "\nYou can't attend a lesson that was cancelled.\nTry again if you want to attend a different lesson.");
                    Runner.displayAttendASwimmingLessonMenu();
                }
                else if(attended.equals("already_attended")){
                    System.out.println("The lesson with Booking ID: "+ bookingId+" is already attended." +
                            "\nYou can't attend a lesson that is already attended.\nTry again if you want to attend a different booking.");
                    Runner.displayAttendASwimmingLessonMenu();
                }
                else {
                    System.out.println("The entered Booking ID and Learner ID does not match.\nTry again by entering a valid Learner ID and Booking ID.");
                    Runner.displayAttendASwimmingLessonMenu();
                }
            } catch (IOException e) {
                System.out.println("The entered Booking ID and Learner ID does not match.\nTry again by entering a valid Learner ID and Booking ID.");
                Runner.displayAttendASwimmingLessonMenu();
            }
    }

    public static void reviewAndRating(String coachName){
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                System.out.println("Write a review for the lesson:");
                String review = scanner.nextLine();
                while(true) {
                    try {
                        System.out.print("Enter a rating for the lesson from 1 to 5: ");
                        int rating = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character
                        if (rating <= 5 && rating >= 1) {
                            BufferedWriter writer = Utils.getBufferedWriter(coachName, review, rating);
                            // Close the file
                            writer.close();
                            System.out.println("The review and rating for the lesson is recorded.");
                            return;
                        } else {
                            throw new CustomValidationException("Rating can only be a number between 1 and 5." +
                                    "\nTry again by entering the correct review and rating.");
                        }
                    } catch (CustomValidationException e) {
                        System.out.println(e.getMessage());
                    }
                    catch (Exception e) {
                        System.out.println("Invalid input! Please enter a valid value.");
                        scanner.nextLine(); // Consume the newline character
                    }
                }

            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid value.");
                scanner.nextLine(); // Consume the newline character
            }
        }
    }

    public static String isLessonBookedOrAttended(int learnerID, Lesson lesson) {
        try {
            //LocalDateTime lessonDateTime = lesson.getLessonDateTimeLDF();
            //int month =   lessonDateTime.getMonthValue();
            String filePath = "learner_data" + File.separator + BOOKING_MONTH + File.separator + learnerID + ".txt";
            File inputFile = new File(filePath);
            inputFile.createNewFile();
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                // Split the line by semicolon
                String[] parts = currentLine.split(";");
                // Check if the line contains the booking ID to remove
                if (parts[5].equals("lesson_id#" + lesson.getLessonID()) && parts[2].equals("booking_status#booked")) {
                    return "booked";
                }
                else if (parts[5].equals("lesson_id#" + lesson.getLessonID()) && parts[2].equals("booking_status#attended")) {
                    return "attended";
                }
            }
            reader.close();
            return  "not_booked";
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return "error";
        }
    }

    public static void cancelBelowLevelLessons(int learnerID, int learnerPreviousGradeLevel){
        try {
            // Read the content of the file
            String filePath = "learner_data" + File.separator + BOOKING_MONTH + File.separator + learnerID+".txt";
            File inputFile = new File(filePath);
            inputFile.createNewFile();
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            StringBuilder content = new StringBuilder();
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {

                String[] parts = currentLine.split(";");
                String[] keyValue = parts[3].split("#");
                int lessonGradeLevel = Integer.parseInt(keyValue[1]);

                if (parts[2].equals("booking_status#booked") && lessonGradeLevel == learnerPreviousGradeLevel) {
                        parts[2] = "booking_status#cancelled";
                        content.append(parts[0]).append(";").append(parts[1]).append(";").
                                append(parts[2]).append(";").append(parts[3]).append(";").
                                append(parts[4]).append(";").append(parts[5]).
                                append(System.lineSeparator());
                        keyValue = parts[5].split("#");
                        Manager.lessonsHashMap.get(Integer.parseInt(keyValue[1])).incrementAvailableSeats();
                }
                else{
                    content.append(currentLine).append(System.lineSeparator());
                }
            }
            reader.close();
            // Write the modified content back to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
            writer.write(content.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
