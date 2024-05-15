package lk.ijse.princetoncollege.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.princetoncollege.db.DbConnection;
import lk.ijse.princetoncollege.repository.PaymentRepo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class DashboardFormController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Label lblStudentCount;
    @FXML
    private Label lblLecturerCount;


    @FXML
    private Label lblBatchCount;

    @FXML
    private Label lblEmployeCount;

    @FXML
    private Text lblUserName;
    @FXML
    private BarChart<?, ?> IncomeChart;

    @FXML
    private AnchorPane rootNode;

    private void IncomeChart(){
        IncomeChart.getData().clear();

        XYChart.Series chart = new XYChart.Series();

        XYChart.Series series = PaymentRepo.IncomeChart(chart);

        IncomeChart.getData().add(series);


    }

    public void initialize() {
        IncomeChart();
        try {
            int studentCount = getStudentCount();
            setStudentCount(studentCount);

            int lecturerCount = getLecturerCount();
            setLecturerCount(lecturerCount);

            int batchCount = getBatchCount();
            setBatchCount(batchCount);

            int employeCount = getEmployeCount();
            setEmployeCount(employeCount);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void setEmployeCount(int employeCount) {
        lblEmployeCount.setText(String.valueOf(employeCount));
    }

    private int getEmployeCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS employe_count FROM employee";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        int employeCount = 0;
        if (resultSet.next()) {
            employeCount = resultSet.getInt("employe_count");
        }

        return employeCount;
    }

    private void setBatchCount(int batchCount) {
        lblBatchCount.setText(String.valueOf(batchCount));
    }

    private int getBatchCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS batch_count FROM batch";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        int batchCount = 0;
        if (resultSet.next()) {
            batchCount = resultSet.getInt("batch_count");
        }
        return batchCount;
    }

    private void setStudentCount(int studentCount) {
        lblStudentCount.setText(String.valueOf(studentCount));
    }

    private int getStudentCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS student_count FROM student";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        int studentCount = 0;
        if (resultSet.next()) {
            studentCount = resultSet.getInt("student_count");
        }
        return studentCount;
    }

    private void setLecturerCount(int lecturerCount) {
        lblLecturerCount.setText(String.valueOf(lecturerCount));
    }

    private int getLecturerCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS lecturer_count FROM lecturer";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        int lecturerCount = 0;
        if (resultSet.next()) {
            lecturerCount = resultSet.getInt("lecturer_count");
        }
        return lecturerCount;
    }



    @FXML
    void btnAttendanceOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/attendance_form.fxml")));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Attendance Form");
        stage.centerOnScreen();


    }

    @FXML
    void btnBatchesOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Batch_form.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Batch Form");
        stage.centerOnScreen();


    }

    @FXML
    void btnCoursesOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/courses_form.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Course Form");
        stage.centerOnScreen();


    }

    @FXML
    void btnEmployesOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/employes_form.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Employe Form");
        stage.centerOnScreen();


    }

    @FXML
    void btnExamsOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/exams_form.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Exams Form");
        stage.centerOnScreen();


    }

    @FXML
    void btnLecturersOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/lecturer_form.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Lecturer Form");
        stage.centerOnScreen();


    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");

    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/payment_form.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Payment Form");
        stage.centerOnScreen();


    }

    @FXML
    void btnScheduleOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/schedule_form.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Schedule Form");
        stage.centerOnScreen();


    }

    @FXML
    void btnStudentsOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/student_form.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Student Form");
        stage.centerOnScreen();


    }

}
