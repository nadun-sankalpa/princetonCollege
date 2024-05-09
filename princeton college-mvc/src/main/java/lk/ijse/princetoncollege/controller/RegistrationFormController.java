package lk.ijse.princetoncollege.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.princetoncollege.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationFormController {

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCno;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPw;

    @FXML
    private TextField txtUserId;

    @FXML
    void btnRegister(ActionEvent event) {
        String user_id = txtUserId.getText();
        String name = txtName.getText();
        String pw = txtPw.getText();
        String address = txtAddress.getText();
        String cno = txtCno.getText();

        saveUser(user_id, name, pw,address,cno);

    }

    private void saveUser(String user_id, String name, String pw, String address, String cno) {
        try {
            String sql = "INSERT INTO user VALUES(?, ?, ?, ?, ?)";

            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setObject(1, user_id);
            pstm.setObject(2, name);
            pstm.setObject(3,cno);
            pstm.setObject(4,pw);
            pstm.setObject(5,address);

            if(pstm.executeUpdate() > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "user saved!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "something happend!").show();
            e.printStackTrace();
        }
    }

}
