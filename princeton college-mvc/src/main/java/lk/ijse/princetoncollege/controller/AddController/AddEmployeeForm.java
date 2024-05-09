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
    void btnAddOnAction(ActionEvent event) {
        String employee_id = txtEmployeeId.getText();
        String name = txtEmployeeName.getText();
        String cno = txtContactNo.getText();
        String address = txtAddress.getText();
        String nic_no = txtNicNo.getText();


        saveUser(employee_id, name,cno, address, nic_no);


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

}
