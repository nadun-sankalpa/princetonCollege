package lk.ijse.princetoncollege.repository;

import lk.ijse.princetoncollege.db.DbConnection;
import lk.ijse.princetoncollege.model.Attendance;
import lk.ijse.princetoncollege.model.Batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttendanceRepo {
    public static List<Attendance> getAll() {
        List<Attendance> attendanceList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Establish database connection (replace the connection details with your own)
            connection = DbConnection.getInstance().getConnection();
            // SQL query to select all students
            String sql = "SELECT * FROM attendance";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            // Iterate through the result set and create Student objects
            while (resultSet.next()) {
                Attendance attendance = new Attendance(
                        resultSet.getString("attendance_id"),
                        resultSet.getString("student_name"),
                        resultSet.getString("date"),
                        resultSet.getString("in_time"),
                        resultSet.getString("out_time"),
                        resultSet.getString("user_id")
                );
                attendanceList.add(attendance);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);


        }
        return attendanceList;

    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM attendance WHERE attendance_id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Attendance attendance) throws SQLException {
        String sql = "UPDATE attendance SET student_name = ?, date = ?, in_time = ?, out_time = ?, user_id = ?  WHERE attendance_id = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        pstm.setObject(1,attendance.getStudentName());
        pstm.setObject(2,attendance.getDate());
        pstm.setObject(3,attendance.getInTime());
        pstm.setObject(4,attendance.getOutTime());
        pstm.setObject(5,attendance.getUserId());
        pstm.setObject(6,attendance.getAttendanceID());

        return pstm.executeUpdate() > 0;
    }
}
