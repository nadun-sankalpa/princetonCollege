package lk.ijse.princetoncollege.controller.EditController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.princetoncollege.model.Student;
import lk.ijse.princetoncollege.repository.LecturerRepo;
import lk.ijse.princetoncollege.repository.StudentRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class EditStudentForm {

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCno;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNicNo;

    @FXML
    private TextField txtStudentId;

    @FXML
    private TextField txtUserId;

    @FXML
    void btnEditOnAction(ActionEvent event) {
        String student_id = txtStudentId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String cno = txtCno.getText();
        String nic_no = txtNicNo.getText();
        String user_id = txtUserId.getText();

        Student student = new Student(student_id,name,address,cno,nic_no,user_id);

        try {
            boolean isUpdated = StudentRepo.update(student);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnExitOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/student_form.fxml")));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Student Form");
        stage.centerOnScreen();

    }

}
