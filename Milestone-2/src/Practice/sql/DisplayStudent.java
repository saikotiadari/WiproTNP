/*
Display Student details
Write the code to display details of all the students, if no roll no. is passed, while executing the main program.
If while executing the main program, the roll no. is passed, then it should display the record of that particular student.
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DisplayStudent {

    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String user = "hr";
        String password = "hr";

        String query;
        if (args.length == 0) {
            query = "SELECT Rollno, StudentName, Standard, Date_Of_Birth, Fees FROM student";
        } else {
            query = "SELECT Rollno, StudentName, Standard, Date_Of_Birth, Fees FROM student WHERE Rollno = ?";
        }

        try {
            Class.forName("oracle.jdbc.OracleDriver");

            try (Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement pstmt = conn.prepareStatement(query)) {

                if (args.length > 0) {
                    int rollno = Integer.parseInt(args[0]);
                    pstmt.setInt(1, rollno);
                }

                try (ResultSet rs = pstmt.executeQuery()) {
                    System.out.println("ROLLNO\tNAME\tSTANDARD\tDOB\t\tFEES");
                    System.out.println("---------------------------------------------------------");

                    boolean found = false;
                    while (rs.next()) {
                        found = true;
                        System.out.println(
                            rs.getInt("Rollno") + "\t" +
                            rs.getString("StudentName") + "\t" +
                            rs.getString("Standard") + "\t\t" +
                            rs.getDate("Date_Of_Birth") + "\t" +
                            rs.getDouble("Fees")
                        );
                    }

                    if (!found) {
                        System.out.println("No record(s) found.");
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver Error: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid Roll No format. Please enter a valid integer.");
        }
    }
}