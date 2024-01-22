package ra.student.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity // định nghĩa 1 bảng trong db
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "student")
public class Student {
    @Id // khóa chính
    @Column(name = "student_id") // định nghĩa cot
    @GeneratedValue(strategy = GenerationType.IDENTITY) // tự động tăng
    private Integer studentId;
    @Column(name = "student_name",length = 100)
    private String studentName;
    @Column(name="phone_number", length = 15)
    private String phoneNumber;
    @Column(columnDefinition = "bit")
    private Boolean sex;
    @Column(columnDefinition = "date")
    private Date birthday;
    @Column(name="image_url")
    private String imageUrl;
    private String address;
    public void copy(Student stu){
        this.studentName=stu.getStudentName();
        this.address=stu.address;
        this.birthday = stu.birthday;
        this.imageUrl = stu.imageUrl;
        this.phoneNumber = stu.phoneNumber;
        this.sex = stu.sex;

    }
}
