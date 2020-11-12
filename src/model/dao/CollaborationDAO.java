package model.dao;

import java.sql.SQLException;

import model.Post;

public class CollaborationDAO {
private JDBCUtil jdbcUtil = null;
	
	public CollaborationDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
	}
	
	// Collaboration(협업 신청 게시글) 생성
	public int create(Post post) throws SQLException {
		String sql = "INSERT INTO USERINFO VALUES (postId_seq.nextval, ?, SYSDATE, 0, ?, ?, ?, ?)";		
		Object[] param = new Object[] { post.getPostTitle(), 
						post.getPostContent(),
						post.getPostAttachment(), 
						post.getPostCategoryId(), 
						post.getArtistId() };				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;			
	}
	
	// Collaboration(협업 신청 게시글) 수정
	public int update(Post post) throws SQLException {
		String sql = "UPDATE Post "
					+ "SET postTitle=?, postContent=?, postAttachment=?, postCategory=? "
					+ "WHERE postId=? ";
		Object[] param = new Object[] { post.getPostTitle(), 
				post.getPostContent(),
				post.getPostAttachment(), 
				post.getPostCategoryId(),
				post.getPostId() };				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil에 update문과 매개 변수 설정
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}
	
	// Collaboration(협업 신청 게시글) 삭제
	public int remove(int postId) throws SQLException {
		String sql = "DELETE FROM Post WHERE postId=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {postId});	// JDBCUtil에 delete문과 매개 변수 설정

		try {				
			int result = jdbcUtil.executeUpdate();	// delete 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}
}
