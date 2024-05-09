package lk.ijse.princetoncollege.controller.AddController;

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
    void btnAddOnAction(ActionEvent event) {
        String course_id = txtCourseId.getText();
        String name = txtCourseName.getText();
        String duration = txtDuration.getText();
        String main_lecturer = txtMainLecturer.getText();
        String course_fee = txtCourseFee.getText();

        saveUser(course_id, name,duration,  main_lecturer, course_fee);

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

}
