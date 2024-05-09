package lk.ijse.princetoncollege.repository;

import lk.ijse.princetoncollege.db.DbConnection;
import lk.ijse.princetoncollege.model.Employee;
import lk.ijse.princetoncollege.model.Exam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExamRepo {
    public static List<Exam> getAll()  {
        List<Exam> examList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Establish database connection (replace the connection details with your own)
            connection = DbConnection.getInstance().getConnection();
            // SQL query to select all students
            String sql = "SELECT * FROM exam";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            // Iterate through the result set and create Student objects
            while (resultSet.next()) {
                Exam exam = new Exam(
                        resultSet.getString("exam_id"),
                        resultSet.getString("exam_name"),
                        resultSet.getString("date"),
                        resultSet.getString("time"),
                        resultSet.getString("lecturer_id")
                );
                examList.add(exam);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);


        }
        return examList;

    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM exam WHERE exam_id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Exam exam) throws SQLException {
        String sql = "UPDATE exam SET exam_name = ?,date = ?,time = ?,lecturer_id = ?  WHERE exam_id = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1,exam.getExamName());
        pstm.setObject(2,exam.getDate());
        pstm.setObject(3,exam.getTime());
        pstm.setObject(4,exam.getLecturerID());
        pstm.setObject(5,exam.getExamID());

        return pstm.executeUpdate() > 0;
    }
}
