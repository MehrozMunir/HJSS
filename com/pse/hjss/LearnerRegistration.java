package com.pse.hjss;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;

public class LearnerRegistration extends JFrame{
    private JLabel lblName;

    private JTextField textFieldName;
    private JPanel mainPanel;
    private JLabel lblGender;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JPanel fieldsPanel;
    private JLabel lblGradeLevel;
    private JComboBox cmbBGradeLevel;
    private JLabel lblRegisterLearner;
    private JPanel calendarPanel;
    private JLabel lblDob;

    public LearnerRegistration(){
        setContentPane(mainPanel);
        setSize(450,300);
        setTitle("HJJS");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JDateChooser dateChooser = new JDateChooser(); //initializing the calendar jpCalendar.add(dateChooser);
        dateChooser.setSize(new Dimension(30, 50));
        calendarPanel.add(dateChooser);
        setVisible(true);
    }
    public static void main(String[] args) {
        LearnerRegistration learnerRegistration = new LearnerRegistration();
    }
}
