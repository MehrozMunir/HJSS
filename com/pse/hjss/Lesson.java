package com.pse.hjss;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class Lesson {
    private int gradeLevel;
    private int lessonID;
    private LocalDateTime lessonDateTime;
    private String coachName;

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void decrementAvailableSeats() {
        this.availableSeats -=1;
    }
    public void incrementAvailableSeats() {
        this.availableSeats +=1;
    }

    // public final ArrayList<Learner> learnersArrayList = new ArrayList<>();
    //public final ArrayList<Booking> bookingsArrayList = new ArrayList<>();
    private int availableSeats = 4;
    public Lesson(int lessonID, int gradeLevel, String coachName, String dateTime){
        this.lessonID = lessonID;
        this.gradeLevel = gradeLevel;
        this.coachName = coachName;
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("E, MMM dd yyyy hh:mm a")
                .toFormatter(Locale.ENGLISH);
        this.lessonDateTime = LocalDateTime.parse(dateTime, formatter);
    }
   /* public  boolean addABooking(Learner learner){
        if(this.bookingsArrayList.size()>=4) {
            return false;
        }
        else{
            bookingsArrayList.add(new Booking(learner, this.lessonID));

            return true;
        }
    }*/
    public int getLessonID() {
        return lessonID;
    }

    public void setBookingID(int lessonID) {
        this.lessonID = lessonID;
    }

    public int getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public String getLessonDateTime() {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy hh:mm a");
        return myFormatObj.format(lessonDateTime);
    }
    public LocalDateTime getLessonDateTimeLDF() {
        return lessonDateTime;
    }

    public void setBookingDataTime(LocalDateTime lessonDateTime) {
        this.lessonDateTime = lessonDateTime;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }
    @Override
    public String toString() {
        return("Lesson ID: "+ getLessonID()+", Coach Name: "+getCoachName()+", Date and Time: "+getLessonDateTime()+
                ", Grade Level: "+getGradeLevel()+
                ", Available Seats: "+  this.getAvailableSeats());
    }
    public String getLessonMonthValue(){
        LocalDateTime lessonDateTime = this.getLessonDateTimeLDF();
        return String.format("%02d",lessonDateTime.getMonthValue());
    }
}
