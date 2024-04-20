package com.pse.hjss;

import java.io.*;

public class Utils {
    static class CustomValidationException extends Exception {
        public CustomValidationException(String message) {
            super(message);
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
            /*if (deleted) {
                System.out.println("Existing folder and its contents deleted successfully!");
            } else {
                System.err.println("Failed to delete the existing folder.");
            }*/
        }
        if(coach_folder.exists())
            deleteFolder(coach_folder);

        // Create a new folder
        //boolean created = folder.mkdir();
        /*if (created) {
            System.out.println("New folder created successfully!");
        } else {
            System.err.println("Failed to create the new folder.");
        }*/
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
    public static void printIndividualLearnerReport(int learnerID){
        try {
                Learner learner = Manager.learnersHashMap.get(learnerID);
                System.out.println("--------------------------------------------------------------");
                System.out.println("Learner ID: "+learner.getID()+"   Learner name: "+learner.getFirstName() + " "+learner.getLastName());
                System.out.println("Current grade level: "+learner.getCurrentGradeLevel()+"    Emergency contact number: "+learner.getEmergencyContactNumber());
                String filePath = "learner_data" + File.separator + Manager.REPORT_MONTH + File.separator + learner.getID() + ".txt";
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
