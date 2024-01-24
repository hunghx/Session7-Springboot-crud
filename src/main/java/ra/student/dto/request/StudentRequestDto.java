package ra.student.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentRequestDto {
//        @Min()
//        @Max()
        private Integer studentId;
        @NotBlank(message = "khong de trong") //chuoi khác ""
        @Size(min = 6,max = 20, message = "tu 6 đến 20 kí tự") // độ dài chuỗi
        private String studentName;
        @NotBlank
        @Pattern(regexp = "^[0-9]{10,11}$") // so khớp bieu thưc chinh quy
        private String phoneNumber;
        private Boolean sex;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @NotNull // không null
        private Date birthday;
        private MultipartFile file;
        @NotBlank
        private String address;

}
