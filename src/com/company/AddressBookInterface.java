package com.company;

import java.sql.*;
import java.util.Scanner;

/**
 * This interface class has got multiple methods which are used in AddressBook class
 * Methods are: adding details, editing details, deleting details, sorting details
 */
public interface AddressBookInterface {

    Scanner scn = new Scanner(System.in);

    /**
     * This method adds the details to the database
     * @param con - connection to database
     * @throws SQLException - handles exceptions because of the usage of prepared statements
     */
    static void addDetails(Connection con) throws SQLException {

        // variables
        String first_name;
        String last_name;
        String address;
        String city;
        String state;
        String zip;
        String phoneNum;

        // take user input
        System.out.print("Enter First Name: ");
        first_name = scn.nextLine();

        System.out.print("Enter Last Name: ");
        last_name = scn.nextLine();

        System.out.print("Enter Address: ");
        address = scn.nextLine();

        System.out.print("Enter City: ");
        city = scn.nextLine();

        System.out.print("Enter State: ");
        state = scn.nextLine();

        System.out.print("Enter Zip-Code: ");
        zip = scn.nextLine();

        System.out.print("Enter Phone number: ");
        phoneNum = scn.nextLine();

        // sql statement to insert the details
        String sql = "INSERT INTO addressBook " +
                "VALUES (?,?,?,?,?,?,?)";

        // using prepared statements to handle sql injection attcaks
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, first_name);
        preparedStatement.setString(2, last_name);
        preparedStatement.setString(3, address);
        preparedStatement.setString(4, city);
        preparedStatement.setString(5, state);
        preparedStatement.setString(6, zip);
        preparedStatement.setString(7, phoneNum);

        preparedStatement.executeUpdate();

        System.out.println("Added successfully!\n");

    }


    /**
     * This method edits the details of a user in the database
     * @param con - connection to database
     * @throws SQLException - handles exceptions because of the usage of prepared statements
     */
    static void updateProfile(Connection con) throws SQLException{

        // using phone number to validate the records as they are the unique ones
        System.out.print("Enter your phone number: ");
        String phoneNum = scn.nextLine();

        System.out.print("Type 1 to update First name\n");
        System.out.print("Type 2 to update Last name\n");
        System.out.print("Type 3 to update Address\n");
        System.out.print("Type 4 to update City\n");
        System.out.print("Type 5 to update State\n");
        System.out.print("Type 6 to update Zip\n");
        System.out.print("Type 7 to update Phone Number\n");

        // variables
        int option = scn.nextInt();
        scn.nextLine();
        String sql;
        PreparedStatement preparedStatement;

        // case statements to edit each part like edit first name, last name etc. by using prepared statements
        switch (option){
            case 1:
                System.out.println("Enter your new First name: ");
                String first_name = scn.nextLine();

                // sql query to update
                sql = ("UPDATE addressBook SET first_name = ? WHERE phoneNum = ?");
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, first_name);
                preparedStatement.setString(2, phoneNum);
                preparedStatement.executeUpdate();
                System.out.println("Updated");
                break;
            case 2:
                System.out.println("Enter your new Last name: ");
                String last_name = scn.nextLine();

                // sql query to update
                sql = ("UPDATE addressBook SET last_name = ? WHERE phoneNum = ?");
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, last_name);
                preparedStatement.setString(2, phoneNum);
                preparedStatement.executeUpdate();
                System.out.println("Updated");
                break;
            case 3:
                System.out.println("Enter your new Address: ");
                String address = scn.nextLine();

                // sql query to update
                sql = ("UPDATE addressBook SET address = ? WHERE phoneNum = ?");
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, address);
                preparedStatement.setString(2, phoneNum);
                preparedStatement.executeUpdate();
                System.out.println("Updated");
                break;
            case 4:
                System.out.println("Enter your new City: ");
                String city = scn.nextLine();

                // sql query to update
                sql = ("UPDATE addressBook SET city = ? WHERE phoneNum = ?");
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, city);
                preparedStatement.setString(2, phoneNum);
                preparedStatement.executeUpdate();
                System.out.println("Updated");
                break;
            case 5:
                System.out.println("Enter the state that you wish to update: ");
                String state = scn.nextLine();

                // sql query to update
                sql = ("UPDATE addressBook SET state = ? WHERE phoneNum = ?");
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, state);
                preparedStatement.setString(2, phoneNum);
                preparedStatement.executeUpdate();
                System.out.println("Updated");
                break;
            case 6:
                System.out.println("Enter the city that you wish to update: ");
                String zip = scn.nextLine();

                // sql query to update
                sql = ("UPDATE addressBook SET zip = ? WHERE phoneNum = ?");
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, zip);
                preparedStatement.setString(2, phoneNum);
                preparedStatement.executeUpdate();
                System.out.println("Updated");
                break;
            case 7:
                System.out.println("Enter the phoneNum that you wish to update: ");
                phoneNum = scn.nextLine();

                // sql query to update
                sql = ("UPDATE addressBook SET phoneNum = ? WHERE phoneNum = ?");
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, phoneNum);
                preparedStatement.setString(2, phoneNum);
                preparedStatement.executeUpdate();
                System.out.println("Updated");
                break;
            default:
                System.out.println("None");
                break;
        }
    }

    /**
     * This method deletes the details in the database
     * @param con - connection to database
     * @throws SQLException - handles exceptions because of the usage of prepared statements
     */
    static void deleteProfile(Connection con) throws SQLException {

        // variables
        String phoneNum;

        // taking user input
        System.out.print("Enter Phone Number: ");
        phoneNum = scn.nextLine();

        // sql query to delete
        String sql = ("DELETE FROM addressBook WHERE phoneNum = ?");

        // using prepared statements
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, phoneNum);
        preparedStatement.executeUpdate();

        System.out.println("Address Deleted\n");
    }

    /**
     * This method sorts the details in an ascending order
     * @param con - connection to database
     * @param myStmt - statement
     * @param myRs - result of the statement
     * @throws SQLException - handles exceptions because of the usage of prepared statements
     */
    static void sortData(Connection con, Statement myStmt, ResultSet myRs) throws SQLException{

        try {
            // Create a statement
            myStmt = con.createStatement();

            // Execute SQL query
            myRs = myStmt.executeQuery("SELECT * FROM addressBook ORDER BY first_name ASC");

            // Process the result set
            while (myRs.next()) {
                System.out.println(myRs.getString("first_name") + ", "
                        + myRs.getString("last_name") + ", "
                        + myRs.getString("address") + ", "
                        + myRs.getString("city") + ", "
                        + myRs.getString("state") + ", "
                        + myRs.getString("zip") + ", "
                        + myRs.getString("phoneNum"));
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
