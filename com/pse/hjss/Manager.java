package com.pse.hjss;

import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Manager {
    //Initialising the learnerID with a seven-digit number
    private static final AtomicInteger LEARNER_ID_GENERATOR = new AtomicInteger(11001);
    private static final AtomicInteger LESSON_ID_GENERATOR = new AtomicInteger(20100);
    private static final AtomicInteger Booking_ID_GENERATOR = new AtomicInteger(300101);
    public static HashMap<Integer, Learner> learnersHashMap = new HashMap<>();
    public static HashMap<Integer, Lesson> lessonsHashMap = new HashMap<>();
    public static final String bookingMonth ="05";

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
            }
            else if (filterKey.equals("grade_level")) {
                if (filterValue.equalsIgnoreCase(lesson.getGradeLevel()+"")){
                    System.out.println(lesson.toString());
                }
            }
            else if (filterKey.equals("coach_name")) {
                if (filterValue.equalsIgnoreCase(lesson.getCoachName())){
                    System.out.println(lesson.toString());
                }
            }
            else{
                System.out.println(lesson.toString());
            }
        }
    }
    public static void printLearnerReport(int learnerID, int month){
        try {
            String filePath = "learner_data" + File.separator + bookingMonth+ File.separator + learnerID+".txt";
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            int booked=0, cancelled=0, attended=0;
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
                    if(key.equals("booking_status") && value.equals("booked"))
                        booked++;
                    else if(key.equals("booking_status") && value.equals("cancelled"))
                        cancelled++;
                    else if(key.equals("booking_status") && value.equals("attended"))
                        attended++;
                    System.out.println(key + ": " + value);
                }
                System.out.println("lesson_date_time" + ": " + lessonDateTime);
            }
            System.out.println("\nTotal lessons:\nbooked = "+booked+"   cancelled = "+cancelled+"   attended = "+attended);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printCoachReport(String coachName, int month){
        try {
            String filePath = "coach_data" + File.separator + bookingMonth+ File.separator + coachName+".txt";
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            String content= coachName+" received ratings of ";
            int numberOfRatings=0, sumOfRatings=0;
            while ((line = br.readLine()) != null) {
                // Process each booking data
                numberOfRatings++;
                String[] parts = line.split(";");
                String[] keyValue = parts[1].split("#");
                content += keyValue[1]+", ";
                sumOfRatings+= Integer.parseInt(keyValue[1]);
            }
            content+= "and his/her average ratings is "+(sumOfRatings/numberOfRatings);
            System.out.println(content);
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
        if(isLessonAlreadyBooked(learnerID, lesson))
        {
            System.out.println("The lesson with id: "+lessonID+ " is already booked against the learner id: "+learnerID);
            return;
        }
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
                String filePath = "learner_data" + File.separator + lesson.getLessonMonthValue()+ File.separator + learnerID+".txt";
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

   public static boolean cancelBooking( String bookingIdToRemove, String month, String learnerID){
       boolean cancelled = false;
        try {
           // Read the content of the file
           String filePath = "learner_data" + File.separator + bookingMonth+ File.separator + learnerID+".txt";
           File inputFile = new File(filePath);
           BufferedReader reader = new BufferedReader(new FileReader(inputFile));
           StringBuilder content = new StringBuilder();
           String currentLine;
           while ((currentLine = reader.readLine()) != null) {
               // Split the line by semicolon
               String[] parts = currentLine.split(";");
               // Check if the line contains the booking ID to remove
               if (parts[0].equals("bookingID#" + bookingIdToRemove)) {
                   parts[2] = "booking_status#cancelled";
                   content.append(parts[0]+";"+parts[1]+";"+parts[2]+";"+
                           parts[3]+";"+parts[4]+";"+parts[5]).append(System.getProperty("line.separator"));
                   String[] keyValue = parts[5].split("#");
                   Lesson lesson = Manager.lessonsHashMap.get(Integer.parseInt(keyValue[1]));
                   lesson.incrementAvailableSeats();
                   cancelled = true;
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
           return cancelled;
       } catch (IOException e) {
           e.printStackTrace();
           return  cancelled;
       }
   }
    public static void changeBooking( String bookingId, String learnerID, int lessonID){
        Lesson lesson = lessonsHashMap.get(lessonID);
        if(lesson.getAvailableSeats()<=0){
            System.out.println("There are already 4 bookings for this time slot!");
        }
        else {
            try {
                String filePath = "learner_data" + File.separator + lesson.getLessonMonthValue() + File.separator + learnerID + ".txt";
                File inputFile = new File(filePath);
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                StringBuilder content = new StringBuilder();

                String currentLine;
                while ((currentLine = reader.readLine()) != null) {
                    // Split the line by semicolon
                    String[] parts = currentLine.split(";");
                    // Check if the line contains the booking ID to remove
                    if (parts[0].equals("bookingID#" + bookingId)) {
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

    public static boolean attendBooking( String bookingId,  String learnerID){
        boolean isAttended = false;
        String coachName ="";
            try {
                String filePath = "learner_data" + File.separator + bookingMonth + File.separator + learnerID + ".txt";
                File inputFile = new File(filePath);
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                StringBuilder content = new StringBuilder();
                String currentLine;
                while ((currentLine = reader.readLine()) != null) {
                    // Split the line by semicolon
                    String[] parts = currentLine.split(";");
                    // Check if the line contains the booking ID to remove
                    if (parts[0].equals("bookingID#" + bookingId)) {
                        isAttended = true;
                        parts[2] = "booking_status#attended";
                        content.append(parts[0]+";"+parts[1]+";"+parts[2]+";"+
                                parts[3]+";"+parts[4]+";"+parts[5]).append(System.getProperty("line.separator"));
                        String[] keyValue = parts[4].split("#");
                        coachName = keyValue[1];
                    } else {
                        content.append(currentLine).append(System.getProperty("line.separator"));
                    }
                }
                reader.close();
                // Write the modified content back to the file
                BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
                writer.write(content.toString());
                writer.close();
                if(isAttended){
                    System.out.println("Your lesson for Booking ID: " + bookingId + " is attended successfully!");
                    reviewAndRating(coachName);
                }
                return isAttended;
            } catch (IOException e) {
                return isAttended;
            }
    }

    public static void reviewAndRating(String coachName){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Write a review for the lesson:");
            String review = scanner.nextLine();
            System.out.print("Enter a rating for the lesson from 1 to 5: ");
            int rating = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            String content = "review#"+review+";rating#"+rating;
            String filePath = "coach_data" + File.separator + "05"+ File.separator + coachName+".txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            // Write the new line to the file
            writer.write(content);
            writer.newLine(); // Add a newline character after the new line
            // Close the file
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean isLessonAlreadyBooked(int learnerID, Lesson lesson) {
        try {
            LocalDateTime lessonDateTime = lesson.getLessonDateTimeLDF();
            int month =   lessonDateTime.getMonthValue();
            String filePath = "learner_data" + File.separator + lesson.getLessonMonthValue() + File.separator + learnerID + ".txt";
            File inputFile = new File(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                // Split the line by semicolon
                String[] parts = currentLine.split(";");
                // Check if the line contains the booking ID to remove
                if (parts[5].equals("lesson_id#" + lesson.getLessonID()) && !parts[2].equals("booking_status#cancelled")) {
                    return true;
                }
            }
            reader.close();
            return  false;
        } catch (IOException e) {
            System.out.println("Invalid input! Please enter a valid learner ID.");
            return true;
        }
    }
}
