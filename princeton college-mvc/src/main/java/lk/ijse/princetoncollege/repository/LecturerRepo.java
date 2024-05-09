package lk.ijse.princetoncollege.repository;
import lk.ijse.princetoncollege.db.DbConnection;
import lk.ijse.princetoncollege.model.Lecturer;
import lk.ijse.princetoncollege.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LecturerRepo {
    public static List<Lecturer> getAll() {
        List<Lecturer> lecturerList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Establish database connection (replace the connection details with your own)
            connection = DbConnection.getInstance().getConnection();
            // SQL query to select all students
            String sql = "SELECT * FROM lecturer";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            // Iterate through the result set and create Student objects
            while (resultSet.next()) {
                Lecturer lecturer = new Lecturer(
                        resultSet.getString("lecturer_id"),
                        resultSet.getString("name"),
                        resultSet.getString("contact_no"),
                        resultSet.getString("address"),
                        resultSet.getString("nic_no")

                );
                lecturerList.add(lecturer);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);


        }
        return lecturerList;

    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM lecturer WHERE lecturer_id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static Lecturer searchById(String id) throws SQLException {
        String sql = "SELECT * FROM lecturer WHERE lecturer_id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);
        ResultSet resultSet = pstm.executeQuery();

        Lecturer lecturer = null;
        return lecturer;
    }

    public static boolean update(Lecturer lecturer) throws SQLException {
        String sql = "UPDATE lecturer SET name = ?,contact_no = ?,address = ?,nic_no = ?  WHERE lecturer_id = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);


        pstm.setObject(1, lecturer.getLecturerName());
        pstm.setObject(2, lecturer.getContactNumber());
        pstm.setObject(3, lecturer.getAddress());
        pstm.setObject(4, lecturer.getNicNumber());
        pstm.setObject(5, lecturer.getLecturerID());




        return pstm.executeUpdate() > 0;
    }
}

