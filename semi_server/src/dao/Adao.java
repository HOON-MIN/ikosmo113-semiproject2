package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import conn.GetConn;
import dto.Chat;

public class Adao {

	
	private static Adao dao;
	private Adao() {}
	
	public static Adao getDao() {
		
		if(dao == null) {
			dao= new Adao();			
		}
		return dao;
	}
	
	public void addChat(Chat c) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql= "insert into chat values(chat_seq.nextVal,?,?,sysdate)";
		try {
			con = GetConn.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, c.getGroupnum());
			pstmt.setString(2, c.getTalk());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}


}
