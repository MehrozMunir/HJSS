package com.pse.hjss;

import com.pse.hjss.Utils.CustomValidationException;

import java.io.*;
import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Manager {
    //Initialising the learnerID with a seven-digit number
    private static final AtomicInteger LEARNER_ID_GENERATOR = new AtomicInteger(11001);
    private static final AtomicInteger LESSON_ID_GENERATOR = new AtomicInteger(20100);
    private static final AtomicInteger Booking_ID_GENERATOR = new AtomicInteger(300157);
    public static HashMap<Integer, Learner> learnersHashMap = new HashMap<>();
    public static HashMap<Integer, Lesson> lessonsHashMap = new HashMap<>();
    public static ArrayList<String> coachesNamesArrayList = new ArrayList<>();
    public static final String BOOKING_MONTH ="05";
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
                        System.out.println(lesson);
                    } else if (filterValue.equalsIgnoreCase("W") && dayOfWeek == DayOfWeek.WEDNESDAY) {
                        System.out.println(lesson);
                    } else if (filterValue.equalsIgnoreCase("F") && dayOfWeek == DayOfWeek.FRIDAY) {
                        System.out.println(lesson);
                    } else if (filterValue.equalsIgnoreCase("S") && dayOfWeek == DayOfWeek.SATURDAY) {
                        System.out.println(lesson);
                    }
                }
                case "grade_level" -> {
                    if (filterValue.equalsIgnoreCase(lesson.getGradeLevel() + "")) {
                        System.out.println(lesson);
                    }
                }
                case "coach_name" -> {
                    if (filterValue.equalsIgnoreCase(lesson.getCoachName())) {
                        System.out.println(lesson);
                    }
                }
                default -> System.out.println(lesson);
            }
        }
    }
    public static void printLearnerReport(int month){
        try {
            String monthValue = String.format("%02d",month);
            TreeMap<Integer, Learner> sortedMap = new TreeMap<>(learnersHashMap);
            for (Map.Entry<Integer, Learner> entry : sortedMap.entrySet()) {
                Learner learner = entry.getValue();
                System.out.println(Utils.ANSI_BOLD+"---------------------------------------------------------------------------------");
                System.out.println("---------------------------------------------------------------------------------"+Utils.ANSI_RESET);
                System.out.println(Utils.ANSI_BOLD+"Learner ID: "+learner.getID()+"   Learner name: "+learner.getFirstName() + " "+learner.getLastName());
                System.out.println("Current grade level: "+learner.getCurrentGradeLevel()+"    Emergency contact number: "+learner.getEmergencyContactNumber()+Utils.ANSI_RESET);
                String filePath = "learner_data" + File.separator + monthValue + File.separator + learner.getID() + ".txt";
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
                System.out.println(Utils.ANSI_BOLD+"\nTotal lessons: "+(attended+booked+cancelled)+"\nbooked = " + booked + "   cancelled = " + cancelled + "   attended = " + attended+"\n"+Utils.ANSI_RESET);
            }
            } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printCoachReport( int month){
        try {
            String monthValue = String.format("%02d",month);
            for (String s : coachesNamesArrayList) {
                System.out.println(Utils.ANSI_BOLD+"---------------------------------------------------------------------------------");
                System.out.println("---------------------------------------------------------------------------------"+Utils.ANSI_RESET);
                System.out.println(Utils.ANSI_BOLD+"Coach name: " + s+Utils.ANSI_RESET);
                String filePath = "coach_data" + File.separator + monthValue + File.separator + s + ".txt";
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
                DecimalFormat decimalFormat = new DecimalFormat("#.#");
                String formattedRating = decimalFormat.format(averageRating);
                content.append("for ").append(numberOfRatings).append(" lessons.\n").append("Average Rating = ").append(formattedRating);

                if (numberOfRatings > 0)
                    System.out.println(content + "\n");
                else
                    System.out.println("There are no ratings for " + s + " for this month.\n");
            }
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void registerLearner(String firstName, String lastName, int age, String gender, int currentGradeLevel, String emergencyContactNumber){
            int id = generateLearnerID();
            learnersHashMap.put(id, new Learner(id, firstName, lastName, age, gender, currentGradeLevel, emergencyContactNumber));
            System.out.println(Utils.ANSI_GREEN+"Learner is added successfully!"+Utils.ANSI_RESET);
    }
    public static boolean bookALesson( int lessonID, int learnerID){
        boolean booked = false;
        Lesson lesson = lessonsHashMap.get(lessonID);
        Learner learner = learnersHashMap.get(learnerID);
        String status = isLessonBookedOrAttended(learnerID, lesson);
        switch (status) {
            case "booked" ->
                    System.out.println(Utils.ANSI_RED + "\nThe lesson with id: " + lessonID + " is already booked against the learner id: " + learnerID + Utils.ANSI_RESET);
            case "attended" ->
                    System.out.println(Utils.ANSI_RED + "\nThe lesson with id: " + lessonID + " is already attended against the learner id: " + learnerID + Utils.ANSI_RESET);
            case "error" ->
                    System.out.println(Utils.ANSI_RED + "\nThere is some error in retrieving the data. Try again!" + Utils.ANSI_RESET);
            default -> {
                if (learner.getCurrentGradeLevel() != lesson.getGradeLevel() && (learner.getCurrentGradeLevel() + 1) != lesson.getGradeLevel()) {
                    System.out.println(Utils.ANSI_RED + "\nThe learner's current grade level is " + learner.getCurrentGradeLevel() +
                            "\nThe learner can book a lesson only of his/her current grade level" +
                            " or a grade level +1." + Utils.ANSI_RESET);
                } else if (lesson.getAvailableSeats() <= 0) {
                    System.out.println(Utils.ANSI_RED + "\nThere are already 4 bookings for this time slot!" + Utils.ANSI_RESET);
                } else {
                    String bookingID = generateBookingID();
                    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy hh:mm a");
                    String booking_date_time = myFormatObj.format(LocalDateTime.now());
                    String content = "booking_id#" + bookingID + ";booking_date_time#" + booking_date_time +
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
                    System.out.println(Utils.ANSI_GREEN + "\nYour booking for the lesson id: " + lessonID + " is booked successfully! " +
                            "Your booking ID is: " + Utils.ANSI_BOLD + bookingID + Utils.ANSI_RESET);
                }
            }
        }
        return booked;
    }
   public static String cancelBooking( String bookingIdToRemove, String learnerID){
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
               if (parts[0].equals("booking_id#" + bookingIdToRemove)) {
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
                    if (parts[0].equals("booking_id#" + bookingId) ) {
                        switch (parts[2]) {
                            case "booking_status#cancelled" -> {
                                changed = "already_cancelled";
                                content.append(currentLine).append(System.lineSeparator());
                            }
                            case "booking_status#booked" -> {
                                changed = "already_booked";
                                content.append(currentLine).append(System.lineSeparator());
                            }
                            case "booking_status#attended" -> {
                                changed = "already_attended";
                                content.append(currentLine).append(System.lineSeparator());
                            }
                            default -> {
                                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy hh:mm a");
                                String booking_date_time = myFormatObj.format(LocalDateTime.now());
                                content.append("booking_id#").append(bookingId).append(";booking_date_time#").
                                        append(booking_date_time).append(";booking_status#booked;grade_level#").
                                        append(lesson.getGradeLevel()).append(";coach_name#").
                                        append(lesson.getCoachName()).append(";lesson_id#").append(lesson.getLessonID()).
                                        append(";").append(System.lineSeparator());
                                lesson.decrementAvailableSeats();
                                changed = "updated";
                            }
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
                    if (parts[0].equals("booking_id#" + bookingId)) {
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
                switch (attended) {
                    case "attended" -> {
                        System.out.println(Utils.ANSI_GREEN + "Your lesson for Booking ID: " + bookingId + " is attended successfully!" + Utils.ANSI_RESET);
                        if (learner.getCurrentGradeLevel() < lessonGradeLevel) {
                            learner.upGrade();
                            cancelBelowLevelLessons(learner.getID(), learner.getCurrentGradeLevel() - 1);
                        }
                        reviewAndRating(coachName);
                    }
                    case "cancelled" -> {
                        System.out.println(Utils.ANSI_RED + "The lesson with Booking ID: " + bookingId + " was cancelled." +
                                "\nYou can't attend a lesson that was cancelled." + Utils.ANSI_RESET + "\nTry again if you want to attend a different lesson.");
                        Runner.displayAttendASwimmingLessonMenu();
                    }
                    case "already_attended" -> {
                        System.out.println(Utils.ANSI_RED + "The lesson with Booking ID: " + bookingId + " is already attended." +
                                "\nYou can't attend a lesson that is already attended." + Utils.ANSI_RESET + "\nTry again if you want to attend a different booking.");
                        Runner.displayAttendASwimmingLessonMenu();
                    }
                    default -> {
                        System.out.println(Utils.ANSI_RED + "The entered Booking ID and Learner ID does not match.\nTry again by entering a valid Learner ID and Booking ID." + Utils.ANSI_RESET);
                        Runner.displayAttendASwimmingLessonMenu();
                    }
                }
            } catch (IOException e) {
                System.out.println(Utils.ANSI_RED+"The entered Booking ID and Learner ID does not match.\nTry again by entering a valid Learner ID and Booking ID."+Utils.ANSI_RESET);
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
                        System.out.println("Enter a rating for the lesson from 1 to 5.");
                        System.out.println("1: Very dissatisfied\n2: Dissatisfied\n3: Ok\n4: Satisfied\n5: Very Satisfied");
                        System.out.print("Enter your choice of rating from the above: ");
                        int rating = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character
                        if (rating <= 5 && rating >= 1) {
                            BufferedWriter writer = Utils.getBufferedWriter(coachName, review, rating);
                            // Close the file
                            writer.close();
                            System.out.println(Utils.ANSI_GREEN+"The review and rating for the lesson is recorded."+Utils.ANSI_RESET);
                            return;
                        } else {
                            throw new CustomValidationException("Rating can only be a number between 1 and 5." +
                                    "\nTry again by entering the correct rating.");
                        }
                    } catch (CustomValidationException e) {
                        System.out.println(e.getMessage());
                    }
                    catch (Exception e) {
                        System.out.println(Utils.ANSI_RED+"Invalid input! Please enter a valid value."+Utils.ANSI_RESET);
                        scanner.nextLine(); // Consume the newline character
                    }
                }

            } catch (Exception e) {
                System.out.println(Utils.ANSI_RED+"Invalid input! Please enter a valid value."+Utils.ANSI_RESET);
                scanner.nextLine(); // Consume the newline character
            }
        }
    }

    public static String isLessonBookedOrAttended(int learnerID, Lesson lesson) {
        try {
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
