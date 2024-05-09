package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.princetoncollege.model.Schedule;
import lk.ijse.princetoncollege.repository.ScheduleRepo;
import lk.ijse.princetoncollege.repository.StudentRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class EditScheduleForm {

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtLecturerId;

    @FXML
    private TextField txtModuleName;

    @FXML
    private TextField txtTime;

    @FXML
    private TextField txtscheduleId;

    @FXML
    void btnEditOnAction(ActionEvent event) {
        String schedule_id = txtscheduleId.getText();
        String name = txtModuleName.getText();
        String date = txtDate.getText();
        String time = txtTime.getText();
        String lecturer_id = txtLecturerId.getText();


        Schedule schedule = new Schedule(schedule_id, name,date, time, lecturer_id);

        try {
            boolean isUpdated = ScheduleRepo.update(schedule);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Schedule updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnExitOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/schedule_form.fxml")));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Schedule Form");
        stage.centerOnScreen();

    }

}
