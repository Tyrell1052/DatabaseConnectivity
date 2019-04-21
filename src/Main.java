/*
* Author: Tyrell Robbins
* Course: CSCI 112
* Program: DatabaseConnectivity
*
* This program is designed to
*
* */

import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.*;

public class Main {

    public static void main(String[] args)  throws SQLException, ClassNotFoundException{

        System.out.println("Establishing database connection...");

        // Connect to a database by  establishing a Connection object
        Connection conn = DriverManager.getConnection("jdbc:mysql://68.178.217.12/CWHDemo", "CWHDemo", "Cwhdemo%123");

        System.out.println("Database connection established.\n");

        // Create a statement Object for this  database connection
        Statement st = conn.createStatement();


        showColumns(st);

        //selectAll(st);

        writeToCSV(st);

        //selectCIS(st);


        // Close the connection
        conn.close();
    }//end main() method

/*******************************************************************************************************************/

/*
* This method will be responsible for writing data from a database to a .CSV file by connecting */
    public static void writeToCSV(Statement s){
        String filePath = "Courses.csv";

        try {

            FileWriter fw = new FileWriter(filePath,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter outFile = new PrintWriter(filePath);

            String queryString;     // a String to hold an SQL query
            ResultSet rs;           // the result set from an SQL query as a table

            // Create an SQL query as as String for this statement
            // this query returns all rows and all columns from the database
            queryString = "SELECT * FROM fall2014;";


            // Send a statement executing the query and saving the result set
            rs = s.executeQuery(queryString);


            outFile.print("Requested Data from: ");
            // print headings for the output
            outFile.println(queryString);
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

        }//end try
        catch (Exception E){

            JOptionPane.showMessageDialog(null,"Failed to save to CSV");

        }//end catch


    }//end writeToCSV

/*******************************************************************************************************************/

    public static void showColumns(Statement s) throws SQLException, ClassNotFoundException {

        String queryString;     // a String to hold an SQL query
        ResultSet rs;           // the result set from an SQL query as a table

        // Create an SQL query as as String for this statement
        // this query returns all rows and all columns from the database
        queryString = "Describe fall2014;";

        // Send a statement executing the query and saving the result set
        rs = s.executeQuery(queryString);

        // print headings for the output

        System.out.println("Columns in the Course table:");

        System.out.printf("%-10s%-10s%n", "Column", "Data Type");
        System.out.println("*********************");

        // Iterate the result set and print name, owner, and species attributes
        while (rs.next()) {
            System.out.printf("%-10s%-10s%n", rs.getString(1), rs.getString(2));
        }

        System.out.println("*********************\n");

    } // end showMetaData

/*******************************************************************************************************************/

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

    public static void selectCIS(Statement s) throws SQLException, ClassNotFoundException {

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

    } // end selectCIS()

}//end Main Class