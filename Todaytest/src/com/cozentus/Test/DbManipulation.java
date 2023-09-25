package com.cozentus.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DbManipulation {
	public boolean insertSubject(String type) {
        try {
            Connection connection = DbUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO subject (name) VALUES (?)");
            statement.setString(1, type);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Object> getSubjectById(int id) {
        ArrayList<Object> result = new ArrayList<>();
        try {
            Connection connection = DbUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM subject WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result.add(resultSet.getInt("id"));
                result.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ResultSet getAllSubjects() {
        try {
            Connection connection = DbUtil.getConnection();
            Statement statement = connection.createStatement();
            return statement.executeQuery("SELECT * FROM subject");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean insertStudent(String student_name, float score, String name) {
        try {
            Connection connection = DbUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO student (name, score, subject_id) VALUES (?, ?, " +
                            "(SELECT id FROM subject WHERE name = ?))");
            statement.setString(1, student_name);
            statement.setFloat(2, score);
            statement.setString(3, name);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Object> getStudentById(int id) {
        ArrayList<Object> result = new ArrayList<>();
        try {
            Connection connection = DbUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM student WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result.add(resultSet.getInt("id"));
                result.add(resultSet.getString("name"));
                result.add(resultSet.getFloat("score"));
                result.add(resultSet.getInt("subject_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ResultSet getAllStudents() {
        try {
            Connection connection = DbUtil.getConnection();
            Statement statement = connection.createStatement();
            return statement.executeQuery("SELECT * FROM student");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
