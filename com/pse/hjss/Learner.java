package com.pse.hjss;

public class Learner {

    public Learner(int ID, String name, int age, String gender, int currentGradeLevel, String emergencyContactNumber){
        this.ID = ID;
        this.name = name;
        this.age = age;
        this.currentGradeLevel = currentGradeLevel;
        this.emergencyContactNumber = emergencyContactNumber;
        this.gender = gender;
    }
    private int ID;
    private String emergencyContactNumber;
    private int currentGradeLevel;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    private String gender;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private int age;
   // private LocalDate dob;
    private String name;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEmergencyContactNumber() {
        return emergencyContactNumber;
    }

    public void setEmergencyContactNumber(String emergencyContactNumber) {
        this.emergencyContactNumber = emergencyContactNumber;
    }

    public int getCurrentGradeLevel() {
        return currentGradeLevel;
    }

    public void setCurrentGradeLevel(int currentGradeLevel) {
        this.currentGradeLevel = currentGradeLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return("ID: "+ getID()+", Name: "+getName()+", Age: "+getAge()+
                ", Gender: "+getGender()+
                ", CurrentGradeLevel: "+getCurrentGradeLevel()+
                ", Emergency contact number: "+getEmergencyContactNumber());
    }
}
