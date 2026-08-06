/*
Create a stored procedure that calculates net salary of all the employees whose records are stored in table "emp".
The criteria for calculating net salary is as follows :
Gross salary = sal + comm.
Net Salary = gross salary - IT
If the employee's commission is null then IT is calculated as
IT = 10% of gross salary
else if the employees commission is less than 500, then IT is calculated as
IT = 15% of gross salary
else
IT = 20% of gross salary.
Develop a jdbc program that invokes this stored procedure by passing the empno. and in return gets the net salary of each employee. Display on screen the empno., ename and net salary of all the employees.
*/

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class CalculateNetSalary {

    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String user = "hr";
        String password = "hr";

        String fetchEmpQuery = "SELECT id, FIRST_NAME FROM emp";
        String procedureCall = "{call calculate_net_salary(?, ?)}";

        try {
            Class.forName("oracle.jdbc.OracleDriver");

            try (Connection conn = DriverManager.getConnection(url, user, password);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(fetchEmpQuery);
                 CallableStatement cstmt = conn.prepareCall(procedureCall)) {

                System.out.println("EMPNO\tENAME\t\tNET SALARY");
                System.out.println("----------------------------------------");

                while (rs.next()) {
                    int empno = rs.getInt("id");
                    String ename = rs.getString("first_name");

                    cstmt.setInt(1, empno);
                    cstmt.registerOutParameter(2, Types.DOUBLE);
                    cstmt.execute();

                    double netSalary = cstmt.getDouble(2);

                    System.out.println(empno + "\t" + ename + "\t\t" + netSalary);
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver Error: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }
}