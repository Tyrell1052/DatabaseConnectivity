/*
* Author: Tyrell Robbins
* Course: CSCI 112
* Program: DatabaseConnectivity
*
* This program is designed to provide a database connection; containing methods that allows the end
* user to write data to a .CVS file - see the databases Metadata - show all rows & columns of the database -
* query database for specific rows and columns.
*
* */

/**************************************Needed imports******************************************************/

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.*;

public class Main {

    public static void main(String[] args)  throws SQLException, ClassNotFoundException{

        System.out.println("Establishing database connection...");

        // Connect to a database by  establishing a Connection object
        Connection conn = DriverManager.getConnection("jdbc:mysql://68.178.217.12/CWHDemo", "CWHDemo", "Cwhdemo%123");

        System.out.println("Database connection established.\n");// This will keep the end user updated on the database connection

        // Create a statement Object for the database connection
        Statement st = conn.createStatement();// this will be passed into all needed methods as a parameter

        writeToCSV(st);

        showMetadata(st);

        selectAll(st);

        queryDatabase(st);


        conn.close();// Close the connection

    }//end main() method

/*******************************************************************************************************************/

/*This method will be responsible for writing data from a database to a .CSV file. */

    public static void writeToCSV(Statement s){

        String filePath = "Courses.csv";// Creating file path for saving information from database

        try {

            FileWriter fw = new FileWriter(filePath,true);// file writer object
            BufferedWriter bw = new BufferedWriter(fw);//implementation of buffered object
            PrintWriter outFile = new PrintWriter(filePath);//print writer object that will allow me to write data form the database

            String queryString; // a String to hold an SQL query
            ResultSet rs; // the result set from an SQL query as a table

            // Create an SQL query as as String for this statement
            // this query returns all rows and all columns from the database
            queryString = "SELECT * FROM fall2014;";


            // Send a statement executing the query and saving the result set
            rs = s.executeQuery(queryString);


            outFile.print("Requested Data from: ");
            // print headings for the output
            outFile.println(queryString);// this is here to display the represented query
            outFile.printf("%-10s%-10s%-10s%-10s%-10s%-20s%-20s\n", "CRN","Subject", "Course","Section","Credits","Time","Days");
            outFile.println("*****************************************************************************");

            // Iterate through the result set and print name, owner, and species attributes
            while (rs.next()) {
                outFile.printf("%-10s%-10s%-10s%-10s%-10s%-20s%-20s\n", rs.getString(1), rs.getString(2), rs.getString(3),

                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
            }

            outFile.println("*****************************************************************************");


            outFile.println();
            outFile.flush();
            outFile.close();

            JOptionPane.showMessageDialog(null,"Saved to CVS");
            /*this will let the end user know after the data from the database is successfully to the .CSV */

        }//end try
        catch (Exception E){// this will attempt to catch any exception raised by this method

            JOptionPane.showMessageDialog(null,"Failed to save to CSV");

        }//end catch


    }//end writeToCSV

/*******************************************************************************************************************/

//This method will display metadata from the database
    public static void showMetadata(Statement s) throws SQLException, ClassNotFoundException {

        String queryString;     // a String to hold an SQL query
        ResultSet rs;           // the result set from an SQL query as a table

        // Create an SQL query as as String for this statement
        // this query returns all rows and all columns from the database
        queryString = "Describe fall2014;";

        // Send a statement executing the query and saving the result set
        rs = s.executeQuery(queryString);

        // print headings for the output

        System.out.println("Metadata of the Course table:");

        System.out.printf("%-10s%-10s%n", "Column", "Data Type");
        System.out.println("*********************");

        // Iterate the result set and print name, owner, and species attributes
        while (rs.next()) {
            System.out.printf("%-10s%-10s%n", rs.getString(1), rs.getString(2));
        }

        System.out.println("*********************\n");

    } // end showMetadata

/*******************************************************************************************************************/

// this method will display all of the data from the fall2014 database
    public static void selectAll(Statement s) throws SQLException, ClassNotFoundException {

        String queryString;     // a String to hold an SQL query
        ResultSet rs;           // the result set from an SQL query as a table

        // Create an SQL query as as String for this statement
        // this query returns all rows and all columns from the database
        queryString = "SELECT * FROM fall2014;";

        // Send a statement executing the query and saving the result set
        rs = s.executeQuery(queryString);

        // print headings for the output
        System.out.println(queryString);
        System.out.printf("%-10s%-10s%-10s%-10s%-10s%-20s%-20s\n", "CRN","Subject", "Course","Section","Credits","Days","Time");
        System.out.println("*****************************************************************************");

        // Iterate through the result set and print name, owner, and species attributes
        while (rs.next()) {
            System.out.printf("%-10s%-10s%-10s%-10s%-10s%-20s%-20s\n", rs.getString(1), rs.getString(2), rs.getString(3),

                    rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
        }

        System.out.println("*****************************************************************************");

    } // end selectAll()

/*******************************************************************************************************************/

// this method is responsible for querying the database for specific information
    public static void queryDatabase(Statement s) throws SQLException, ClassNotFoundException {

        String queryString;     // a String to hold an SQL query
        ResultSet rs;           // the result set from an SQL query as a table

        // Create an SQL query as as String for this statement
        // this query returns all rows and all columns from the database
        queryString = "SELECT crn, subject, course, days FROM fall2014 where credits = '4' ;";

        // Send a statement executing the query and saving the result set
        rs = s.executeQuery(queryString);

        // print headings for the output
        System.out.println(queryString);
        System.out.printf("%-10s%-10s%-10s%-10s\n", "CRN","Subject", "Course","Days");
        System.out.println("*****************************************************************************");

        // Iterate through the result set and print name, owner, and species attributes
        while (rs.next()) {
            System.out.printf("%-10s%-10s%-10s%-10s\n", rs.getString(1), rs.getString(2), rs.getString(3),

                    rs.getString(4));
        }

        System.out.println("*****************************************************************************");

    } // end queryDatabase()

}//end Main Class