import java.sql.*;
import java.util.ArrayList;

public class BasicJDBCOperations {

    public static Connection getConnection() throws Exception {
        Class.forName("oracle.jdbc.OracleDriver");
        return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
    }
    public static String getUserType(String userID) {
        String sql = "SELECT UserType FROM UserTable WHERE UserID = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, userID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("UserType");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String getIncorrectAttempts(String userID) {
        String sql = "SELECT IncorrectAttempts FROM UserTable WHERE UserID = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, userID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int attempts = rs.getInt("IncorrectAttempts");
                if (attempts == 0) return "No Incorrect Attempt";
                if (attempts == 1) return "One Time";
                return "Incorrect Attempt Exceeded";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "User Not Found";
    }
    public static String changeUserType(String userID) {
        String sql = "UPDATE UserTable SET UserType = 'Admin' WHERE UserID = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, userID);
            int rowsUpdated = ps.executeUpdate();
            return (rowsUpdated >= 1) ? "Update Success" : "Update Failed";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Update Failed";
    }
    public static int getLockStatus() {
        String sql = "SELECT COUNT(*) FROM UserTable WHERE LockStatus = 0";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static String changeName(String id, String name) {
        String sql = "UPDATE UserTable SET Name = ? WHERE UserID = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, id);
            int rows = ps.executeUpdate();
            return (rows > 0) ? "Success" : "Failed";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Failed";
    }
    public static String changePassword(String password) {
        String sql = "UPDATE UserTable SET Password = ? WHERE UserType = 'Admin'";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, password);
            int rows = ps.executeUpdate();
            return (rows > 0) ? "Changed" : "0";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }
    public static String addUser_1(UserBean bean) {
        String sql = "INSERT INTO UserTable VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, bean.getId());
            ps.setString(2, bean.getPassword());
            ps.setString(3, bean.getName());
            ps.setInt(4, bean.getIncorrectAttempts());
            ps.setInt(5, bean.getLockStatus());
            ps.setString(6, bean.getUserType());
            int rows = ps.executeUpdate();
            return (rows > 0) ? "Success" : "Fail";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Fail";
    }
    public static String addUser_2(UserBean bean) {
        if (bean.getLockStatus() != 0) {
            return "Fail";
        }
        return addUser_1(bean);
    }
    public static ArrayList<UserBean> getUsers(String userType) {
        ArrayList<UserBean> list = new ArrayList<>();
        String sql = "SELECT * FROM UserTable WHERE UserType = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, userType);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UserBean user = new UserBean(
                    rs.getString("UserID"),
                    rs.getString("Password"),
                    rs.getString("Name"),
                    rs.getInt("IncorrectAttempts"),
                    rs.getInt("LockStatus"),
                    rs.getString("UserType")
                );
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public static ArrayList<UserBean> storeAllRecords() {
        ArrayList<UserBean> list = new ArrayList<>();
        String sql = "SELECT * FROM UserTable";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                UserBean user = new UserBean(
                    rs.getString("UserID"),
                    rs.getString("Password"),
                    rs.getString("Name"),
                    rs.getInt("IncorrectAttempts"),
                    rs.getInt("LockStatus"),
                    rs.getString("UserType")
                );
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public static String[] getNames() {
        ArrayList<String> namesList = new ArrayList<>();
        String sql = "SELECT Name FROM UserTable";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                namesList.add(rs.getString("Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return namesList.toArray(new String[0]);
    }

    public static void main(String[] args) {
        System.out.println("Scenario 1: " + getUserType("AB1001"));
        System.out.println("Scenario 2: " + getIncorrectAttempts("AB1001"));
        System.out.println("Scenario 3: " + changeUserType("TA1002"));
        System.out.println("Scenario 4 Lock Status Count: " + getLockStatus());
        System.out.println("Scenario 5: " + changeName("RS1003", "Ganesh Kumar"));
        System.out.println("Scenario 6: " + changePassword("AdminPass123"));

        UserBean newUser = new UserBean("XY1004", "XY1004", "Suresh", 0, 0, "Employee");
        System.out.println("Scenario 7: " + addUser_1(newUser));

        UserBean newUser2 = new UserBean("XY1005", "XY1005", "Ramesh", 0, 0, "Employee");
        System.out.println("Scenario 8: " + addUser_2(newUser2));

        System.out.println("Scenario 9 Count: " + getUsers("Admin").size());
        System.out.println("Scenario 10 Count: " + storeAllRecords().size());

        String[] names = getNames();
        System.out.print("Scenario 11 Names: ");
        for (String name : names) {
            System.out.print(name + " ");
        }
    }
}