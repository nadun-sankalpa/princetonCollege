package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.princetoncollege.model.Attendance;
import lk.ijse.princetoncollege.repository.AttendanceRepo;
import lk.ijse.princetoncollege.repository.BatchRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class EditAttendanceForm {

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
    void btnEditOnAction(ActionEvent event) {
        String attendance_id = txtAttendanceId.getText();
        String student_name = txtStudentName.getText();
        String date = txtDate.getText();
        String in_time = txtInTime.getText();
        String out_time = txtOutTime.getText();
        String user_id = txtUserId.getText();

        Attendance attendance = new Attendance(attendance_id,student_name,date,in_time,out_time,user_id);

        try {
            boolean isUpdated = AttendanceRepo.update(attendance);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Attendance updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
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
