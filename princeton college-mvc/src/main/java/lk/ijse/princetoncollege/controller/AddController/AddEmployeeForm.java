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

public class AddEmployeeForm {

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContactNo;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtEmployeeName;

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
        if(!phonePattern.matcher(txtContactNo.getText()).matches()){
            addError(txtContactNo);

        }else{
            removeError(txtContactNo);
        }

    }

    @FXML
    void txtEmployeeIdReleasedOnAction(KeyEvent event) {
        Pattern EmployeeIdPattern = Pattern.compile("^E\\d{3}$");
        if(!EmployeeIdPattern.matcher(txtEmployeeId.getText()).matches()){
            addError(txtEmployeeId);

        }else{
            removeError(txtEmployeeId);
        }

    }

    @FXML
    void txtNameReleasedOnAction(KeyEvent event) {
        Pattern NamePattern = Pattern.compile("^[A-z|\\\\s]{3,}$");
        if(!NamePattern.matcher(txtEmployeeName.getText()).matches()){
            addError(txtEmployeeName);

        }else{
            removeError(txtEmployeeName);
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
        String employee_id = txtEmployeeId.getText();
        String name = txtEmployeeName.getText();
        String cno = txtContactNo.getText();
        String address = txtAddress.getText();
        String nic_no = txtNicNo.getText();

        Pattern adressPattern = Pattern.compile( "^([A-z0-9]|[-/,.@+]|\\\\s){4,}$");
        Pattern phonePattern = Pattern.compile( "^([+]94{1,3}|[0])([1-9]{2})([0-9]){7}$");
        Pattern EmployeeIdPattern = Pattern.compile("^E\\d{3}$");
        Pattern NamePattern = Pattern.compile("^[A-z|\\\\s]{3,}$");
        Pattern NicNoPattern = Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{10})$");

        if (isValidInput(adressPattern,phonePattern,EmployeeIdPattern,NamePattern,NicNoPattern)) {


            saveUser(employee_id, name, cno, address, nic_no);
        }

    }

    private boolean isValidInput(Pattern adressPattern, Pattern phonePattern, Pattern EmployeeIdPattern, Pattern NamePattern, Pattern NicNoPattern) {
        boolean isValid = true;
        if(!adressPattern.matcher(txtAddress.getText()).matches()){
            addError(txtAddress);
            isValid =false;

        }else{
            removeError(txtAddress);
        }

        if(!phonePattern.matcher(txtContactNo.getText()).matches()){
            addError(txtContactNo);
            isValid = false;

        }else{
            removeError(txtContactNo);
        }
        if(!EmployeeIdPattern.matcher(txtEmployeeId.getText()).matches()){
            addError(txtEmployeeId);
            isValid = false;

        }else{
            removeError(txtEmployeeId);
        }
        if(!NicNoPattern.matcher(txtNicNo.getText()).matches()){
            addError(txtNicNo);
            isValid = false;

        }else{
            removeError(txtNicNo);
        }
        if(!NamePattern.matcher(txtEmployeeName.getText()).matches()){
            addError(txtEmployeeName);
            isValid = false;

        }else{
            removeError(txtNicNo);
        }

        return isValid;
    }

    private void saveUser(String employeeId, String name, String cno, String address, String nicNo) {
        try {
            String sqlCheck = "SELECT * FROM employee WHERE employee_id = ?";
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement checkStmt = connection.prepareStatement(sqlCheck);
            checkStmt.setString(1, employeeId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {

                new Alert(Alert.AlertType.ERROR, "Employee ID already exists!").show();
            } else {

                String sql = "INSERT INTO employee VALUES (?, ?, ?, ?, ?)";
                PreparedStatement pstm = connection.prepareStatement(sql);
                pstm.setObject(1, employeeId);
                pstm.setObject(2, name);
                pstm.setObject(3, cno);
                pstm.setObject(4, address);
                pstm.setObject(5, nicNo);


                if (pstm.executeUpdate() > 0) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Employee saved!").show();
                }
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something happened!").show();
            e.printStackTrace();
        }
    }

    @FXML
    void btnExitOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/employes_form.fxml")));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Employee Form");
        stage.centerOnScreen();

    }
    private void removeError(TextField textField) {
        textField.setStyle("-fx-border-color: green");

    }

    private void addError(TextField textField) {
        textField.setStyle("-fx-border-color: red");
    }

}
