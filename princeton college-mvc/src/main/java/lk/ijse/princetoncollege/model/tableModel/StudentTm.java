package lk.ijse.princetoncollege.model.tableModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class StudentTm {
    private String studentID;
    private String fullName;
    private String address;
    private String contactNumber;
    private String nicNumber;
    private String userId;
}
