import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DisplayEmpDetails {

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
            String sql = "SELECT EMPLOYEE_ID, FIRST_NAME FROM EMPLOYEES";
            rs = stmt.executeQuery(sql);
            System.out.printf("%-10s %-15s%n", "EMPNO", "ENAME");
            System.out.println("-------------------------");

            while (rs.next()) {
                int empno = rs.getInt(1);
                String ename = rs.getString("FIRST_NAME");

                System.out.printf("%-10d %-15s%n", empno, ename);
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