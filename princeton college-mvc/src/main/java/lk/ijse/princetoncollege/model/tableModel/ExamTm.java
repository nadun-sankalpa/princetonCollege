package lk.ijse.princetoncollege.model.tableModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExamTm {
    private String ExamID;
    private String ExamName;
    private String Date;
    private String Time;
    private String LecturerID;
}
