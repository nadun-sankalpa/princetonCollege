package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.princetoncollege.model.Course;
import lk.ijse.princetoncollege.repository.BatchRepo;
import lk.ijse.princetoncollege.repository.CourseRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class EditCourseForm {

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtCourseFee;

    @FXML
    private TextField txtCourseId;

    @FXML
    private TextField txtCourseName;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtMainLecturer;

    @FXML
    void btnEditOnAction(ActionEvent event) {
        String course_id = txtCourseId.getText();
        String name = txtCourseName.getText();
        String duration = txtDuration.getText();
        String main_lecturer = txtMainLecturer.getText();
        String course_fee = txtCourseFee.getText();

        Course course = new Course(course_id, name,duration,  main_lecturer, course_fee);

        try {
            boolean isUpdated = CourseRepo.update(course);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Course updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnExitOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/courses_form.fxml")));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Course Form");
        stage.centerOnScreen();
    }

}
