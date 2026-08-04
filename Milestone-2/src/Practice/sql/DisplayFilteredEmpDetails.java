import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DisplayFilteredEmpDetails {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String username = "hr";
            String password = "hr";

            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();

            String sql = "SELECT FIRST_NAME,JOB_ID,SALARY,COMM FROM EMPLOYEES WHERE SALARY > 100 AND SALARY < 20000";
            rs = stmt.executeQuery(sql);

            System.out.printf("%-15s %-15s %-10s %-10s%n", "ENAME", "JOB", "SALARY", "COMM");
            System.out.println("----------------------------------------------------------------");

            while (rs.next()) {
                String ename = rs.getString("FIRST_NAME");
                String job = rs.getString("JOB_ID");
                double sal = rs.getDouble("SALARY");
                double comm = rs.getDouble("COMM");

                System.out.printf("%-15s %-15s %-10.2f %10.2f %n", ename, job, sal, comm);
            }

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}