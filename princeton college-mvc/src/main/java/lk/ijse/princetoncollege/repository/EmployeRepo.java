package lk.ijse.princetoncollege.repository;

import lk.ijse.princetoncollege.db.DbConnection;
import lk.ijse.princetoncollege.model.Employee;
import lk.ijse.princetoncollege.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeRepo {
    public static List<Employee> getAll()  {
        List<Employee> employeeList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Establish database connection (replace the connection details with your own)
            connection = DbConnection.getInstance().getConnection();
            // SQL query to select all students
            String sql = "SELECT * FROM employee";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            // Iterate through the result set and create Student objects
            while (resultSet.next()) {
                Employee employee = new Employee(
                        resultSet.getString("employee_id"),
                        resultSet.getString("name"),
                        resultSet.getString("contact_no"),
                        resultSet.getString("address"),
                        resultSet.getString("nic_no")
                );
                employeeList.add(employee);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);


        }
        return employeeList;

    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM employee WHERE employee_id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Employee employee) throws SQLException {
        String sql = "UPDATE employee SET name = ?,contact_no = ?,address = ?,nic_no = ?  WHERE employee_id = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        pstm.setObject(1,employee.getEmployeName());
        pstm.setObject(2,employee.getContactNumber());
        pstm.setObject(3,employee.getAddress());
        pstm.setObject(4,employee.getNicNumber());
        pstm.setObject(5,employee.getEmployeID());

        return pstm.executeUpdate() > 0;
    }
}
