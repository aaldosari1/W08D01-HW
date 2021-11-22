package com.company;

import java.sql.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("Welcome to Tuwaiq books database");
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ebookstore?useSSL=false&allowPublicKeyRetrieval=true",
                    "root",
                    "Secrets54321&"
            );
            ResultSet results;
            int rowsAffected;

            Statement statement = connection.createStatement();
            Scanner scanner = new Scanner(System.in);
            boolean flag = true;
            int userInput;
            int id = 0;
            String title = "";
            String auther = "";
            int qty = 0;

            while (flag) {
                System.out.println("press '1' to enter a book \npress '2' to update a book info \npress '3' to delete a book \npress '4' to search for books \nor 0 to exit");
                userInput = scanner.nextInt();
                if (userInput == 0)
                    flag = false;
                if (userInput == 1) {
                    System.out.println("Enter the book ID No.");
                    id = scanner.nextInt();
                    scanner.nextLine(); //source https://stackoverflow.com/questions/13102045/scanner-is-skipping-nextline-after-using-next-or-nextfoo
                    System.out.println("Enter the book title");
                    title = scanner.nextLine();
                    System.out.println("Enter the book auther's name");
                    auther = scanner.nextLine();
                    System.out.println("Enter the books quantity");
                    qty = scanner.nextInt();
                    statement.executeUpdate("insert into books values (" + id + ", '" + title + "','" + auther + "'," + qty + ")");
                }

                if (userInput == 2) {
                    System.out.println("Enter the book ID that you want to update");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter the new book title");
                    title = scanner.nextLine();
                    System.out.println("Enter the book auther's name");
                    auther = scanner.nextLine();
                    System.out.println("Enter the books quantity");
                    qty = scanner.nextInt();
                    statement.executeUpdate("update books set title='" + title + "', auther= '" + auther + "',qty=" + qty + " where id =" + id);
                }
                if (userInput == 3) {
                    System.out.println("Enter the book ID that you want to delete");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    statement.executeUpdate("delete from books where id =" + id);
                }
                if (userInput == 4) {
                    System.out.println("Enter the book title to search for");
                    scanner.nextLine();
                    title = scanner.nextLine();
                    results = statement.executeQuery("select * from books where title = '" + title + "'");
                    while (results.next()) {
                        System.out.println("Id: " + results.getString("id") + ", Title: " + results.getString("title") +
                                ", Auther: " + results.getString("auther") + ", quantity: " + results.getString("qty"));
                    }
                    results.close();
                }
            }
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
