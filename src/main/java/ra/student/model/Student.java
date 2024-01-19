package ra.student.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {
    private Integer studentId;
    private String studentName;
    private String phoneNumber;
    private Boolean sex;
    private Date birthday;
    private String imageUrl;
    private String address;
}
