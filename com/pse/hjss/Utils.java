package com.pse.hjss;

import java.io.File;

public class Utils {


    static class CustomValidationException extends Exception {
        public CustomValidationException(String message) {
            super(message);
        }
    }

    public static void initializeFoldersAndFiles() {
        String learner_folder_path = "learner_data/"+Manager.bookingMonth; // Replace with your desired folder path
        File learner_folder = new File(learner_folder_path);
        String coach_folder_path = "coach_data/"+Manager.bookingMonth; // Replace with your desired folder path
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

}
