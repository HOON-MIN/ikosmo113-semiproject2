package conn;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConn {
    
           static{
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("����̹� �ε� ����");
        } catch (ClassNotFoundException ex) {
            System.out.println("�ش� Ŭ���� �ε��� �����߽��ϴ�.");
        }
    }
    public static Connection getConn() throws SQLException{
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "semi0523";
        String password = "semi0523";
        return DriverManager.getConnection(url, user,password);
    }
    
    public static void main(String[] args) throws SQLException {
        Connection con = TestConn.getConn();
        System.out.println("Test : "+con);
    }
        
    }
