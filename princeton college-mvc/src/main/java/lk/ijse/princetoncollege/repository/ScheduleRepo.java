package lk.ijse.princetoncollege.repository;

import lk.ijse.princetoncollege.db.DbConnection;
import lk.ijse.princetoncollege.model.Employee;
import lk.ijse.princetoncollege.model.Schedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleRepo {
    public static List<Schedule> getAll()  {
        List<Schedule> scheduleList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Establish database connection (replace the connection details with your own)
            connection = DbConnection.getInstance().getConnection();
            // SQL query to select all students
            String sql = "SELECT * FROM schedule";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            // Iterate through the result set and create Student objects
            while (resultSet.next()) {
                Schedule schedule = new Schedule(
                        resultSet.getString("schedule_id"),
                        resultSet.getString("module_name"),
                        resultSet.getString("date"),
                        resultSet.getString("time"),
                        resultSet.getString("lecturer_id")
                );
                scheduleList.add(schedule);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);


        }
        return scheduleList;

    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM schedule WHERE schedule_id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Schedule schedule) throws SQLException {

        String sql = "UPDATE schedule SET module_name = ?, date = ?, time = ?,lecturer_id = ?,WHERE schedule_id = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1,schedule.getModuleName());
        pstm.setObject(2,schedule.getDate());
        pstm.setObject(3,schedule.getTime());
        pstm.setObject(4,schedule.getLecturerID());
        pstm.setObject(5,schedule.getScheduleID());

        return pstm.executeUpdate() > 0;
    }
}
