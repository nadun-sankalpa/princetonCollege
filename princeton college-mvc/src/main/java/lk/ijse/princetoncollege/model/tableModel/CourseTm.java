package lk.ijse.princetoncollege.model.tableModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseTm {
    private String CourseID;
    private String CourseName;
    private String Duration;
    private String MainLecturer;
    private String CourseFee;
}
