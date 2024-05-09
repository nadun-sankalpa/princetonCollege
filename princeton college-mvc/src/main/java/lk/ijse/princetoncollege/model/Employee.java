package lk.ijse.princetoncollege.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {
        private String EmployeID;
        private String EmployeName;
        private String contactNumber;
        private String address;
        private String NicNumber;

}
