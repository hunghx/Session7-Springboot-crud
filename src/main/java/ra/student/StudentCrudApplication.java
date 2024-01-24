package ra.student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.sql.DriverManager;
import java.util.Properties;

@SpringBootApplication
public class StudentCrudApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentCrudApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Bean // quản lí các dối tuợng đọc từ db
    public SessionFactory sessionFactory(){
        return new org.hibernate.cfg.Configuration().configure("hibernate-config.xml")
                .buildSessionFactory();
    }
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
//            LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//            em.setDataSource(dataSource());
//            em.setPackagesToScan("ra.student.model");
//
//        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaProperties(additionalProperties());
//        em.setJpaVendorAdapter(jpaVendorAdapter);
//        return em;
//    }
//    public Properties additionalProperties() {
//        Properties properties = new Properties();
//        properties.setProperty("show_sql","true");
//        properties.setProperty("hibernate.hbm2ddl.auto", "update");
////        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
//        return properties;
//    }
    @Bean // cung cấp các phương thức làm việc
    public EntityManager entityManager(){
        return sessionFactory().createEntityManager();
    }

//    @Bean
//    public DataSource dataSource(){
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/studentcrud?createDatabaseIfNotExist=true");
//        dataSource.setUsername("root");
//        dataSource.setPassword("hung18061999");
//        return dataSource;
//    }
}
