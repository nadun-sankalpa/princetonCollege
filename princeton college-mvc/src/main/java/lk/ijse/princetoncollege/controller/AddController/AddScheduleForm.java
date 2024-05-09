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

public class AddScheduleForm {

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
    void btnAddOnAction(ActionEvent event) {
        String schedule_id = txtscheduleId.getText();
        String name = txtModuleName.getText();
        String date = txtDate.getText();
        String time = txtTime.getText();
        String lecturer_id = txtLecturerId.getText();


        saveUser(schedule_id, name,date, time, lecturer_id);


    }

    private void saveUser(String scheduleId, String name, String date, String time, String lecturerId) {
        try {
            String sqlCheck = "SELECT * FROM schedule WHERE schedule_id = ?";
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement checkStmt = connection.prepareStatement(sqlCheck);
            checkStmt.setString(1, scheduleId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {

                new Alert(Alert.AlertType.ERROR, "Schedule ID already exists!").show();
            } else {

                String sql = "INSERT INTO schedule VALUES (?, ?, ?, ?, ?)";
                PreparedStatement pstm = connection.prepareStatement(sql);
                pstm.setObject(1, scheduleId);
                pstm.setObject(2, name);
                pstm.setObject(3, date);
                pstm.setObject(4, time);
                pstm.setObject(5, lecturerId);


                if (pstm.executeUpdate() > 0) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Schedule saved!").show();
                }
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something happened!").show();
            e.printStackTrace();
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
