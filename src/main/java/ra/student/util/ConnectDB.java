package ra.student.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Component
public class ConnectDB {
    @Value("${jdbc.mysql.driver}")
    public String DRIVER ;
    @Value("${jdbc.mysql.url}")
    public String URL;
    @Value("${jdbc.mysql.username}")
    public  String USER ;
    @Value("${jdbc.mysql.password}")
    public String PASSWORD;

    public Connection getConnection(){
        Connection conn = null;
        try {
            // khai báo Driver cho Class
            Class.forName(DRIVER);
            // thực hiện mở kết nói thông qua DriverManager
            conn = DriverManager.getConnection(URL,USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public void closeConnection(Connection conn){
        try {
            if(!conn.isClosed()){
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
