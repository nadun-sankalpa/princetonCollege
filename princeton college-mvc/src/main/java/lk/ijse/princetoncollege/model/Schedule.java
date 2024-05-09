package lk.ijse.princetoncollege.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Schedule {
    private String ScheduleID;
    private String ModuleName;
    private String Date;
    private String Time;
    private String LecturerID;
}
