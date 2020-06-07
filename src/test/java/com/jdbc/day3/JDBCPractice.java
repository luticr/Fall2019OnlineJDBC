package com.jdbc.day3;

import org.junit.Test;

import java.sql.*;

public class JDBCPractice {

    final String DB_URL = "jdbc:oracle:thin:@54.152.21.73:1521:xe";
    final String DB_USER = "hr";
    final String DB_PASSWORD = "hr";

    @Test
    public void connectToDB() throws Exception {

        /**
         * To connect with a database we call DriverManager
         * Provide DB connection URL. username and password
         */

        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD );
        /**
         * statement is used to execute a query
         */
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        /**
         * A table of data representing a database result set,
         * which is usually generated by executing a statement that queries the database
         * It pointer will be before first row, we need to call next() method
         * To switch pointer and move it to the first row
         */
        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");

        resultSet.next(); // returns boolean, and moves pointer to the next row in result set if it present

        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        System.out.println(firstName);

        statement.executeUpdate("UPDATE employees SET first_name = 'Steven', last_Name = 'King' WHERE employee_id = 100");

        System.out.println(firstName);
        System.out.println(lastName);

        resultSet.close();
        statement.close();
        connection.close();
    }
}
