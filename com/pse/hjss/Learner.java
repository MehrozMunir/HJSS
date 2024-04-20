package com.pse.hjss;

public class Learner {

    public Learner(int ID, String firstName, String lastName, int age, String gender, int currentGradeLevel, String emergencyContactNumber){
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
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
    private String firstName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private String lastName;

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
    public void upGrade(){
        this.currentGradeLevel +=1;
    }

    @Override
    public String toString() {
        return("ID: "+ getID()+", Name: "+getFirstName()+ " "+ getLastName()+", Age: "+getAge()+
                ", Gender: "+getGender()+
                ", CurrentGradeLevel: "+getCurrentGradeLevel()+
                ", Emergency contact number: "+getEmergencyContactNumber());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}