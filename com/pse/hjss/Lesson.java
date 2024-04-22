package com.pse.hjss;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class Lesson {
    private final int gradeLevel;
    private final int lessonID;
    private final LocalDateTime lessonDateTime;
    private final String coachName;

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void decrementAvailableSeats() {
        this.availableSeats -=1;
    }
    public void incrementAvailableSeats() {
        this.availableSeats +=1;
    }
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
    public int getLessonID() {
        return lessonID;
    }
    public int getGradeLevel() {
        return gradeLevel;
    }

    public String getLessonDateTime() {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy hh:mm a");
        return myFormatObj.format(lessonDateTime);
    }
    public LocalDateTime getLessonDateTimeLDF() {
        return lessonDateTime;
    }


    public String getCoachName() {
        return coachName;
    }
    @Override
    public String toString() {
        return("\nLesson ID: "+ getLessonID()+", Coach Name: "+getCoachName()+", Date and Time: "+getLessonDateTime()+
                ", Grade Level: "+getGradeLevel()+
                ", Available Seats: "+  this.getAvailableSeats());
    }
}
