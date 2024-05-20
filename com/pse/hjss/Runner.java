package com.pse.hjss;

import java.util.Scanner;

import static com.pse.hjss.Manager.getManagerInstance;

public class Runner {
    public static void main(String[] args) {
        AddLearnersLessons.addLearners();
        AddLearnersLessons.addCoaches();
        AddLearnersLessons.addLessons();
        Utils.initializeFoldersAndFiles();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Utils.displayMainMenu();
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // Call function to handle registering a learner
                    displayLearnerRegistrationMenu();
                    System.out.println("\nPress Enter to return to the main menu...");
                    scanner.nextLine(); // Wait for Enter key
                    break;
                case "2":
                    displayBookASwimmingLessonMenu();
                    // Call function to handle booking a lesson
                    System.out.println("\nPress Enter to return to the main menu...");
                    scanner.nextLine(); // Wait for Enter key
                    break;
                case "3":
                    displayCancelASwimmingLessonMenu();
                    // Call function to handle cancel booking
                    System.out.println("\nPress Enter to return to the main menu...");
                    scanner.nextLine(); // Wait for Enter key
                    break;
                case "4":
                    displayChangeASwimmingLessonMenu();
                    // Call function to change booking a lesson
                    System.out.println("\nPress Enter to return to the main menu...");
                    scanner.nextLine(); // Wait for Enter key
                    break;
                case "5":
                    displayAttendASwimmingLessonMenu();
                    // Call function to change booking a lesson
                    System.out.println("\nPress Enter to return to the main menu...");
                    scanner.nextLine(); // Wait for Enter key
                    break;
                case "6":
                    System.out.println("List of registered learners!");
                    displayLearnersList();
                    System.out.println("\nPress Enter to return to the main menu...");
                    scanner.nextLine(); // Wait for Enter key
                    break;
                case "7":
                    System.out.println(Utils.ANSI_BOLD+"List of all Lessons!"+Utils.ANSI_RESET);
                    getManagerInstance().printLessonsList("","");
                    System.out.println("\nPress Enter to return to the main menu...");
                    scanner.nextLine(); // Wait for Enter key
                    break;
                case "8":
                    System.out.println(Utils.ANSI_BOLD+"Names of all the Coaches...."+Utils.ANSI_RESET);
                    Utils.printCoachesNames();
                    System.out.println("\nPress Enter to return to the main menu...");
                    scanner.nextLine(); // Wait for Enter key
                    break;
                case "9":
                    System.out.println(Utils.ANSI_BOLD+"--------------Monthly Learner Report--------------"+Utils.ANSI_RESET);
                    Utils.displayMonthlyLearnerReport();
                    System.out.println("\nPress Enter to return to the main menu...");
                    scanner.nextLine(); // Wait for Enter key
                    break;
                case "10":
                    System.out.println(Utils.ANSI_BOLD+"-------------Monthly Coach Report----------------"+Utils.ANSI_RESET);
                    Utils.displayMonthlyCoachReport();
                    System.out.println("\nPress Enter to return to the main menu...");
                    scanner.nextLine(); // Wait for Enter key
                    break;
                case "0":
                    System.out.println("Exiting the application. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println(Utils.ANSI_RED+"Invalid choice. Please enter a valid option."+Utils.ANSI_RESET);
                    break;
            }
        }
    }

    public static void displayLearnerRegistrationMenu() {
        Scanner scanner = new Scanner(System.in);
       String firstName, lastName, gender, emergencyContactNumber;
       int age, currentGradeLevel;
        while (true) {
            try {
                System.out.println(Utils.ANSI_BOLD+"\n-----------------------Register a new Learner-----------------------"+Utils.ANSI_RESET);
                System.out.print("Enter Learner's First Name: ");
                firstName = scanner.nextLine();
                if(firstName.equalsIgnoreCase(""))
                    throw new Utils.CustomValidationException("First name can't be empty.\nTry again!");
                System.out.print("Enter Learner's Last Name: ");
                lastName = scanner.nextLine();
                System.out.print("Enter Learner's Age: ");
                age = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if(age < 4 || age > 11)
                    throw new Utils.CustomValidationException("Age of the learner can only be between 4 and 11.\nTry again!");
                System.out.print("Enter Learner's Gender male/female/other: ");
                gender = scanner.nextLine();
                if(!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female") && !gender.equalsIgnoreCase("other"))
                    throw new Utils.CustomValidationException("Gender can be only male, female or other.\nTry again!");
                System.out.print("Enter Learner's Current Grade Level: ");
                currentGradeLevel = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if(currentGradeLevel<0 || currentGradeLevel>5)
                    throw new Utils.CustomValidationException("Current grade level can only be between 0 and 5.\nTry again!");
                System.out.print("Enter Learner's Emergency Contact Number: ");
                emergencyContactNumber = scanner.nextLine();
                getManagerInstance().registerLearner(firstName, lastName, age, gender, currentGradeLevel, emergencyContactNumber);
                return;
            }
            catch (Utils.CustomValidationException e){
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(Utils.ANSI_RED+"Invalid input! Please enter a valid value."+Utils.ANSI_RESET);
                scanner.nextLine(); // Consume the newline character
            }
        }
    }

    public static void displayBookASwimmingLessonMenu(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(Utils.ANSI_BOLD+"\n--------------------Book a Swimming Lesson--------------------------"+Utils.ANSI_RESET);
            try {
                System.out.println("If you want to return to main menu, enter 0(zero).");
                System.out.print("Enter Learner ID: ");
                int learnerID = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if(learnerID == 0)
                    return;
                int lessonID;
                if(getManagerInstance().learnersHashMap.containsKey(learnerID))
                    Utils.displayLessonsViewMenu();
                else
                    throw new Utils.CustomValidationException("The learner ID: "+learnerID+" does not exist. Please enter a valid learner ID.");
                System.out.print("Enter your choice: ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                            System.out.print("Enter M for Monday, W for Wednesday, F for Friday, S for Saturday: ");
                            String selectedDay = scanner.next();
                            scanner.nextLine(); // Wait for Enter key
                            if (selectedDay.equalsIgnoreCase("m") || selectedDay.equalsIgnoreCase("w")
                                    || selectedDay.equalsIgnoreCase("f") || selectedDay.equalsIgnoreCase("s")){
                                getManagerInstance().printLessonsList("day", selectedDay);
                                System.out.print("Enter the Lesson ID from the above lessons that you want to book: ");
                                lessonID = scanner.nextInt();
                                scanner.nextLine(); // Consume the newline character
                                if(getManagerInstance().lessonsHashMap.containsKey(lessonID)) {
                                    boolean booked = getManagerInstance().bookALesson(lessonID, learnerID);
                                    if(booked)
                                        throw new Utils.CustomValidationException(Utils.ANSI_RESET+"If you want to book another lesson, carry on!");
                                    else
                                        throw new Utils.CustomValidationException("Try again!.");
                                }
                                else{
                                    throw new Utils.CustomValidationException("Enter a valid lesson ID.");
                                }
                            }
                            else {
                                 throw new Utils.CustomValidationException("Please enter a valid day value such as M, W, F or S.");
                            }
                    case "2":
                        System.out.print("Enter the grade level for which you want to see the lessons: ");
                        int grade_level = scanner.nextInt();
                        scanner.nextLine(); // Wait for Enter key
                        if (grade_level == 1 || grade_level == 2
                                || grade_level == 3 || grade_level == 4 || grade_level == 5){
                            getManagerInstance().printLessonsList("grade_level", grade_level + "");
                            // Call function to handle booking a lesson
                            System.out.print("Enter the Lesson ID from the above lessons that you want to book: ");
                            lessonID = scanner.nextInt();
                            scanner.nextLine(); // Consume the newline character
                            if(getManagerInstance().lessonsHashMap.containsKey(lessonID)) {
                                boolean booked = getManagerInstance().bookALesson(lessonID, learnerID);
                                if(booked)
                                    throw new Utils.CustomValidationException(Utils.ANSI_RESET+"If you want to book another lesson, carry on!");
                                else
                                    throw new Utils.CustomValidationException("Try again!.");
                            }
                            else{
                                throw new Utils.CustomValidationException("Enter a valid lesson ID.");
                            }
                        }
                        else {
                            throw new Utils.CustomValidationException("Please enter a valid grade value from 1 to 5.");
                        }
                    case "3":
                        System.out.print("Enter the coach name whose lessons you want to see: ");
                        String coach_name1 = scanner.nextLine();
                        String coach_name = coach_name1.substring(0, 1).toUpperCase() + coach_name1.substring(1).toLowerCase();
                        if(getManagerInstance().coachesNamesArrayList.contains(coach_name)) {
                            getManagerInstance().printLessonsList("coach_name", coach_name);
                            // Call function to handle booking a lesson
                            System.out.print("Enter the Lesson ID from the above lessons that you want to book: ");
                            lessonID = scanner.nextInt();
                            scanner.nextLine(); // Consume the newline character
                            if(getManagerInstance().lessonsHashMap.containsKey(lessonID)) {
                                boolean booked = getManagerInstance().bookALesson(lessonID, learnerID);
                                if(booked)
                                    throw new Utils.CustomValidationException(Utils.ANSI_RESET+"If you want to book another lesson, carry on!");
                                else
                                    throw new Utils.CustomValidationException("Try again!.");
                            }
                            else{
                                throw new Utils.CustomValidationException("Enter a valid lesson ID.");
                            }
                        }
                        else{
                            throw new Utils.CustomValidationException("The coach name entered does not exist.");
                        }
                    case "4":
                        getManagerInstance().printLessonsList("", "");
                        // Call function to handle booking a lesson
                        System.out.print("Enter the Lesson ID from the above lessons that you want to book: ");
                        lessonID = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character
                        if(getManagerInstance().lessonsHashMap.containsKey(lessonID)) {
                            boolean booked = getManagerInstance().bookALesson(lessonID, learnerID);
                            if(booked)
                                throw new Utils.CustomValidationException(Utils.ANSI_RESET+"If you want to book another lesson, carry on!");
                            else
                                throw new Utils.CustomValidationException("Try again!.");
                        }
                        else{
                            throw new Utils.CustomValidationException("Enter a valid lesson ID.");
                        }
                    default:
                        System.out.println(Utils.ANSI_RED+"Invalid choice. Please enter a valid option."+Utils.ANSI_RESET);
                        break;
                }
            } catch (Utils.CustomValidationException e){
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(Utils.ANSI_RED+"Invalid input! Please enter a valid value."+Utils.ANSI_RESET);
                scanner.nextLine(); // Consume the newline character
            }
        }

    }
    public static void displayCancelASwimmingLessonMenu() {
        while(true) {
            System.out.println(Utils.ANSI_BOLD+"\n----------------------Cancel a Swimming Lesson-------------------"+Utils.ANSI_RESET);
            Scanner scanner = new Scanner(System.in);
            System.out.println("If you want to return to main menu, enter 0(zero).");
            System.out.print("Enter Learner ID: ");
            try {
                int learnerID = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if(learnerID == 0)
                    return;
                if (!getManagerInstance().learnersHashMap.containsKey(learnerID))
                    throw new Utils.CustomValidationException("The entered learner ID does not exist, try again.");
                System.out.print("Enter the Booking ID that you want to cancel, e.g. B300101: ");
                String bookingID = scanner.next();
                scanner.nextLine(); // Consume the newline character
                // for my application, the lessons for the month of May can only be booked and cancelled
                String cancelled = getManagerInstance().cancelBooking(bookingID, "" + learnerID);
                switch (cancelled) {
                    case "cancelled" -> {
                        System.out.println(Utils.ANSI_GREEN + "The lesson with Booking ID: " + bookingID + " is cancelled successfully!" + Utils.ANSI_RESET);
                        return;
                    }
                    case "error" -> {
                        System.out.println(Utils.ANSI_RED + "The booking ID: " + bookingID + "  against the learner: " + learnerID +
                                " is not present." + Utils.ANSI_RESET + "\nTry again and enter the correct Learner ID and Booking ID.");
                        throw new Utils.CustomValidationException("");
                    }
                    case "already_cancelled" -> {
                        System.out.println(Utils.ANSI_RED + "The booking ID: " + bookingID + "  against the learner: " + learnerID +
                                " is already cancelled." + Utils.ANSI_RESET + "\nTry again if you want to cancel another booking.");
                        throw new Utils.CustomValidationException("");
                    }
                    case "already_attended" -> {
                        System.out.println(Utils.ANSI_RED + "The lesson for the booking ID: " + bookingID + "  is already attended by the learner: " + learnerID +
                                " and, therefore, can't be cancelled." + Utils.ANSI_RESET + "\nTry again if you want to cancel another booking.");
                        throw new Utils.CustomValidationException("");
                    }
                }
            } catch (Utils.CustomValidationException e) {
                System.out.print(e.getMessage());
            } catch (Exception e) {
                System.out.println(Utils.ANSI_RED+"Invalid input! Please enter a valid value."+Utils.ANSI_RESET);
                scanner.nextLine(); // Consume the newline character
            }
        }
    }
    public static void displayAttendASwimmingLessonMenu() {
        while(true) {
            System.out.println(Utils.ANSI_BOLD+"\n----------------------Attend a Swimming Lesson------------------------"+Utils.ANSI_RESET);
            Scanner scanner = new Scanner(System.in);
            System.out.println("If you want to return to main menu, enter 0(zero).");
            System.out.print("Enter Learner ID: ");
            try {
                int learnerID = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if(learnerID == 0)
                    return;
                if (!getManagerInstance().learnersHashMap.containsKey(learnerID))
                    throw new Utils.CustomValidationException("The entered learner ID does not exist, try again.\n");
                System.out.print("Enter the Booking ID, e.g. B300101, for the lesson that you want to attend: ");
                String bookingID = scanner.next();
                scanner.nextLine(); // Consume the newline character
                getManagerInstance().attendBooking(bookingID, "" + learnerID);
            } catch (Utils.CustomValidationException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(Utils.ANSI_RED+"Invalid input! Please enter a valid value."+Utils.ANSI_RESET);
                scanner.nextLine(); // Consume the newline character
            }
        }
    }
    public static void displayChangeASwimmingLessonMenu() {
        while(true) {
            System.out.println(Utils.ANSI_BOLD+"\n--------------------Change/Update a Swimming Lesson------------------------"+Utils.ANSI_RESET);
            Scanner scanner = new Scanner(System.in);
            System.out.println("If you want to return to main menu, enter 0(zero).");
            System.out.print("Enter Learner ID: ");
            try {
                int learnerID = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if(learnerID == 0)
                    return;
                if (!getManagerInstance().learnersHashMap.containsKey(learnerID))
                    throw new Utils.CustomValidationException("The entered learner ID does not exist, try again.\n");
                System.out.print("Enter the Booking ID, e.g. B300101, for the booking that you want to change: ");
                String bookingID = scanner.next();
                scanner.nextLine(); // Consume the newline character
                getManagerInstance().printLessonsList("", "");
                System.out.print("Enter the Lesson ID from the above lessons that you want to book now: ");
                int lessonID = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if(!getManagerInstance().lessonsHashMap.containsKey(lessonID))
                    throw new Utils.CustomValidationException("The entered learner ID does not exist, try again.\n");
                String output = getManagerInstance().changeBooking(bookingID, "" + learnerID, lessonID);
                switch (output) {
                    case "updated" ->
                            System.out.println(Utils.ANSI_GREEN + "Your Booking ID: " + bookingID + " is now updated for the lesson: " + lessonID + Utils.ANSI_RESET);
                    case "full_booked" -> {
                        System.out.println(Utils.ANSI_RED + "There are already 4 bookings for this time slot! Try again to book a different lesson." + Utils.ANSI_RESET);
                        throw new Utils.CustomValidationException("");
                    }
                    case "already_cancelled" -> {
                        System.out.println(Utils.ANSI_RED + "The entered Booking ID: " + bookingID + " has a status of cancelled against the learner id: " + learnerID +
                                " and, therefore, this booking can't be changed.\nIf a learner wants to book this lesson, a new booking should be made." + Utils.ANSI_RESET);
                        throw new Utils.CustomValidationException("");
                    }
                    case "already_booked" -> {
                        System.out.println(Utils.ANSI_RED + "The entered Lesson ID: " + lessonID + " is already booked against the Learner with Learner ID: " + learnerID +
                                " and, therefore, can't be re-booked.\nTry again with a different lesson." + Utils.ANSI_RESET);
                        throw new Utils.CustomValidationException("");
                    }
                    case "already_attended" -> {
                        System.out.println(Utils.ANSI_RED + "The entered Lesson ID: " + lessonID + " is already attended by the Learner with Learner ID: " + learnerID +
                                " and, therefore, it can't be booked again.\nTry again with a different lesson." + Utils.ANSI_RESET);
                        throw new Utils.CustomValidationException("");
                    }
                    case "grade_level_error" -> {
                        Learner learner = getManagerInstance().learnersHashMap.get(learnerID);
                        System.out.println(Utils.ANSI_RED + "\nThe learner's current grade level is " + learner.getCurrentGradeLevel() + Utils.ANSI_RESET +
                                "\nThe learner can book a lesson only of his/her current grade level" +
                                " or a grade level +1.");
                        throw new Utils.CustomValidationException("");
                    }
                    default -> {
                        System.out.println(Utils.ANSI_RED + "The booking ID: " + bookingID + "  against the learner: " + learnerID +
                                " is not present." + Utils.ANSI_RESET + "\nTry again and enter the correct learner ID and booking ID.");
                        throw new Utils.CustomValidationException("");
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
    public static void displayLearnersList() {
        getManagerInstance().printLearnersList();
    }
}
