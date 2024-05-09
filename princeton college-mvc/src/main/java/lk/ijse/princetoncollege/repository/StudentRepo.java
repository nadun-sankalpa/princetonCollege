package lk.ijse.princetoncollege.repository;

import javafx.scene.control.Alert;
import lk.ijse.princetoncollege.db.DbConnection;
import lk.ijse.princetoncollege.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepo {
    // Define a method to retrieve all students from the database
    public static List<Student> getAll()  {
        List<Student> studentList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Establish database connection (replace the connection details with your own)
            connection = DbConnection.getInstance().getConnection();
            // SQL query to select all students
            String sql = "SELECT * FROM student";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            // Iterate through the result set and create Student objects
            while (resultSet.next()) {
                Student student = new Student(
                        resultSet.getString("student_id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("contact_no"),
                        resultSet.getString("NIC"),
                        resultSet.getString("user_id")
                );
                studentList.add(student);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);


        }
        return studentList;

    }
    public static boolean saveStudent(Student student) throws SQLException {

        String sqlCheck = "SELECT * FROM student WHERE student_id = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement checkStmt = connection.prepareStatement(sqlCheck);
        checkStmt.setString(1, student.getStudentID());
        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
            // Student ID already exists, handle appropriately
            new Alert(Alert.AlertType.ERROR, "Student ID already exists!").show();
        } else {
            // Insert new student record
            String sql = "INSERT INTO student (student_id, name, address, contact_no, NIC, user_id) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, student.getStudentID());
            pstm.setString(2, student.getFullName());
            pstm.setString(3, student.getAddress());
            pstm.setString(4, student.getContactNumber());
            pstm.setString(5, student.getNicNumber());
            pstm.setString(6, student.getUserId());

            return pstm.executeUpdate() > 0;
        }
        return false;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM student WHERE student_id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Student student) throws SQLException {
        String sql = "UPDATE student SET name = ?,address = ?,contact_no = ?,NIC = ?,user_id = ?  WHERE student_id = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setString(1, student.getFullName());
        pstm.setString(2, student.getAddress());
        pstm.setString(3, student.getContactNumber());
        pstm.setString(4, student.getNicNumber());
        pstm.setString(5, student.getUserId());
        pstm.setString(6, student.getStudentID());

        return pstm.executeUpdate() > 0;
    }
}

