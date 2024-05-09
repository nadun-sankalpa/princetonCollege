package lk.ijse.princetoncollege.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.princetoncollege.model.Lecturer;
import lk.ijse.princetoncollege.model.Student;
import lk.ijse.princetoncollege.model.tableModel.LecturerTm;
import lk.ijse.princetoncollege.model.tableModel.StudentTm;
import lk.ijse.princetoncollege.repository.LecturerRepo;
import lk.ijse.princetoncollege.repository.StudentRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LecturerProfileController {

    @FXML
    private BorderPane borderPane;
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContactNo;

    @FXML
    private TableColumn<?, ?> colLecturerId;

    @FXML
    private TableColumn<?, ?> colLecturerName;

    @FXML
    private TableColumn<?, ?> colNicNumber;
    @FXML
    private TextField txtSearch;


    @FXML
    private Text lblUserName;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<LecturerTm> tblLecturer;

    private List<Lecturer> lecturerList = new ArrayList<>();

    public void initialize() {
        this.lecturerList = LecturerRepo.getAll();
        setCellValueFactory();
        loadLecturerTable();
    }

    private void loadLecturerTable() {
        ObservableList<LecturerTm> tmList = FXCollections.observableArrayList();

        for (Lecturer lecturer : lecturerList) {
            LecturerTm lecturerTm = new LecturerTm(
                    lecturer.getLecturerID(),
                    lecturer.getLecturerName(),
                    lecturer.getContactNumber(),
                    lecturer.getAddress(),
                    lecturer.getNicNumber()

            );

            tmList.add(lecturerTm);
        }
        tblLecturer.setItems(tmList);

    }

    private void setCellValueFactory() {
        colLecturerId.setCellValueFactory(new PropertyValueFactory<>("LecturerID"));
        colLecturerName.setCellValueFactory(new PropertyValueFactory<>("LecturerName"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colNicNumber.setCellValueFactory(new PropertyValueFactory<>("nicNumber"));

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String id = txtSearch.getText();

        try {
            Lecturer lecturer = LecturerRepo.searchById(id);

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnAttendanceOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/attendance_form.fxml")));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Attendance Form");
        stage.centerOnScreen();

    }
    @FXML
    void btnAddlecturerOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/addLecturer_form.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Add Lecturer Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnDeleteLecturerOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/delete_form.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Delete Form");

        stage.show();

    }

    @FXML
    void btnEditLecturerOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/editLecturer_form.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Edit Lecturer Form");
        stage.centerOnScreen();


    }


        @FXML
    void btnBatchesOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Batch_form.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Batch Form");
        stage.centerOnScreen();

    }

    @FXML
    void btnCorsesOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/courses_form.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Course Form");
        stage.centerOnScreen();

    }

    @FXML
    void btnEmployesOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/employes_form.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Employe Form");
        stage.centerOnScreen();

    }

    @FXML
    void btnExamsOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/exams_form.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Exams Form");
        stage.centerOnScreen();


    }

    @FXML
    void btnExitOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
        stage.show();

    }

    @FXML
    void btnLecturersOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/lecturer_form.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Lecturer Form");
        stage.centerOnScreen();


    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");


    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) throws IOException {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/payment_form.fxml"));
            Stage stage = (Stage) rootNode.getScene().getWindow();

            stage.setScene(new Scene(anchorPane));
            stage.setTitle("Payment Form");
            stage.centerOnScreen();


        }

    @FXML
    void btnScheduleOnAction(ActionEvent event) throws IOException {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/schedule_form.fxml"));
            Stage stage = (Stage) rootNode.getScene().getWindow();

            stage.setScene(new Scene(anchorPane));
            stage.setTitle("Schedule Form");
            stage.centerOnScreen();


        }

    @FXML
    void btnStudentsOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/student_form.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Student Form");
        stage.centerOnScreen();


    }

}
