package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Admin {
    public static void addDepartment(Connection con, Statement myStmt, ResultSet myRs) throws SQLException {
        Scanner scn = new Scanner(System.in);
        String name = null, code = null;

        System.out.println("Database connection successful!\n");

        System.out.print("Enter Department code: ");
        code = scn.nextLine();

        System.out.print("Enter Department name: ");
        name = scn.nextLine();

        System.out.println("Inserting a new Department \n");
        myStmt = con.createStatement();

        String sql = "INSERT INTO Department " +
                "VALUES (?,?)";

        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, code);
        preparedStatement.setString(2, name);
        preparedStatement.executeUpdate();

        System.out.println("Department Added Successfully\n");
    }

    public static void deleteDepartment(Connection con, Statement myStmt, ResultSet myRs) throws SQLException {
        Scanner scn = new Scanner(System.in);
        String code = "";

        System.out.println("Database connection successful!\n");

        // Create a statement
        myStmt = con.createStatement();

        System.out.print("Enter Department code: ");
        code = scn.nextLine();

        String sql = ("DELETE FROM Department WHERE code = ?");

        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, code);
        preparedStatement.executeUpdate();

        System.out.println("Department deleted successfully!");
    }

    public static void addDegreeCourse(Connection con, Statement myStmt, ResultSet myRs) throws SQLException {
        Scanner scn = new Scanner(System.in);
        String degreeCode = null, leadDeptCode = null, partnerDeptCode = null, name = null;
        int level;
        boolean yearInIndustry = false;


        System.out.println("Database connection successful!\n");

        System.out.print("Enter Degree code: ");
        degreeCode = scn.nextLine();

        System.out.print("Enter Degree name: ");
        name = scn.nextLine();

        System.out.print("Enter Degree Level: ");
        level = Integer.parseInt(scn.nextLine());

        System.out.print("Enter Lead Department Code: ");
        leadDeptCode = scn.nextLine();

        System.out.print("Enter Partner Department code: ");
        partnerDeptCode = scn.nextLine();

        System.out.print("Enter True/False for Year in Industry: ");
        boolean bn = scn.nextBoolean();


        System.out.println("Inserting a new Department \n");
        myStmt = con.createStatement();

        String sql = "INSERT INTO DegreeCourse " +
                "VALUES (?,?,?,?,?,?)";

        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, degreeCode);
        preparedStatement.setString(2, name);
        preparedStatement.setBoolean(3, yearInIndustry);
        preparedStatement.setInt(4, level);
        preparedStatement.setString(5, leadDeptCode);
        preparedStatement.setString(6, partnerDeptCode);

        preparedStatement.executeUpdate();


        System.out.println(" Degree Course Added successfully!\n");
    }


    public static void deleteDegreeCourse(Connection con, Statement myStmt, ResultSet myRs) throws SQLException {
        Scanner scn = new Scanner(System.in);
        String degreeCode = null;

        System.out.println("Database connection successful!\n");

        System.out.print("Enter Degree code: ");
        degreeCode = scn.nextLine();

        String sql = ("DELETE FROM DegreeCourse WHERE degreeCode = ?");

        myStmt = con.createStatement();

        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, degreeCode);
        preparedStatement.executeUpdate();

        System.out.println(" Degree Course Deleted\n");
    }

    public static void addModule(Connection con, Statement myStmt, ResultSet myRs) throws SQLException {
        Scanner scn = new Scanner(System.in);
        String moduleCode = null, name = null, seasonTaught = null, degreeCode = null;
        String periodCode = null;
        int credits;
        boolean isCore = false;

        System.out.println("Database connection successful!\n");

        System.out.print("Enter Module code: ");
        moduleCode = scn.nextLine();

        System.out.print("Enter Module name: ");
        name = scn.nextLine();

        System.out.print("Enter the season in which it is taught: ");
        seasonTaught = scn.nextLine();

        System.out.print("Enter Credits of the Module: ");
        credits = Integer.parseInt(scn.nextLine());

        System.out.print("Enter Period Code: ");
        periodCode = scn.nextLine();

        System.out.print("Enter Degree Code: ");
        degreeCode = scn.nextLine();

        System.out.print("Enter true/false for core modules: ");
        boolean bn = scn.nextBoolean();


        System.out.println("Inserting a new Module \n");
        myStmt = con.createStatement();

        String sql = "INSERT INTO Module " +
                "VALUES (?,?,?,?)";

        String sql2 = "INSERT INTO  DegreeCourseModuleLinkTable " +
                "VALUES (?,?,?,?)";


        PreparedStatement preparedStatement = con.prepareStatement(sql);
        PreparedStatement preparedStatement2 = con.prepareStatement(sql2);

        preparedStatement.setString(1, moduleCode);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, seasonTaught);
        preparedStatement.setInt(4, credits);
        preparedStatement.executeUpdate();

        preparedStatement2.setString(1, degreeCode);
        preparedStatement2.setString(2, moduleCode);
        preparedStatement2.setBoolean(3, bn);
        preparedStatement2.setString(4, periodCode);
        preparedStatement2.executeUpdate();


        System.out.println("Module Added successfully!\n");
    }

    public static void deleteModule(Connection con, Statement myStmt, ResultSet myRs) throws SQLException {
        Scanner scn = new Scanner(System.in);
        String moduleCode = "";

        System.out.println("Database connection successful!\n");

        // Create a statement
        myStmt = con.createStatement();

        System.out.print("Enter the Module Code to be deleted: ");
        moduleCode = scn.nextLine();

        String sql = ("DELETE FROM Module WHERE moduleCode = ?");
        String sql2 = ("DELETE FROM DegreeCourseModuleLinkTable WHERE moduleCode = ?");

        PreparedStatement preparedStatement2 = con.prepareStatement(sql2);
        preparedStatement2.setString(1, moduleCode);
        preparedStatement2.executeUpdate();


        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, moduleCode);
        preparedStatement.executeUpdate();

        System.out.println("Module Deleted Successfully.");
    }

    private static void close(Connection con, Statement myStmt,
                              ResultSet myRs) throws SQLException {
        if (myRs != null) {
            myRs.close();
        }

        if (myStmt != null) {
            myStmt.close();
        }

        if (con != null) {
            con.close();
        }
    }

    public static void main(String[] args) throws SQLException {

        Connection con = null; // a Connection object
        Statement myStmt = null;
        ResultSet myRs = null;
        Scanner scn = new Scanner(System.in);
        String option = "";

        try {
            // 1. Get a connection to database
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team017?user=team017&password=ae2d22b6");

            System.out.println("Database connection successful!\n");

            System.out.print("Add/Delete: ");
            option = scn.nextLine();

            if (option.equals("Delete")) {
                deleteDegreeCourse(con, myStmt, myRs);
            } else {
                addDegreeCourse(con, myStmt, myRs);
            }
			/*	if (option.equals("Delete Department")) {
				deleteDepartment(con, myStmt, myRs);
			} else {
				addDepartment(con, myStmt, myRs);
			}
			if (option.equals("Delete Degree Course")) {
				deleteDegreeCourse(con, myStmt, myRs);
			} else {
				addDegreeCourse(con, myStmt, myRs);
			}*/

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            close(con, myStmt, myRs);
        }
    }
}