package ra.student.service.student;

import ra.student.dto.request.StudentRequestDto;
import ra.student.model.Student;

import java.util.List;

public interface IStudentService {
    List<Student> findAll();
    Student findById(Integer id);
    void save(StudentRequestDto student);
    void deleteById(Integer id);
}
