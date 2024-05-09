package lk.ijse.princetoncollege.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Course {
    private String CourseID;
    private String CourseName;
    private String Duration;
    private String MainLecturer;
    private String CourseFee;
}
