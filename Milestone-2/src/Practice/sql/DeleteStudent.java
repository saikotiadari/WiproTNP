/*
Assignment 3: Deleting a Student’s record
When a student leaves the school, the record related to that student needs to be deleted from the Student table. 
The student’s roll no, whose record has to be deleted, should be passed as a command line argument.
Upon deletion, the Student details must be stored in another table named StudentLog which will maintain 
the details such as Rollno, StudentName, Standard and Leaving_date.
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteStudent {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please pass the Roll No as a command line argument.");
            System.out.println("Usage: java DeleteStudent <rollno>");
            return;
        }

        int rollno = Integer.parseInt(args[0]);

        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String user = "hr";
        String password = "hr";

       
        String selectQuery = "SELECT Rollno, StudentName, Standard FROM student WHERE Rollno = ?";
        String insertLogQuery = "INSERT INTO StudentLog (Rollno, StudentName, Standard, Leaving_date) VALUES (?, ?, ?, SYSDATE)";
        String deleteQuery = "DELETE FROM student WHERE Rollno = ?";

        Connection conn = null;
        PreparedStatement pstmtSelect = null;
        PreparedStatement pstmtInsertLog = null;
        PreparedStatement pstmtDelete = null;
        ResultSet rs = null;

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection(url, user, password);

           
            conn.setAutoCommit(false);

           
            pstmtSelect = conn.prepareStatement(selectQuery);
            pstmtSelect.setInt(1, rollno);
            rs = pstmtSelect.executeQuery();

            if (rs.next()) {
                String name = rs.getString("StudentName");
                String standard = rs.getString("Standard");

                
                pstmtInsertLog = conn.prepareStatement(insertLogQuery);
                pstmtInsertLog.setInt(1, rollno);
                pstmtInsertLog.setString(2, name);
                pstmtInsertLog.setString(3, standard);
                pstmtInsertLog.executeUpdate();

                
                pstmtDelete = conn.prepareStatement(deleteQuery);
                pstmtDelete.setInt(1, rollno);
                int rowsDeleted = pstmtDelete.executeUpdate();

                
                conn.commit();

                if (rowsDeleted > 0) {
                    System.out.println("Student record deleted successfully and logged in StudentLog table.");
                }
            } else {
                System.out.println("No student found with Roll No: " + rollno);
            }

        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback(); 
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.out.println("Error executing operation: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmtSelect != null) pstmtSelect.close();
                if (pstmtInsertLog != null) pstmtInsertLog.close();
                if (pstmtDelete != null) pstmtDelete.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}