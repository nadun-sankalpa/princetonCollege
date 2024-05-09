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

public class AddBatchForm {

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtBatchId;

    @FXML
    private TextField txtBatchName;

    @FXML
    private TextField txtBatchRepresenter;

    @FXML
    private TextField txtMainLecturer;

    @FXML
    private TextField txtNoLecturers;

    @FXML
    private TextField txtNoStudents;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String batch_id = txtBatchId.getText();
        String name = txtBatchName.getText();
        String no_students = txtNoStudents.getText();
        String no_lecturers = txtNoLecturers.getText();
        String main_lecturer = txtMainLecturer.getText();
        String batch_representer = txtBatchRepresenter.getText();

        saveUser(batch_id, name,no_students, no_lecturers, main_lecturer, batch_representer);


    }

    private void saveUser(String batchId, String name, String noStudents, String noLecturers, String mainLecturer, String batchRepresenter) {
        try {
            String sqlCheck = "SELECT * FROM batch WHERE batch_id = ?";
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement checkStmt = connection.prepareStatement(sqlCheck);
            checkStmt.setString(1, batchId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {

                new Alert(Alert.AlertType.ERROR, "Batch ID already exists!").show();
            } else {

                String sql = "INSERT INTO batch VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement pstm = connection.prepareStatement(sql);
                pstm.setObject(1, batchId);
                pstm.setObject(2, name);
                pstm.setObject(3, noStudents);
                pstm.setObject(4, noLecturers);
                pstm.setObject(5, mainLecturer);
                pstm.setObject(6, batchRepresenter);

                if (pstm.executeUpdate() > 0) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Batch saved!").show();
                }
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something happened!").show();
            e.printStackTrace();
        }
    }

    @FXML
    void btnExitOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Batch_form.fxml")));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Batch Form");
        stage.centerOnScreen();

    }

}
