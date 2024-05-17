package lk.ijse.princetoncollege.controller.AddController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import lk.ijse.princetoncollege.db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.regex.Pattern;

public class AddCourseForm {

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
    void txtCourseFeeReleasedOnAction(KeyEvent event) {
        Pattern CourseFeePattern = Pattern.compile("^(0|[1-9]\\d{0,6}|10000000)$");
        if(!CourseFeePattern.matcher(txtCourseFee.getText()).matches()){
            addError(txtCourseFee);

        }else{
            removeError(txtCourseFee);
        }

    }

    @FXML
    void txtCourseIdReleasedOnAction(KeyEvent event) {
        Pattern CourseIdPattern = Pattern.compile("^C\\d{3}$");
        if(!CourseIdPattern.matcher(txtCourseId.getText()).matches()){
            addError(txtCourseId);

        }else{
            removeError(txtCourseId);
        }

    }

    @FXML
    void txtCourseNameReleasedOnAction(KeyEvent event) {
        Pattern CourseNamePattern = Pattern.compile("^[A-z|\\\\s]{3,}$");
        if(!CourseNamePattern.matcher(txtCourseName.getText()).matches()){
            addError(txtCourseName);

        }else{
            removeError(txtCourseName);
        }

    }

    @FXML
    void txtDurationReleasedOnAction(KeyEvent event) {
        Pattern DurationPattern = Pattern.compile("^([1-9]|[1-3][0-9]|4[0-8])\\s*months?$");
        if(!DurationPattern.matcher(txtDuration.getText()).matches()){
            addError(txtDuration);

        }else{
            removeError(txtDuration);
        }

    }

    @FXML
    void txtMainLecturerReleasedOnAction(KeyEvent event) {
        Pattern MainLecturerPattern = Pattern.compile("^[A-z|\\\\s]{3,}$");
        if(!MainLecturerPattern.matcher(txtMainLecturer.getText()).matches()){
            addError(txtMainLecturer);

        }else{
            removeError(txtMainLecturer);
        }


    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String course_id = txtCourseId.getText();
        String name = txtCourseName.getText();
        String duration = txtDuration.getText();
        String main_lecturer = txtMainLecturer.getText();
        String course_fee = txtCourseFee.getText();

        Pattern CourseFeePattern = Pattern.compile("^(0|[1-9]\\d{0,6}|10000000)$");
        Pattern CourseIdPattern = Pattern.compile("^C\\d{3}$");
        Pattern CourseNamePattern = Pattern.compile("^[A-z|\\\\s]{3,}$");
        Pattern DurationPattern = Pattern.compile("^([1-9]|[1-3][0-9]|4[0-8])\\s*months?$");
        Pattern MainLecturerPattern = Pattern.compile("^[A-z|\\\\s]{3,}$");

        if (isValidInput(CourseFeePattern,CourseIdPattern,CourseNamePattern,DurationPattern,MainLecturerPattern)) {

            saveUser(course_id, name, duration, main_lecturer, course_fee);
        }
    }

    private boolean isValidInput(Pattern courseFeePattern, Pattern courseIdPattern, Pattern courseNamePattern, Pattern durationPattern, Pattern mainLecturerPattern) {
        boolean isValid = true;
        if(!courseFeePattern.matcher(txtCourseFee.getText()).matches()){
            addError(txtCourseFee);
            isValid =false;

        }else{
            removeError(txtCourseFee);
        }

        if(!courseIdPattern.matcher(txtCourseId.getText()).matches()){
            addError(txtCourseId);
            isValid = false;

        }else{
            removeError(txtCourseId);
        }
        if(!courseNamePattern.matcher(txtCourseName.getText()).matches()){
            addError(txtCourseName);
            isValid = false;

        }else{
            removeError(txtCourseName);
        }
        if(!mainLecturerPattern.matcher(txtMainLecturer.getText()).matches()){
            addError(txtMainLecturer);
            isValid = false;

        }else{
            removeError(txtMainLecturer);
        }
        if(!durationPattern.matcher(txtDuration.getText()).matches()){
            addError(txtDuration);
            isValid = false;
        }else{
            removeError(txtDuration);
        }
        return isValid;
        }

    private void saveUser(String courseId, String name, String duration, String mainLecturer, String courseFee) {

        try {
            String sqlCheck = "SELECT * FROM course WHERE course_id = ?";
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement checkStmt = connection.prepareStatement(sqlCheck);
            checkStmt.setString(1, courseId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {

                new Alert(Alert.AlertType.ERROR, "Course ID already exists!").show();
            } else {

                String sql = "INSERT INTO course VALUES (?, ?, ?, ?, ?)";
                PreparedStatement pstm = connection.prepareStatement(sql);
                pstm.setObject(1, courseId);
                pstm.setObject(2, name);
                pstm.setObject(3, duration);
                pstm.setObject(4, mainLecturer);
                pstm.setObject(5, courseFee);

                if (pstm.executeUpdate() > 0) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Course saved!").show();
                }
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something happened!").show();
            e.printStackTrace();
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
    private void removeError(TextField textField) {
        textField.setStyle("-fx-border-color: green");

    }

    private void addError(TextField textField) {
        textField.setStyle("-fx-border-color: red");
    }

}
