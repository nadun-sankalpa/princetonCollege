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

import static java.util.prefs.Preferences.MAX_NAME_LENGTH;

public class AddlecturerForm {

    @FXML
    private AnchorPane rootNode;


    @FXML
    private TextField txtLecturerId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtCno;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtNicNo;
    @FXML
    void txtAddressReleasedOnAction(KeyEvent event) {
        Pattern adressPattern = Pattern.compile( "^([A-z0-9]|[-/,.@+]|\\\\s){4,}$");
        if(!adressPattern.matcher(txtAddress.getText()).matches()){
            addError(txtAddress);

        }else{
            removeError(txtAddress);
        }

    }

    @FXML
    void txtCnoReleasedOnAction(KeyEvent event) {
        Pattern phonePattern = Pattern.compile( "^([+]94{1,3}|[0])([1-9]{2})([0-9]){7}$");
        if(!phonePattern.matcher(txtCno.getText()).matches()){
            addError(txtCno);

        }else{
            removeError(txtCno);
        }

    }

    @FXML
    void txtLecturerIdReleasedOnAction(KeyEvent event) {
        Pattern LecturerIdPattern = Pattern.compile("^L\\d{3}$");
        if(!LecturerIdPattern.matcher(txtLecturerId.getText()).matches()){
            addError(txtLecturerId);

        }else{
            removeError(txtLecturerId);
        }

    }

    @FXML
    void txtNameReleasedOnAction(KeyEvent event) {
        Pattern NamePattern = Pattern.compile("^[A-z|\\\\s]{3,}$");
        if(!NamePattern.matcher(txtName.getText()).matches()){
            addError(txtName);

        }else{
            removeError(txtName);
        }

    }

    @FXML
    void txtNicNoReleasedOnAction(KeyEvent event) {
        Pattern NicNoPattern = Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{10})$");
        if(!NicNoPattern.matcher(txtNicNo.getText()).matches()){
            addError(txtNicNo);

        }else{
            removeError(txtNicNo);
        }

    }



    @FXML
    void btnAddOnAction(ActionEvent event) {
        String lecturer_id = txtLecturerId.getText();
        String name = txtName.getText();
        String cno = txtCno.getText();
        String address = txtAddress.getText();
        String nic_no = txtNicNo.getText();

        Pattern adressPattern = Pattern.compile( "^([A-z0-9]|[-/,.@+]|\\\\s){4,}$");
        Pattern phonePattern = Pattern.compile( "^([+]94{1,3}|[0])([1-9]{2})([0-9]){7}$");
        Pattern LecturerIdPattern = Pattern.compile("^L\\d{3}$");
        Pattern NamePattern = Pattern.compile("^[A-z|\\\\s]{3,}$");
        Pattern NicNoPattern = Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{10})$");

        if (name.length() > MAX_NAME_LENGTH) {
            // Handle error - name too long
            // You can display an error message to the user or handle it as appropriate
            System.out.println("Error: Name exceeds maximum length.");
            return;
        }

        if (isValidInput(adressPattern,phonePattern,LecturerIdPattern,NamePattern,NicNoPattern)) {

            saveUser(lecturer_id, name, cno, address, nic_no);
        }

    }


    private boolean isValidInput(Pattern adressPattern, Pattern phonePattern, Pattern LecturerIdPattern, Pattern NamePattern, Pattern NicNoPattern) {
        boolean isValid = true;
        if(!adressPattern.matcher(txtAddress.getText()).matches()){
            addError(txtAddress);
            isValid =false;

        }else{
            removeError(txtAddress);
        }

        if(!phonePattern.matcher(txtCno.getText()).matches()){
            addError(txtCno);
            isValid = false;

        }else{
            removeError(txtCno);
        }
        if(!LecturerIdPattern.matcher(txtLecturerId.getText()).matches()){
            addError(txtLecturerId);
            isValid = false;

        }else{
            removeError(txtLecturerId);
        }
        if(!NicNoPattern.matcher(txtNicNo.getText()).matches()){
            addError(txtNicNo);
            isValid = false;

        }else{
            removeError(txtNicNo);
        }
        if(!NamePattern.matcher(txtName.getText()).matches()){
            addError(txtName);
            isValid = false;
        }else{
            removeError(txtName);
        }
        return isValid;
    }

    private void saveUser(String lecturer_id, String name, String cno, String address, String nic_no) {
        try {
            String sqlCheck = "SELECT * FROM lecturer WHERE lecturer_id = ?";
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement checkStmt = connection.prepareStatement(sqlCheck);
            checkStmt.setString(1, lecturer_id);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {

                new Alert(Alert.AlertType.ERROR, "Lecturer ID already exists!").show();
            } else {

                String sql = "INSERT INTO lecturer VALUES (?, ?, ?, ?, ?)";
                PreparedStatement pstm = connection.prepareStatement(sql);
                pstm.setObject(1, lecturer_id);
                pstm.setObject(2, name);
                pstm.setObject(3, cno);
                pstm.setObject(4, address);
                pstm.setObject(5, nic_no);

                if (pstm.executeUpdate() > 0) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Lecturer saved!").show();
                }
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something happened!").show();
            e.printStackTrace();
        }

    }




    @FXML
    void btnExitOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/lecturer_form.fxml")));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Lecturer Form");
        stage.centerOnScreen();

    }
    private void removeError(TextField textField) {
        textField.setStyle("-fx-border-color: green");

    }

    private void addError(TextField textField) {
        textField.setStyle("-fx-border-color: red");
    }

}
