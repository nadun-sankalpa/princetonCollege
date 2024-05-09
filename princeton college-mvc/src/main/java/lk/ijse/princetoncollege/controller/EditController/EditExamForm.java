package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.princetoncollege.model.Exam;
import lk.ijse.princetoncollege.model.Lecturer;
import lk.ijse.princetoncollege.repository.ExamRepo;
import lk.ijse.princetoncollege.repository.LecturerRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class EditExamForm {

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtExamId;

    @FXML
    private TextField txtExamName;

    @FXML
    private TextField txtLecturerId;

    @FXML
    private TextField txtTime;

    @FXML
    void btnEditOnAction(ActionEvent event) {
        String exam_id = txtExamId.getText();
        String name = txtExamName.getText();
        String date = txtDate.getText();
        String time = txtTime.getText();
        String lecturer_id = txtLecturerId.getText();

        Exam exam = new Exam(exam_id, name,date, time, lecturer_id);

        try {
            boolean isUpdated = ExamRepo.update(exam);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Exam updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnExitOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/exams_form.fxml")));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Exam Form");
        stage.centerOnScreen();

    }

}
