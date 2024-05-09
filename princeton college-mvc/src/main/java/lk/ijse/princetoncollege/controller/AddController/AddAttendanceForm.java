package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.princetoncollege.db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class AddAttendanceForm {

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtAttendanceId;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtInTime;

    @FXML
    private TextField txtOutTime;

    @FXML
    private TextField txtStudentName;

    @FXML
    private TextField txtUserId;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String attendance_id = txtAttendanceId.getText();
        String student_name = txtStudentName.getText();
        String date = txtDate.getText();
        String in_time = txtInTime.getText();
        String out_time = txtOutTime.getText();
        String user_id = txtUserId.getText();

        saveUser(attendance_id,student_name,date,in_time,out_time,user_id);

    }

    private void saveUser(String attendanceId, String studentName, String date, String inTime, String outTime, String userId) {
        try {
            String sqlCheck = "SELECT * FROM attendance WHERE attendance_id = ?";
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement checkStmt = connection.prepareStatement(sqlCheck);
            checkStmt.setString(1, attendanceId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {

                new Alert(Alert.AlertType.ERROR, "Attendance ID already exists!").show();
            } else {

                String sql = "INSERT INTO attendance VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement pstm = connection.prepareStatement(sql);
                pstm.setObject(1, attendanceId);
                pstm.setObject(2, studentName);
                pstm.setObject(3, date);
                pstm.setObject(4, inTime);
                pstm.setObject(5, outTime);
                pstm.setObject(6, userId);

                if (pstm.executeUpdate() > 0) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Attendance saved!").show();
                }
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something happened!").show();
            e.printStackTrace();
        }
    }

    @FXML
    void btnExitOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/attendance_form.fxml")));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Attendance Form");
        stage.centerOnScreen();

    }

}
