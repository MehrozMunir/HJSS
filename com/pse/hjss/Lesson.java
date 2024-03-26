package com.pse.hjss;


import java.time.LocalDateTime;

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

    // public final ArrayList<Learner> learnersArrayList = new ArrayList<>();
    //public final ArrayList<Booking> bookingsArrayList = new ArrayList<>();
    private int availableSeats = 4;
    public Lesson(int lessonID, int gradeLevel, String coachName){
            this.lessonID = lessonID;
            this.gradeLevel = gradeLevel;
            this.coachName = coachName;
            this.lessonDateTime = LocalDateTime.now();
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

    public LocalDateTime getBookingDateTime() {
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
        return("Lesson ID: "+ getLessonID()+", Coach Name: "+getCoachName()+", Date and Time: "+getBookingDateTime()+
                ", Grade Level: "+getGradeLevel()+
                ", Available Seats: "+ (4 - this.getAvailableSeats()));
    }
}
