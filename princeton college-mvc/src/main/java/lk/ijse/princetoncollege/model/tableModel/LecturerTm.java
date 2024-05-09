package lk.ijse.princetoncollege.model.tableModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LecturerTm {
    private String LecturerID;
    private String LecturerName;
    private String contactNumber;
    private String Address;
    private String NicNumber;
}
