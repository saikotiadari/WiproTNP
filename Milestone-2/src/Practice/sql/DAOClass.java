import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOClass {
    private String url = "jdbc:oracle:thin:@localhost:1521:XE";
    private String user = "hr";
    private String password = "hr";

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.OracleDriver");
        return DriverManager.getConnection(url, user, password);
    }

    public void insert(int rollno, String name, String standard, String dob, double fees) {
        String sql = "INSERT INTO student (Rollno, StudentName, Standard, Date_Of_Birth, Fees) VALUES (?, ?, ?, TO_DATE(?, 'DD-Mon-YYYY'), ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, rollno);
            pstmt.setString(2, name);
            pstmt.setString(3, standard);
            pstmt.setString(4, dob);
            pstmt.setDouble(5, fees);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Student record inserted successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error inserting record: " + e.getMessage());
        }
    }
    public void delete(int rollno) {
        String sql = "DELETE FROM student WHERE Rollno = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, rollno);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Student record deleted successfully.");
            } else {
                System.out.println("No student found with Roll No: " + rollno);
            }
        } catch (Exception e) {
            System.out.println("Error deleting record: " + e.getMessage());
        }
    }

   
    public void modify(int rollno, double fee) {
        String sql = "UPDATE student SET Fees = ? WHERE Rollno = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, fee);
            pstmt.setInt(2, rollno);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Student fee updated successfully.");
            } else {
                System.out.println("No student found with Roll No: " + rollno);
            }
        } catch (Exception e) {
            System.out.println("Error updating record: " + e.getMessage());
        }
    }

    public void display(Integer rollno) {
        String sql;
        if (rollno == null) {
            sql = "SELECT Rollno, StudentName, Standard, Date_Of_Birth, Fees FROM student";
        } else {
            sql = "SELECT Rollno, StudentName, Standard, Date_Of_Birth, Fees FROM student WHERE Rollno = ?";
        }

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if (rollno != null) {
                pstmt.setInt(1, rollno);
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("ROLLNO\tNAME\tSTANDARD\tDOB\t\tFEES");
                System.out.println("---------------------------------------------------------");

                while (rs.next()) {
                    System.out.println(
                        rs.getInt("Rollno") + "\t" +
                        rs.getString("StudentName") + "\t" +
                        rs.getString("Standard") + "\t\t" +
                        rs.getDate("Date_Of_Birth") + "\t" +
                        rs.getDouble("Fees")
                    );
                }
            }
        } catch (Exception e) {
            System.out.println("Error fetching record: " + e.getMessage());
        }
    }
}