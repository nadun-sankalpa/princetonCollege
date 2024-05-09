package lk.ijse.princetoncollege.repository;

import lk.ijse.princetoncollege.db.DbConnection;
import lk.ijse.princetoncollege.model.Batch;
import lk.ijse.princetoncollege.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BatchRepo {
    public static List<Batch> getAll()  {
        List<Batch> batchList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Establish database connection (replace the connection details with your own)
            connection = DbConnection.getInstance().getConnection();
            // SQL query to select all students
            String sql = "SELECT * FROM batch";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            // Iterate through the result set and create Student objects
            while (resultSet.next()) {
                Batch batch = new Batch(
                        resultSet.getString("batch_id"),
                        resultSet.getString("batch_name"),
                        resultSet.getString("no_of_students"),
                        resultSet.getString("no_of_lecturers"),
                        resultSet.getString("main_lecturer"),
                        resultSet.getString("batch_representer")
                );
                batchList.add(batch);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);


        }
        return batchList;

    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM batch WHERE batch_id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Batch batch) throws SQLException {
        String sql = "UPDATE batch SET batch_name = ?, no_of_students = ?, no_of_lecturers = ?,main_lecturer = ?, batch_representer = ?  WHERE batch_id = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1,batch.getBatchName());
        pstm.setObject(2,batch.getNoOfStudents());
        pstm.setObject(3,batch.getNoOfLecturers());
        pstm.setObject(4,batch.getMainLecturer());
        pstm.setObject(5,batch.getBatchReprsenter());
        pstm.setObject(6,batch.getBatchID());

        return pstm.executeUpdate() > 0;
    }
}
