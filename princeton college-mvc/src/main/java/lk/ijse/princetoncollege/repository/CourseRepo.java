package lk.ijse.princetoncollege.repository;

import lk.ijse.princetoncollege.db.DbConnection;
import lk.ijse.princetoncollege.model.Course;
import lk.ijse.princetoncollege.model.Exam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseRepo {
    public static List<Course> getAll() {
        List<Course> courseList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Establish database connection (replace the connection details with your own)
            connection = DbConnection.getInstance().getConnection();
            // SQL query to select all students
            String sql = "SELECT * FROM course";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            // Iterate through the result set and create Student objects
            while (resultSet.next()) {
                Course course = new Course(
                        resultSet.getString("course_id"),
                        resultSet.getString("course_name"),
                        resultSet.getString("duration"),
                        resultSet.getString("main_lecturer"),
                        resultSet.getString("course_fee")
                );
                courseList.add(course);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);


        }
        return courseList;
    }

    public static boolean update(Course course) throws SQLException {
        String sql = "UPDATE course SET course_name = ?, duration = ?, main_lecturer = ?, course_fee = ?  WHERE course_id = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1,course.getCourseName());
        pstm.setObject(2,course.getDuration());
        pstm.setObject(3,course.getMainLecturer());
        pstm.setObject(4,course.getCourseFee());
        pstm.setObject(5,course.getCourseID());

        return pstm.executeUpdate() > 0;
    }
}
