package lk.ijse.princetoncollege.repository;

import lk.ijse.princetoncollege.db.DbConnection;
import lk.ijse.princetoncollege.model.Payment;
import lk.ijse.princetoncollege.model.Student;

import java.sql.Connection;
import java.sql.SQLException;

public class AddStudentRepo {

    public static boolean studentRegistration(Student student, Payment payment) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
try {
    boolean isStudentSaved = StudentRepo.saveStudent(student);
    if(isStudentSaved) {
        boolean isPayementSaved = PaymentRepo.savePayment(payment);
        if (isPayementSaved) {
            connection.commit();
            return true;
        }
    }
    connection.rollback();
    return false;
} catch (SQLException e) {
    e.printStackTrace();
    connection.rollback();
    return false;
}finally {
    connection.setAutoCommit(true);
}
}

    }

