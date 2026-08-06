/*
Assignment 2: Inserting a record
ABC International School wants to computerize students details.
• Rollno: 4-digit number
• StudentName: Valid value can contain maximum 20 letters in uppercase
• Standard: Roman Letters representing I to X (I, II, III, IV... IX, X)
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertStudent {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Roll No (4-digit number): ");
        int rollno = scanner.nextInt();
        if (rollno < 1000 || rollno > 9999) {
            System.out.println("Error: Rollno must be a 4-digit number.");
            return;
        }

        scanner.nextLine(); 
        System.out.print("Enter Student Name (Max 20 uppercase letters): ");
        String name = scanner.nextLine().trim();
        if (name.length() > 20 || !name.matches("[a-zA-Z\\s]+")) {
            System.out.println("Error: Name must contain only letters (max 20).");
            return;
        }
        name = name.toUpperCase();

        System.out.print("Enter Standard (Roman numeral I to X): ");
        String standard = scanner.nextLine().trim().toUpperCase();
        if (!standard.matches("^(I|II|III|IV|V|VI|VII|VIII|IX|X)$")) {
            System.out.println("Error: Standard must be a Roman numeral between I and X.");
            return;
        }

        System.out.print("Enter Date of Birth (DD-Mon-YYYY e.g., 15-AUG-2005): ");
        String dob = scanner.nextLine().trim();

        System.out.print("Enter Fees: ");
        double fees = scanner.nextDouble();

        // Database operation
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String user = "hr";
        String password = "hr";

        String query = "INSERT INTO student (Rollno, StudentName, Standard, Date_Of_Birth, Fees) VALUES (?, ?, ?, TO_DATE(?, 'DD-Mon-YYYY'), ?)";

        try {
            Class.forName("oracle.jdbc.OracleDriver");

            try (Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement pstmt = conn.prepareStatement(query)) {

                pstmt.setInt(1, rollno);
                pstmt.setString(2, name);
                pstmt.setString(3, standard);
                pstmt.setString(4, dob);
                pstmt.setDouble(5, fees);

                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Student record successfully inserted!");
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver Error: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}