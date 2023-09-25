package com.cozentus.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/assessment_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root@123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

}
