package lk.ijse.princetoncollege.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Student {
    private String studentID;
    private String fullName;
    private String address;
    private String contactNumber;
    private String nicNumber;
    private String userId;

}
