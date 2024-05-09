package lk.ijse.princetoncollege.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Attendance {
    private String AttendanceID;
    private String StudentName;
    private String Date;
    private String InTime;
    private String OutTime;
    private String UserId;
}
