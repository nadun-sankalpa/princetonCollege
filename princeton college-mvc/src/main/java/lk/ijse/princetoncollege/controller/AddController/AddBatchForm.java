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
    void txtBatchIdReleasedOnAction(KeyEvent event) {
        Pattern BatchIDPattern = Pattern.compile("^B\\d{3}$");
        if(!BatchIDPattern.matcher(txtBatchId.getText()).matches()){
            addError(txtBatchId);

        }else{
            removeError(txtBatchId);
        }

    }

    @FXML
    void txtBatchNameReleasedOnAction(KeyEvent event) {
        Pattern BatchNamePattern = Pattern.compile("^([A-z0-9]|[-/,.@+]|\\\\s){4,}$");
        if(!BatchNamePattern.matcher(txtBatchName.getText()).matches()){
            addError(txtBatchName);

        }else{
            removeError(txtBatchName);
        }

    }

    @FXML
    void txtBatchRepresenterReleasedOnAction(KeyEvent event) {
        Pattern BatchRepresenterPattern = Pattern.compile("^[A-z|\\\\s]{3,}$");
        if(!BatchRepresenterPattern.matcher(txtBatchRepresenter.getText()).matches()){
            addError(txtBatchRepresenter);

        }else{
            removeError(txtBatchRepresenter);
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
    void txtNoLecturersReleasedOnAction(KeyEvent event) {
        Pattern NoLecturersPattern = Pattern.compile("^([0-9]|[1-9][0-9]|100)$");
        if(!NoLecturersPattern.matcher(txtNoLecturers.getText()).matches()){
            addError(txtNoLecturers);

        }else{
            removeError(txtNoLecturers);
        }


    }

    @FXML
    void txtNoStudentsReleasedOnAction(KeyEvent event) {
        Pattern NoStudentsPattern = Pattern.compile("^([0-9]|[1-9][0-9]|100)$");
        if(!NoStudentsPattern.matcher(txtNoStudents.getText()).matches()){
            addError(txtNoStudents);

        }else{
            removeError(txtNoStudents);
        }

    }
    @FXML
    void btnAddOnAction(ActionEvent event) {
        String batch_id = txtBatchId.getText();
        String name = txtBatchName.getText();
        String no_students = txtNoStudents.getText();
        String no_lecturers = txtNoLecturers.getText();
        String main_lecturer = txtMainLecturer.getText();
        String batch_representer = txtBatchRepresenter.getText();

        Pattern BatchIDPattern = Pattern.compile("^B\\d{3}$");
        Pattern BatchNamePattern = Pattern.compile("^([A-z0-9]|[-/,.@+]|\\\\s){4,}$");
        Pattern BatchRepresenterPattern = Pattern.compile("^[A-z|\\\\s]{3,}$");
        Pattern MainLecturerPattern = Pattern.compile("^[A-z|\\\\s]{3,}$");
        Pattern NoLecturersPattern = Pattern.compile("^([0-9]|[1-9][0-9]|100)$");
        Pattern NoStudentsPattern = Pattern.compile("^([0-9]|[1-9][0-9]|100)$");

        if (isValidInput(BatchIDPattern,BatchNamePattern,BatchRepresenterPattern,MainLecturerPattern,NoLecturersPattern,NoStudentsPattern)) {

            saveUser(batch_id, name, no_students, no_lecturers, main_lecturer, batch_representer);
        }


    }

    private boolean isValidInput(Pattern batchIDPattern, Pattern batchNamePattern, Pattern batchRepresenterPattern, Pattern mainLecturerPattern, Pattern noLecturersPattern, Pattern noStudentsPattern) {
        boolean isValid = true;
        if(!batchIDPattern.matcher(txtBatchId.getText()).matches()){
            addError(txtBatchId);
            isValid =false;

        }else{
            removeError(txtBatchId);
        }

        if(!batchNamePattern.matcher(txtBatchName.getText()).matches()){
            addError(txtBatchName);
            isValid = false;

        }else{
            removeError(txtBatchName);
        }
        if(!batchRepresenterPattern.matcher(txtBatchRepresenter.getText()).matches()){
            addError(txtBatchRepresenter);
            isValid = false;

        }else{
            removeError(txtBatchRepresenter);
        }
        if(!mainLecturerPattern.matcher(txtMainLecturer.getText()).matches()){
            addError(txtMainLecturer);
            isValid = false;

        }else{
            removeError(txtMainLecturer);
        }
        if(!noLecturersPattern.matcher(txtNoLecturers.getText()).matches()){
            addError(txtNoLecturers);
            isValid = false;

        }else{
            removeError(txtNoLecturers);
        }
        if(!noStudentsPattern.matcher(txtNoStudents.getText()).matches()){
            addError(txtNoStudents);
            isValid = false;

        }else{
            removeError(txtNoStudents);
        }
        return isValid;
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
    private void removeError(TextField textField) {
        textField.setStyle("-fx-border-color: green");

    }

    private void addError(TextField textField) {
        textField.setStyle("-fx-border-color: red");
    }

}
