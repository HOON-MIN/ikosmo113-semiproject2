package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConn {

	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver On!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConn() throws SQLException {
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "semi0523";
		String pwd = "semi0523";
		return DriverManager.getConnection(url, user, pwd);

		
	}
	
}
