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
    void btnAddOnAction(ActionEvent event) {
        String lecturer_id = txtLecturerId.getText();
        String name = txtName.getText();
        String cno = txtCno.getText();
        String address = txtAddress.getText();
        String nic_no = txtNicNo.getText();

        saveUser(lecturer_id, name,cno,address,nic_no);


    }

    private void saveUser(String lecturer_id, String name, String cno, String address, String nic_no) {
        try {
            String sqlCheck = "SELECT * FROM lecturer WHERE lecturer_id = ?";
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement checkStmt = connection.prepareStatement(sqlCheck);
            checkStmt.setString(1, lecturer_id);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // Lecturer ID already exists, handle appropriately
                new Alert(Alert.AlertType.ERROR, "Lecturer ID already exists!").show();
            } else {
                // Insert new lecturer record
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

}
