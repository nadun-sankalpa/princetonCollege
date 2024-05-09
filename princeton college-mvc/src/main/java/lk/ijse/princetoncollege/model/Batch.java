package lk.ijse.princetoncollege.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Batch {
    private String BatchID;
    private String BatchName;
    private String NoOfStudents;
    private String NoOfLecturers;
    private String MainLecturer;
    private String BatchReprsenter;
}
