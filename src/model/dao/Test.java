package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Post;

public class Test {
	
	public Test() {	// ������ 
		// JDBC ����̹� �ε� �� ���
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}	
	}
	
	private static Connection getConnection() {
		String url = // "jdbc:oracle:thin:@localhost:1521:xe";
					"jdbc:oracle:thin:@202.20.119.117:1521:orcl";	
		String user = "dbprog0207";
		String passwd = "dbprog0207";

		// DBMS���� ���� ȹ��
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, passwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}	 
		return conn;
	}
	
	
	public static void findPostList() {
		Connection conn = null;
		PreparedStatement pStmt = null;			// PreparedStatment ���� ���� ����
		ResultSet rs = null;
		
		String query = "SELECT p.postId, p.postTitle, p.postDate, p.postView, p.postContent, p.postAttachment, pc.postCategoryId, a.artistId "
    			+ "FROM Post p, PostCategory pc, Artist a "
    			+ "WHERE p.postCategoryId = pc.postCategoryId "
    			+ "AND p.artistId = a.artistId ";  
		
		try {
			conn = getConnection();
			pStmt = conn.prepareStatement(query);
			rs = pStmt.executeQuery();
			while (rs.next()) { // ����� ���ƾ� 1���� ���� �׳� while ��� if
				int postId = rs.getInt("postId");
				String postTitle = rs.getString("postTitle");
				Date postDate = rs.getDate("postDate");
				int postView = rs.getInt("postView");
				String postContent = rs.getString("postContent");
				String postAttachment = rs.getString("postAttachment");
				int postCategoryId = rs.getInt("postCategoryId");
				String artistId = rs.getString("artistId");
				
				System.out.println(postId + "  " + postTitle + "  " + postDate + "  " + postView);
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		} finally {		// �ڿ� �ݳ�
			if (rs != null) 
				try { 
					rs.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (pStmt != null) 
				try { 
					pStmt.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (conn != null) 
				try { 
					conn.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
		}	

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("����");
		findPostList();

	}

}
