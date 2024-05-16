package lk.ijse.princetoncollege.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.princetoncollege.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javafx.scene.input.KeyEvent;

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
    void txtCnoOnKeyReleasedAction(KeyEvent event) {
        Pattern phonePattern = Pattern.compile( "^([+]94{1,3}|[0])([1-9]{2})([0-9]){7}$");
        if(!phonePattern.matcher(txtCno.getText()).matches()){
            addError(txtCno);

        }else{
            removeError(txtCno);
        }

    }

    @FXML
    void txtUserIdReleaseOnAction(KeyEvent event) {
        Pattern IDPattern = Pattern.compile("^U\\d{3}$");
        if(!IDPattern.matcher(txtUserId.getText()).matches()){
            addError(txtUserId);

        }else{
            removeError(txtUserId);
        }

    }

    @FXML
    void btnRegister(ActionEvent event) {
        String user_id = txtUserId.getText();
        String name = txtName.getText();
        String pw = txtPw.getText();
        String address = txtAddress.getText();
        String cno = txtCno.getText();

        Pattern phonePattern = Pattern.compile( "^([+]94{1,3}|[0])([1-9]{2})([0-9]){7}$");
        Pattern IDPattern = Pattern.compile("^U\\d{3}$");
        if (isValidInput(IDPattern,phonePattern)) {

            saveUser(user_id, name, pw, address, cno);
        }

    }

    private boolean isValidInput(Pattern idPattern, Pattern phonePattern) {
        boolean isValid = true;
        if(!idPattern.matcher(txtUserId.getText()).matches()){
            addError(txtUserId);
            isValid =false;

        }else{
            removeError(txtUserId);
        }

        if(!phonePattern.matcher(txtCno.getText()).matches()){
            addError(txtCno);
            isValid = false;

        }else{
            removeError(txtCno);
        }
        return isValid;
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
    private void removeError(TextField textField) {
        textField.setStyle("-fx-border-color: green");

    }

    private void addError(TextField textField) {
        textField.setStyle("-fx-border-color: red");
    }

}
