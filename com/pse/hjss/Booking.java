package com.pse.hjss;

import java.time.LocalDateTime;

public class Booking {
    private int bookingID, gradeLevel, availableSeats = 4;
    private LocalDateTime bookingDateTime;
    private String coachName;

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public int getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void decrementAvailableSeats() {
        this.availableSeats-=1;
    }

    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public void setBookingDataTime(LocalDateTime bookingDataTime) {
        this.bookingDateTime = bookingDataTime;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }
    @Override
    public String toString() {
        return("ID: "+ getBookingID()+", Coach Name: "+getCoachName()+", Date and Time: "+getBookingDateTime()+
                ", Grade Level: "+getGradeLevel()+
                ", Available Seats: "+getAvailableSeats());
    }
}
