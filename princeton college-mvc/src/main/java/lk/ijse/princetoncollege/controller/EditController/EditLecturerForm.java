package lk.ijse.princetoncollege.controller.EditController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.princetoncollege.model.Lecturer;
import lk.ijse.princetoncollege.repository.LecturerRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class EditLecturerForm {

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCno;

    @FXML
    private TextField txtLecturerId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNicNo;

    @FXML
    void btnEditOnAction(ActionEvent event) {
        String lecturer_id = txtLecturerId.getText();
        String name = txtName.getText();
        String cno = txtCno.getText();
        String address = txtAddress.getText();
        String nic_no = txtNicNo.getText();

        Lecturer lecturer = new Lecturer(lecturer_id, name,cno,address,nic_no);

        try {
            boolean isUpdated = LecturerRepo.update(lecturer);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "lecturer updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnExitOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/lecturer_form.fxml")));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("lecturer Form");
        stage.centerOnScreen();

    }

}
