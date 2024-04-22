package com.pse.hjss;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class LearnerTest {

    @Test
    void upGrade() {
        String firstName = "Alia";
        String lastName = "Doe";
        int age = 5;
        String gender = "female";
        int currentGradeLevel = 3;
        String emergencyContactNumber = "1234567890";
        int id = Manager.generateLearnerID();
        Learner learner = new Learner(id, firstName,lastName, age, gender, currentGradeLevel, emergencyContactNumber);
        learner.upGrade(); //upGrading learner level from 3 to 4
        int expectedResult = 4;
        int actualResult = learner.getCurrentGradeLevel();
        assertEquals(expectedResult,actualResult);
    }
}