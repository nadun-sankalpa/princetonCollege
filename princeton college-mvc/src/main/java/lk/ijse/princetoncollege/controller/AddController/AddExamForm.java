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

public class AddExamForm {

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
    void btnAddOnAction(ActionEvent event) {
        String exam_id = txtExamId.getText();
        String name = txtExamName.getText();
        String date = txtDate.getText();
        String time = txtTime.getText();
        String lecturer_id = txtLecturerId.getText();


        saveUser(exam_id, name,date, time, lecturer_id);

    }

    private void saveUser(String examId, String name, String date, String time, String lecturerId) {
        try {
            String sqlCheck = "SELECT * FROM exam WHERE exam_id = ?";
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement checkStmt = connection.prepareStatement(sqlCheck);
            checkStmt.setString(1, examId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {

                new Alert(Alert.AlertType.ERROR, "Exam ID already exists!").show();
            } else {

                String sql = "INSERT INTO exam VALUES (?, ?, ?, ?, ?)";
                PreparedStatement pstm = connection.prepareStatement(sql);
                pstm.setObject(1, examId);
                pstm.setObject(2, name);
                pstm.setObject(3, date);
                pstm.setObject(4, time);
                pstm.setObject(5, lecturerId);


                if (pstm.executeUpdate() > 0) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Exam saved!").show();
                }
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something happened!").show();
            e.printStackTrace();
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
