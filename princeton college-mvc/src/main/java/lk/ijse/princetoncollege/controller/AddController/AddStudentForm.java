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
import lk.ijse.princetoncollege.model.Payment;
import lk.ijse.princetoncollege.model.Student;
import lk.ijse.princetoncollege.repository.AddStudentRepo;
import lk.ijse.princetoncollege.repository.PaymentRepo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

public class AddStudentForm {

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCno;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtInitialPayment;

    @FXML
    private TextField txtNicNo;

    @FXML
    private TextField txtStudentId;

    @FXML
    private TextField txtUserId;


    @FXML
    private TextField txtCourseId;

    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException {
        String student_id = txtStudentId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String cno = txtCno.getText();
        String nic_no = txtNicNo.getText();
        String user_id = txtUserId.getText();
        String initial_payment = txtInitialPayment.getText();
        String course_id = txtCourseId.getText();
        String date = String.valueOf(LocalDate.now());
        String PaymentId = PaymentRepo.getnextPaymentId();

       Student student = new Student(student_id,name,address,cno,nic_no,user_id);

       Payment payment = new Payment(PaymentId,initial_payment,date,student_id,user_id,course_id);

       boolean isSaved = AddStudentRepo.studentRegistration(student,payment);

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
