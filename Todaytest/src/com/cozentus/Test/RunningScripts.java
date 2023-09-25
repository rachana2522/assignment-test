package com.cozentus.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;

public class RunningScripts {
	public static void main(String[] args) {
        try {
            Connection connection = DbUtil.getConnection();
            Statement statement = connection.createStatement();

            BufferedReader reader = new BufferedReader(new FileReader("cozentusdb.sql"));
            String line;
            StringBuilder query = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                query.append(line);
                if (line.endsWith(";")) {
                    statement.execute(query.toString());
                    query.setLength(0);
                }
            }

            System.out.println("Tables created successfully.");
            
          
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
