package com.cozentus.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DbManipulation {
	public boolean insertSubject(String type) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Subject (subject_name) VALUES (?)")) {

 

            preparedStatement.setString(1, type);
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

 

    public ArrayList<Object> getSubjectById(int id) {
        ArrayList<Object> subjectData = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Subject WHERE subject_id = ?")) {

 

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int subjectId = resultSet.getInt("subject_id");
                String subjectName = resultSet.getString("subject_name");
                subjectData.add(subjectId);
                subjectData.add(subjectName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjectData;
    }

 

    public ResultSet getAllSubjects() {
        try {
            Connection connection = DbUtil.getConnection();
            return connection.createStatement().executeQuery("SELECT * FROM Subject");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

 

//    public boolean insertStudent(String studentName, float score, String subjectName) {
//        try (Connection connection = DbUtil.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(
//                     "INSERT INTO Student (student_name, score, subject_id) VALUES (?, ?, " +
//                             "(SELECT subject_id FROM Subject WHERE subject_name = ?))")) {
//
//            preparedStatement.setString(1, studentName);
//            preparedStatement.setFloat(2, score);
//            preparedStatement.setString(3, subjectName);
//            int rowsInserted = preparedStatement.executeUpdate();
//            return rowsInserted > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
    public boolean insertStudent(String studentName, float score, String subjectName) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Student (student_name, score, subject_id) SELECT ?, ?, subject_id FROM Subject WHERE subject_name = ?")) {

 

            preparedStatement.setString(1, studentName);
            preparedStatement.setFloat(2, score);
            preparedStatement.setString(3, subjectName);
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

 

 

    public ArrayList<Object> getStudentById(int id) {
        ArrayList<Object> studentData = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Student WHERE student_id = ?")) {

 

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int studentId = resultSet.getInt("student_id");
                String studentName = resultSet.getString("student_name");
                float score = resultSet.getFloat("score");
                int subjectId = resultSet.getInt("subject_id");
                studentData.add(studentId);
                studentData.add(studentName);
                studentData.add(score);
                studentData.add(subjectId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentData;
    }

 

    public ResultSet getAllStudents() {
        try {
            Connection connection = DbUtil.getConnection();
            return connection.createStatement().executeQuery("SELECT * FROM Student");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
