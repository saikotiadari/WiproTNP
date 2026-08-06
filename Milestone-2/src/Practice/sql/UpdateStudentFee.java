/*
Modification of Student record 
When there is a change in the fee to be paid by a student, the respective row should be appropriately updated. Pass the rollno from the command prompt along with the new fee amount and this amount should be reflected in the table for that particular student.
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateStudentFee {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java UpdateStudentFee <rollno> <new_fee>");
            return;
        }

        int rollno = Integer.parseInt(args[0]);
        double newFee = Double.parseDouble(args[1]);

        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String user = "hr";
        String password = "hr";

        String updateQuery = "UPDATE student SET Fees = ? WHERE Rollno = ?";

        try {
            Class.forName("oracle.jdbc.OracleDriver");

            try (Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {

                pstmt.setDouble(1, newFee);
                pstmt.setInt(2, rollno);

                int rowsUpdated = pstmt.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Student fee updated successfully.");
                } else {
                    System.out.println("No student found with Roll No: " + rollno);
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver Error: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }
}