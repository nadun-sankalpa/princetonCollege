package lk.ijse.princetoncollege.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Payment {
    String PaymentID;
    String Amount;
    String Date;
    String StudentID;
    String UserID;
    String CourseID;;
}
