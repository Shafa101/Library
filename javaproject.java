package javaproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
 

public class javaproject {

  

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try( Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?", "root", "")) 
            {
           
               do {
                    System.out.println("\tAssalamualaikum, Welcome to Library Management Server!");
                    System.out.println("\t******************************************************");
                    System.out.println("\tPlease Enter a Number belongs to '1-5' for this Databse");
                    System.out.println("\t________________________________________________________");
                    System.out.println("\t===For Adding new Iteam in The Database Enter '1'===");
                    System.out.println("");
                    System.out.println("\t===For Show The Iteams in The Database Enter '2'===");
                    System.out.println("");
                    System.out.println("\t===For Updating Iteam in The Database Enter '3'===");
                    System.out.println("");
                    System.out.println("\t===For Deleting Any Iteam in The Database Enter '4'===");
                    System.out.println("");
                    System.out.println("    \t\t===Enter '5' To Exit The Database===");
                    System.out.println("        ****************************************************");
                    System.out.print("\tPlease Enter Your Choice Here To Continue: ");
                    choice = sc.nextInt();
                    switch (choice) {
                        case 1:
                            try {
                            System.out.println("Enter Book Tracking Code : ");
                            int Book_Id = sc.nextInt();
                            System.out.println("Enter Book Name : ");
                            String Book_Name = sc.next();
                            System.out.println("Enter Book Catagory : ");
                            String Book_Catagory = sc.next();
                            System.out.println("Enter Author Name : ");
                            String Author = sc.next();
                            String sql = "insert into info values (?,?,?,?);";
                            PreparedStatement stmt = con.prepareStatement(sql);
                            stmt.setInt(1, Book_Id);
                            stmt.setString(2, Book_Name);
                            stmt.setString(3,Book_Catagory);
                            stmt.setString(4,Author);
                            stmt.execute();
                            System.out.println("New Book Details Inserted Successfully!");
                            System.out.println("");
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                        case 2:
                            try {
                            System.out.println("Enter Book Tracking Code to see in the Record : ");
                            Scanner input = new Scanner(System.in);
                            int Book_Id = input.nextInt();
                            String sql = "select * from info where Book_Id=?;";
                            PreparedStatement stmt = con.prepareStatement(sql);
                            stmt.setInt(1, Book_Id);
                            ResultSet rs = stmt.executeQuery();
                            while (rs.next()) {

                                System.out.println("Book Id No = " + rs.getInt(1));
                                System.out.println("Book Name is = " + rs.getString(2));
                                System.out.println("Book Catagory is = " + rs.getString(3));
                                System.out.println("Author Name = " + rs.getString(4));
                            }
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                        case 3:
                            try {
                            System.out.println("Enter Book Tracking Code To Update : ");
                            int Book_Id = sc.nextInt();
                            System.out.println("Enter Book New Catagory : ");
                            String Book_Catagory = sc.next();
                            String sql = "update info set Book_Catagory = ? where Book_Id = ?";
                            PreparedStatement statement = con.prepareStatement(sql);
                            statement.setString(1, Book_Catagory);
                            statement.setInt(2,Book_Id);
                            int rowsUpdated = statement.executeUpdate();
                            if (rowsUpdated > 0) {
                                System.out.println("Record Updated Successfully!");
                            } else {
                                System.out.println("There is No Such Record Found.");
                            }
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                        case 4:
                            try {
                            System.out.println("Enter Book Tracking Code for Deleting the Record : ");
                            int Book_Id = sc.nextInt();
                            String sql = "delete from info where Book_Id = ?;";
                            PreparedStatement statement = con.prepareStatement(sql);
                            statement.setInt(1,Book_Id);
                            int rowsUpdated = statement.executeUpdate();
                            if (rowsUpdated > 0) {
                                System.out.println("Record Deleted Successfully!");
                            } else {
                                System.out.println("There is No Such Record Found.");
                            }
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                        case 5:
                            System.out.println("");
                            break;
                        default:
                            System.out.println("Please Enter The Correct Option!");
                            break;
                    }

                } while (choice != 5);
                System.out.println("Shukriya, For Using The Database!");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            
        }
    }
}