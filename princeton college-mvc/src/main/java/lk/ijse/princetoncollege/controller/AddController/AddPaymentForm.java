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
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AddPaymentForm {

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtCourseId;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtPaymentId;

    @FXML
    private TextField txtStudentId;

    @FXML
    private TextField txtUserID;

    @FXML
    void btnAddOnAction(ActionEvent event) throws JRException, SQLException {
        String payment_id = txtPaymentId.getText();
        String amount = txtAmount.getText();
        String date = txtDate.getText();
        String student_id = txtStudentId.getText();
        String user_id = txtUserID.getText();
        String course_id = txtCourseId.getText();

        saveUser(payment_id, amount,date,student_id,user_id, course_id);

        JasperDesign jasperDesign =
                 JRXmlLoader.load(getClass().getResourceAsStream("/Report/PaymentReport.jrxml"));

        JasperReport jasperReport =
                JasperCompileManager.compileReport(jasperDesign);

        Map<String, Object> data = new HashMap<>();
        data.put("CustomerID",txtStudentId.getText());
        data.put("NetTotal","3000");

        JasperPrint jasperPrint =
                JasperFillManager.fillReport(
                        jasperReport,
                        data,
                        DbConnection.getInstance().getConnection());

        JasperViewer.viewReport(jasperPrint,false);

    }

    private void saveUser(String paymentId, String amount, String date, String studentId, String userId, String courseId) {

        try {
            String sqlCheck = "SELECT * FROM payment WHERE payment_id = ?";
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement checkStmt = connection.prepareStatement(sqlCheck);
            checkStmt.setString(1, paymentId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {

                new Alert(Alert.AlertType.ERROR, "Payment ID already exists!").show();
            } else {

                String sql = "INSERT INTO payment VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement pstm = connection.prepareStatement(sql);
                pstm.setObject(1, paymentId);
                pstm.setObject(2, amount);
                pstm.setObject(3, date);
                pstm.setObject(4, studentId);
                pstm.setObject(5, userId);
                pstm.setObject(6, courseId);

                if (pstm.executeUpdate() > 0) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Payment saved!").show();
                }
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something happened!").show();
            e.printStackTrace();
        }
    }

    @FXML
    void btnExitOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/payment_form.fxml")));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Payment Form");
        stage.centerOnScreen();

    }

}
