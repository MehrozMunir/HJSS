package com.pse.hjss;

public class Booking {
    private String bookingID;
    private int lessonID;
    private Learner learner;

    public Booking(Learner learner, int lessonID, String bookingID){
        this.learner = learner;
        this.lessonID = lessonID;
        this.bookingID = bookingID;
    }
    public String getBookingID() {
        return bookingID;
    }
    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public int getLessonID() {
        return lessonID;
    }

    public void setLessonID(int lessonID) {
        this.lessonID = lessonID;
    }

    public Learner getLearner() {
        return learner;
    }

    public void setLearner(Learner learner) {
        this.learner = learner;
    }

    @Override
    public String toString() {
        return("Booking ID: "+ getBookingID()+
                ", lessonID: "+getLessonID()+
                ", learner: "+ getLearner());
    }
}
