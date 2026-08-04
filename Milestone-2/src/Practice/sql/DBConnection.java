import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static void main(String[] args) {
        Connection conn = null;

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String username = "hr";
            String password = "hr";

            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established successfully");

        } catch (ClassNotFoundException e) {
            System.out.println("Connection could not be established ");
            System.out.println("Driver Class Not Found: " + e.getMessage());

        } catch (SQLException e) {
            System.out.println("Connection could not be established ");
            System.out.println("SQLException: " + e.getMessage());

        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}