package ra.student.service.student;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.student.dao.student.IStudentDao;
import ra.student.dto.request.StudentRequestDto;
import ra.student.model.Student;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private  UploadService uploadService;
    @Autowired
    private IStudentDao studentDao;
    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public Student findById(Integer id) {
        return null;
    }

    @Override
    public void save(StudentRequestDto student) {
        String imageUrl = null;
        if (student.getFile().getSize()!=0){
          imageUrl= uploadService.uploadFileToServer(student.getFile());
        }
        // chuyển đổi tù dto -> model
        Student newStudent = modelMapper.map(student,Student.class);
        newStudent.setImageUrl(imageUrl);
        studentDao.save(newStudent);
    }

    @Override
    public void deleteById(Integer id) {

    }
}
