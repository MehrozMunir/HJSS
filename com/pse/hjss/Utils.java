package com.pse.hjss;

import java.io.*;
import java.util.Scanner;

public class Utils {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BOLD = "\u001B[1m";
    static class CustomValidationException extends Exception {
        public CustomValidationException(String message) {
            super(ANSI_RED+message+ANSI_RESET);
        }
    }

    public static void initializeFoldersAndFiles() {
        String learner_folder_path = "learner_data/"+Manager.BOOKING_MONTH; // Replace with your desired folder path
        File learner_folder = new File(learner_folder_path);
        String coach_folder_path = "coach_data/"+Manager.BOOKING_MONTH; // Replace with your desired folder path
        File coach_folder = new File(coach_folder_path);
        // Delete existing folder (if it exists) along with its contents
        if (learner_folder.exists()) {
            deleteFolder(learner_folder);
        }
        if(coach_folder.exists())
            deleteFolder(coach_folder);
    }

    private static void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                    file.delete();
                }
            }
    }
    static BufferedWriter getBufferedWriter(String coachName, String review, int rating) throws IOException {
        String content = "review#" + review + ";rating#" + rating;
        String filePath = "coach_data" + File.separator + Manager.BOOKING_MONTH + File.separator + coachName + ".txt";
        File file = new File(filePath);
        file.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
        // Write the new line to the file
        writer.write(content);
        writer.newLine(); // Add a newline character after the content
        return writer;
    }

    public static void printCoachesNames(){
        int number = 1;
        for (String value : Manager.coachesNamesArrayList) {
            System.out.println(number+"."+value);
            number++;
        }
    }
    public static void printIndividualLearnerReport(int learnerID, int month){
        try {
                String monthValue = String.format("%02d",month);
                Learner learner = Manager.learnersHashMap.get(learnerID);
                System.out.println("---------------------------------------------------------------------------");
                System.out.println("---------------------------------------------------------------------------");
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
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void displayMonthlyCoachReport() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("If you want to return to main menu, enter 0(zero).\nOR");
                System.out.print("Enter the month number for the coach report(e.g., 04 for April): ");
                int month = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if (month == 0) {
                    return;
                }
                else if(month!=4 && month!=5){
                    throw  new Utils.CustomValidationException("The report is only available for the past month i.e. April (4th month) and the current month i.e. May(5th month).\nSo, enter 04 or 05 to see the report.");
                }
                else {
                    Manager.printCoachReport(month);
                }
            } catch (Utils.CustomValidationException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(Utils.ANSI_RED+"Invalid input! Please enter a valid value."+Utils.ANSI_RESET);
                scanner.nextLine(); // Consume the newline character
            }
        }
    }
    public static void displayMainMenu() {
        System.out.println(Utils.ANSI_BOLD+"\u001B[33mWelcome to Hatfield Junior Swimming School (HJSS)\u001B[0m");
        System.out.println("1.  Register a new learner");
        System.out.println("2.  Book a swimming lesson for a learner");
        System.out.println("3.  Cancel a swimming lesson for a learner");
        System.out.println("4.  Change/Update a swimming lesson for a learner");
        System.out.println("5.  Attend a swimming lesson");
        System.out.println("6.  Display Learners List");
        System.out.println("7.  Display all Lessons List");
        System.out.println("8.  Display Coaches Names");
        System.out.println("9.  Monthly Learner Report");
        System.out.println("10. Monthly Coach Report");
        System.out.println("0.  Exit");
    }

    public static void displayLessonsViewMenu() {
        System.out.println("How do you want the lessons to be displayed?");
        System.out.println("1. By specifying the day");
        System.out.println("2. By specifying the grade level");
        System.out.println("3. By specifying the coach's name");
        System.out.println("4. All lessons");
    }
    public static void displayMonthlyLearnerReport() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                System.out.println("If you want to return to main menu, enter 0(zero).\nOR");
                System.out.print("Enter the month number for the report(e.g., 04 for April): ");
                int month = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if(month == 0){
                    return;
                }
                else if(month!=4 && month!=5){
                    throw  new Utils.CustomValidationException("The report is only available for the past month i.e. April (4th month) and the current month i.e. May(5th month).\nSo, enter 04 or 05 to see the report.");
                }
                else {
                    System.out.println("1. See All Learners Report");
                    System.out.println("2. See Individual Learner Report");
                    System.out.println("3. Go Back to Main Menu");
                    System.out.print("Enter your choice: ");
                    String choice = scanner.nextLine();
                    switch (choice) {
                        case "1":
                            Manager.printLearnerReport(month);
                            return;
                        case "2":
                            System.out.print("Enter Learner ID: ");
                            int learnerID = scanner.nextInt();
                            scanner.nextLine(); // Consume the newline character
                            if (!Manager.learnersHashMap.containsKey(learnerID)) {
                                throw new Utils.CustomValidationException("The entered Learner ID does not exist, try again.\n");
                            }
                            else {
                                Utils.printIndividualLearnerReport(learnerID,month);
                                return;
                            }
                        case "3":
                            return;
                        default:
                            throw new Utils.CustomValidationException("Invalid choice, try again!\n");

                    }
                }
            }
            catch (Utils.CustomValidationException e){
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(Utils.ANSI_RED+"Invalid input! Please enter a valid value."+Utils.ANSI_RESET);
                scanner.nextLine(); // Consume the newline character
            }
        }
    }

}