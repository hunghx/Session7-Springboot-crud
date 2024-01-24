package ra.student.dao.student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.student.model.Student;

import java.util.List;

@Repository
public class StudentDaoImpl implements IStudentDao{
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private EntityManager entityManager;
    @Override
    public List<Student> findAll() {
        // sử dụng ngôn ngu HQL
        TypedQuery<Student> query = entityManager.createQuery("select S from Student S", Student.class);
        return query.getResultList();
    }

    @Override
    public Student findById(Integer id) {
        TypedQuery<Student> query = entityManager.createQuery("select S from Student S where S.studentId = :idfind", Student.class);
        // truyền dối số vào
        query.setParameter("idfind",id);
        return query.getSingleResult();
    }

    @Override
    public boolean save(Student student) {
        // tạo 1 session
        Session session = sessionFactory.openSession();
        Transaction transaction= session.beginTransaction(); // bắt đầu giao dich
//        try{
//            session.saveOrUpdate(student);
//            transaction.commit(); // xác nhận cam kết để cap nhật trong db
//            return true;
//        }catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }finally {
//            if (session!=null){
//                session.close();
//            }
//        }
        if (student.getStudentId()==null){
            // thêm mới
            session.saveOrUpdate(student);
        }else {
//            cập nhật
            Student old = findById(student.getStudentId()); // lấy ra đối tươg cũ
            old.copy(student); // thay đổi giá trị
            session.saveOrUpdate(old);
        }
        transaction.commit();
        session.close();
        return true;
    }

    @Override
//    @Transactional // đánh dấu phương thức này quoc quản lý bởi giao dịch
    public boolean deleteById(Integer id) {
        // tạo 1 session
        Session session = sessionFactory.openSession();
        Transaction transaction= session.beginTransaction(); // bắt đầu giao dich
//        try{
//            session.delete(findById(id));
//            transaction.commit(); // xác nhận cam kết để cap nhật trong db
//            return true;
//        }catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }finally {
//            if (session!=null){
//                session.close();
//            }
//        }

        Student student = findById(id);
        session.remove(student);
        transaction.commit();
        session.close();
        return true;
    }
}
