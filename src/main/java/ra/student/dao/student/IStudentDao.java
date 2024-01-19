package ra.student.dao.student;

import ra.student.model.Student;

import java.util.List;

public interface IStudentDao {
    List<Student> findAll();
    Student findById(Integer id);
    boolean save(Student student);
    boolean deleteById(Integer id);
}
