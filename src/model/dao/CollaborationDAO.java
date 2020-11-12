package model.dao;

import java.sql.SQLException;

import model.Post;

public class CollaborationDAO {
private JDBCUtil jdbcUtil = null;
	
	public CollaborationDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil ��ü ����
	}
	
	// Collaboration(���� ��û �Խñ�) ����
	public int create(Post post) throws SQLException {
		String sql = "INSERT INTO USERINFO VALUES (postId_seq.nextval, ?, SYSDATE, 0, ?, ?, ?, ?)";		
		Object[] param = new Object[] { post.getPostTitle(), 
						post.getPostContent(),
						post.getPostAttachment(), 
						post.getPostCategoryId(), 
						post.getArtistId() };				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;			
	}
	
	// Collaboration(���� ��û �Խñ�) ����
	public int update(Post post) throws SQLException {
		String sql = "UPDATE Post "
					+ "SET postTitle=?, postContent=?, postAttachment=?, postCategory=? "
					+ "WHERE postId=? ";
		Object[] param = new Object[] { post.getPostTitle(), 
				post.getPostContent(),
				post.getPostAttachment(), 
				post.getPostCategoryId(),
				post.getPostId() };				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil�� update���� �Ű� ���� ����
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}
	
	// Collaboration(���� ��û �Խñ�) ����
	public int remove(int postId) throws SQLException {
		String sql = "DELETE FROM Post WHERE postId=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {postId});	// JDBCUtil�� delete���� �Ű� ���� ����

		try {				
			int result = jdbcUtil.executeUpdate();	// delete �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}
}
