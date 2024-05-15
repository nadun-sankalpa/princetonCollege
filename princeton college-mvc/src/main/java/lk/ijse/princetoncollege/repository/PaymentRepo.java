package lk.ijse.princetoncollege.repository;

import javafx.scene.chart.XYChart;
import lk.ijse.princetoncollege.db.DbConnection;
import lk.ijse.princetoncollege.model.Payment;
import lk.ijse.princetoncollege.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepo {

    public static List<Payment> getAll()  {
        List<Payment> paymentList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Establish database connection (replace the connection details with your own)
            connection = DbConnection.getInstance().getConnection();
            // SQL query to select all students
            String sql = "SELECT * FROM payment";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            // Iterate through the result set and create Student objects
            while (resultSet.next()) {
                Payment payment = new Payment(
                        resultSet.getString("payment_id"),
                        resultSet.getString("amount"),
                        resultSet.getString("date"),
                        resultSet.getString("student_id"),
                        resultSet.getString("user_id"),
                        resultSet.getString("course_id")
                );
                paymentList.add(payment);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);


        }
        return paymentList;

    }
    public static String getnextPaymentId() throws SQLException {
        String nextId =null;
        int id=0;
        String sql = "select payment_id from payment order by payment_id desc limit 1";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            id = Integer.parseInt(resultSet.getString("payment_id"));
            id++;
        }
        return nextId = String.valueOf(id);

    }

    public static boolean savePayment(Payment payment) throws SQLException {
        String sql = "INSERT INTO payment (payment_id, amount, date, student_id, user_id, course_id) VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement checkStmt = connection.prepareStatement(sql);
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, payment.getPaymentID());
        pstm.setString(2, payment.getAmount());
        pstm.setString(3, payment.getDate());
        pstm.setString(4, payment.getStudentID());
        pstm.setString(5, payment.getUserID());
        pstm.setString(6, payment.getCourseID());
        return pstm.executeUpdate() > 0;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM payment WHERE payment_id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Payment payment) throws SQLException {

        String sql = "UPDATE payment SET amount = ?, date = ?, student_id = ?,user_id = ?, course_id = ?  WHERE payment_id = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1,payment.getAmount());
        pstm.setObject(2,payment.getDate());
        pstm.setObject(3,payment.getStudentID());
        pstm.setObject(4,payment.getUserID());
        pstm.setObject(5,payment.getCourseID());
        pstm.setObject(6,payment.getPaymentID());

        return pstm.executeUpdate() > 0;

    }

    public static XYChart.Series IncomeChart(XYChart.Series chart) {
        String sql = "SELECT date , SUM(amount) FROM payment GROUP BY date ORDER BY TIMESTAMP(date)";

        try {
            ResultSet resultSet = DbConnection.getInstance().getConnection().prepareStatement(sql).executeQuery();

            while (resultSet.next()) {
                chart.getData().add(new XYChart.Data<>(resultSet.getString(1), resultSet.getFloat(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chart;

    }
}
