package com.cozentus.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RunningScripts {
	public static void main(String[] args) {
        DbManipulation dbManipulation = new DbManipulation();



        // Insert a subject
        boolean subjectInserted = dbManipulation.insertSubject("Mathematics");
        if (subjectInserted) {
            System.out.println("Subject inserted successfully.");
        } else {
            System.out.println("Subject insertion failed.");
        }



        // Insert a student
        boolean studentInserted = dbManipulation.insertStudent("John Doe", 85.5f, "Mathematics");
        if (studentInserted) {
            System.out.println("Student inserted successfully.");
        } else {
            System.out.println("Student insertion failed.");
        }



        // Get subject by ID
        ArrayList<Object> subjectData = dbManipulation.getSubjectById(1);
        System.out.println("Subject data: " + subjectData);



        // Get student by ID
        ArrayList<Object> studentData = dbManipulation.getStudentById(1);
        System.out.println("Student data: " + studentData);



        // Get all subjects
        //ResultSet subjectsResultSet = dbManipulation.getAllSubjects();
        // Process the result set as needed
        ResultSet subjectsResultSet = dbManipulation.getAllSubjects();
        if (subjectsResultSet != null) {
            try {
                while (subjectsResultSet.next()) {
                    int subjectId = subjectsResultSet.getInt("subject_id");
                    String subjectName = subjectsResultSet.getString("subject_name");
                    System.out.println("Subject ID: " + subjectId + ", Subject Name: " + subjectName);
                }
                subjectsResultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



        // Get all students
       // ResultSet studentsResultSet = dbManipulation.getAllStudents();
        // Process the result set as needed
        ResultSet studentsResultSet = dbManipulation.getAllStudents();
        if (studentsResultSet != null) {
            try {
                while (studentsResultSet.next()) {
                    int studentId = studentsResultSet.getInt("student_id");
                    String studentName = studentsResultSet.getString("student_name");
                    float score = studentsResultSet.getFloat("score");
                    int subjectId = studentsResultSet.getInt("subject_id");
                    System.out.println("Student ID: " + studentId + ", Student Name: " + studentName +
                            ", Score: " + score + ", Subject ID: " + subjectId);
                }
                studentsResultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
