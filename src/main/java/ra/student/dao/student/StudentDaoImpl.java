package ra.student.dao.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.student.model.Student;
import ra.student.util.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class StudentDaoImpl implements IStudentDao{
    @Autowired
    private ConnectDB connectDB;
    @Override
    public List<Student> findAll() {
        List<Student> list = new ArrayList<>();
        Connection conn = connectDB.getConnection();
        try {
            CallableStatement callSt = conn.prepareCall("select * from student");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){
                Student student =new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setStudentName(rs.getString("student_name"));
                student.setPhoneNumber(rs.getString("phone_number"));
                student.setSex(rs.getBoolean("sex"));
                student.setBirthday(rs.getDate("birthday"));
                student.setAddress(rs.getString("address"));
                student.setImageUrl(rs.getString("image_url"));
                list.add(student);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connectDB.closeConnection(conn);
        }

    }

    @Override
    public Student findById(Integer id) {
        Student student = null;
        Connection conn = connectDB.getConnection();
        try {
            CallableStatement callSt = conn.prepareCall("select * from student where student_id=?");
            callSt.setInt(1,id);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()){
                student =new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setStudentName(rs.getString("student_name"));
                student.setPhoneNumber(rs.getString("phone_number"));
                student.setSex(rs.getBoolean("sex"));
                student.setBirthday(rs.getDate("birthday"));
                student.setAddress(rs.getString("address"));
                student.setImageUrl(rs.getString("image_url"));
            }
            return student;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connectDB.closeConnection(conn);
        }
    }

    @Override
    public boolean save(Student student) {
        Connection conn = connectDB.getConnection();
        CallableStatement callSt = null;
        try {
            if (student.getStudentId()==null){
                // thêm mới
                callSt = conn.prepareCall("insert into student(student_name, phone_number, sex, birthday, address, image_url) value (?,?,?,?,?,?)");
                callSt.setString(1,student.getStudentName());
                callSt.setString(2,student.getPhoneNumber());
                callSt.setBoolean(3,student.getSex());
                callSt.setDate(4,new Date(student.getBirthday().getTime())); // chú ý chuển đổi
                callSt.setString(5,student.getAddress());
                callSt.setString(6,student.getImageUrl());
            }else {
                // cập nhật
                callSt = conn.prepareCall("update  student set student_name=?, phone_number=?, sex=?, birthday=?, address=?, image_url=? where student_id =?");
                callSt.setString(1,student.getStudentName());
                callSt.setString(2,student.getPhoneNumber());
                callSt.setBoolean(3,student.getSex());
                callSt.setDate(4,new Date(student.getBirthday().getTime())); // chú ý chuển đổi
                callSt.setString(5,student.getAddress());
                callSt.setString(6,student.getImageUrl());
                callSt.setInt(7,student.getStudentId());
            }
            int count =callSt.executeUpdate();
            return count>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connectDB.closeConnection(conn);
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        Connection conn = connectDB.getConnection();
        try {
            CallableStatement callSt = conn.prepareCall("DELETE  from student where student_id=?");
            callSt.setInt(1,id);
            int count = callSt.executeUpdate();
            return count>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connectDB.closeConnection(conn);
        }
    }
}
