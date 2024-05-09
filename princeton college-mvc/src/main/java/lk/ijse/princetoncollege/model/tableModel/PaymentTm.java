package lk.ijse.princetoncollege.model.tableModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentTm {
    String PaymentID;
    String Amount;
    String Date;
    String StudentID;
    String UserID;
    String CourseID;
}
