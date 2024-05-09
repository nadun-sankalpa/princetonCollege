package lk.ijse.princetoncollege.model.tableModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScheduleTm {
    private String ScheduleID;
    private String ModuleName;
    private String Date;
    private String Time;
    private String LecturerID;
}
