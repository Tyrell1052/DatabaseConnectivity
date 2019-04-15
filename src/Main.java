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

        System.out.println("**************************");

        selectAll(st);




        // Close the connection
        conn.close();



    }//end main() method

    public static void showColumns(Statement s)
            throws SQLException, ClassNotFoundException {

        String queryString;     // a String to hold an SQL query
        ResultSet rs;           // the result set from an SQL query as a table

        // Create an SQL query as as String for this statement
        // this query returns all rows and all columns from the database
        queryString = "Describe pet;";

        // Send a statement executing the query and saving the result set
        rs = s.executeQuery(queryString);

        // print headings for the output

        System.out.println("Columns in the pet table:");

        System.out.printf("%-10s%-10s%n", "Column", "Datatype");
        System.out.println("*********************");

        // Iterate the result set and print name, owner, and species attributes
        while (rs.next()) {
            System.out.printf("%-10s%-10s%n", rs.getString(1), rs.getString(2));
        }

        System.out.println("*********************\n");

    } // end showMetaData


    public static void selectAll(Statement s) throws SQLException, ClassNotFoundException {

        String queryString;     // a String to hold an SQL query
        ResultSet rs;           // the result set from an SQL query as a table

        // Create an SQL query as as String for this statement
        // this query returns all rows and all columns from the database
        queryString = "SELECT * FROM pet;";

        // Send a statement executing the query and saving the result set
        rs = s.executeQuery(queryString);

        // print headings for the output
        System.out.println(queryString);
        System.out.printf("%-20s%-20s%-20s%n", "Pet's Name", "Owner", "Species");
        System.out.println("*******************************************************");

        // Iterate through the result set and print name, owner, and species attributes
        while (rs.next()) {
            System.out.printf("%-20s%-20s%-20s%n", rs.getString(1), rs.getString(2), rs.getString(3));
        }

        System.out.println("*******************************************************");

    } // end selectAll()


}//end Main Class
