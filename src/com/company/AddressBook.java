package com.company;

import java.sql.*;
import java.util.Scanner;
import java.sql.SQLException;

/**
 * This class only has 1 method that takes the user input.
 * User types 1 for adding a new person to the address book
 * User types 2 for editing details
 * User types 3 for deleting details
 * User types 4 for sorting data in the address book
 * User types 5 to exit
 */
public class AddressBook implements AddressBookInterface {

    /**
     * This method makes a connection to the JDBC (MySQL) db. User can type an option and proceed ahead
     * to perform a task
     * @throws SQLException - to take of the prepared statements
     */
    public static void displayOptions() throws SQLException {

        // scanner variables
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select an Option: ");
        System.out.println("Type 1 for Adding a new person to Address book");
        System.out.println("Type 2 for Editing details");
        System.out.println("Type 3 for Deleting details");
        System.out.println("Type 4 for Sorting in Address book");
        System.out.println("Type 5 to exit");

        // user input
        int option = scanner.nextInt();
        scanner.nextLine();

        // variables
        Connection con = null; // a Connection object
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            // Make a connection to the database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useTimezone=true&serverTimezone=GMT",
                    "student", "student");

            System.out.println("Database connection successful!\n");

        } catch (SQLException se) {
            se.printStackTrace();
        }

        // choose an option. Every option has got an interface method attached to it
        switch (option) {
            case 1:
                AddressBookInterface.addDetails(con);
                displayOptions();
                break;

            case 2:
                AddressBookInterface.updateProfile(con);
                break;

            case 3:
                System.out.println("\nEnter your Phone number to delete your details: ");
                AddressBookInterface.deleteProfile(con);
                displayOptions();
                break;

            case 4:
                System.out.print("Sorted Data by First Name (Ascending order)\n");
                AddressBookInterface.sortData(con, myStmt, myRs);
                break;

            case 5:
                System.out.println("Bye! See you soon:)");
                break;

            default:
                System.out.println("Invalid input! Try again");
                displayOptions();
        }
    }

    // main method only calls the displayOptions method
    public static void main(String[] args) throws SQLException {

        // call the method
        displayOptions();
    }
}
