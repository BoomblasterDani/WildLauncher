package com.example.wildlauncher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ApplicationDatabase {
    //private static Connection connection;

    public ApplicationDatabase() throws SQLException {

    }

    /*public static Connection establishConnection() throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:derby:applications;create=true");
            conn.setAutoCommit(false);
            System.out.println("Connection to database was successful");
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return conn;
    } */


    public static void createTableApplications() throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection("jdbc:derby:applications;create=true");
        connection.setAutoCommit(false);

        String CREATE_TABLE_INSTRUCTION = "CREATE TABLE APPLICATIONS (" +
                "ID INT NOT NULL GENERATED BY DEFAULT AS IDENTITY, " +
                "NAME VARCHAR(32), " +
                "PATH VARCHAR(255), " +
                "SCRIPTPATH VARCHAR(255)";
                //"RUNNING BOOLEAN";
        try (Statement sqlInstruction = connection.createStatement()) {
            sqlInstruction.executeUpdate(CREATE_TABLE_INSTRUCTION);
        } catch (SQLException e) {
            if (e.getSQLState().equals("XOY32")) {
                System.out.println("Table APPLICATIONS already exists.");

            } else {
                e.printStackTrace();
            }
        }


    }

    public static void addApplication(Application application) throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection("jdbc:derby:applications;create=true");
        connection.setAutoCommit(false);
        String ADDING_SQLINSTRUCTION = "INSERT INTO APPLICATIONS (NAME, PATH, SCRIPTPATH) " +
                "VALUES (?, ?, ?)";
        try (PreparedStatement addingSQLInstructions = connection.prepareStatement(ADDING_SQLINSTRUCTION)) {
            addingSQLInstructions.setString(1, application.name);
            addingSQLInstructions.setString(2, application.path);
            addingSQLInstructions.setString(3, application.scriptPath);
            //addingSQLInstructions.setBoolean(4, application.running);

            addingSQLInstructions.executeUpdate();
        }
    }

    public void deleteApplication() throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection("jdbc:derby:applications;create=true");
        connection.setAutoCommit(false);

        try (Statement sqlInstruction = connection.createStatement()) {
            sqlInstruction.executeUpdate("DELETE * FROM APPLICATIONS");
        }

    }

    public static List<Application> getApplicationsArray() throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection("jdbc:derby:applications;create=true");
        connection.setAutoCommit(false);

        List<Application> applications = new ArrayList<>();
        String SELECT_APPLICATIONS = "SELECT * FROM UNSERE_HELDEN";
        try (PreparedStatement sqlInstruction = connection.prepareStatement(SELECT_APPLICATIONS);
             ResultSet result = sqlInstruction.executeQuery()) {
            while (result.next()) {
                Application application = new Application(
                        result.getString("NAME"),
                        result.getString("PATH"),
                        result.getString("SCRIPTPATH"),
                        false

                );
                applications.add(application);
            }
        }
        return applications;
    }


}


