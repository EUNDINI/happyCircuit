package model.dao;

import java.sql.SQLException;

import model.Collaboration;
import model.Post;

public class CollaborationDAO {
private JDBCUtil jdbcUtil = null;
	
	public CollaborationDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
	}
	
	// Collaboration(협업 신청 게시글) 생성
	public int create(Collaboration collaboration) throws SQLException {
		String sql = "INSERT INTO COLLABORATION VALUES (collaborationId_seq.nextval, ?, ?, ?, SYSDATE, ?)";		
		Object[] param = new Object[] { collaboration.getPostId(), 
				collaboration.getArtistId(), 
				collaboration.getCollaborationTitle(), 
				collaboration.getCollaborationDate(),
				collaboration.getCollaborationContent()};				
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
	public int update(Collaboration collaboration) throws SQLException {
		String sql = "UPDATE Collaboration "
					+ "SET collaborationTitle=?, collaborationContent=? "
					+ "WHERE collaborationId=? ";
		Object[] param = new Object[] { collaboration.getCollaborationTitle(), 
				collaboration.getCollaborationContent(),
				collaboration.getCollaborationId() };				
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
	public int remove(int collaborationId) throws SQLException {
		String sql = "DELETE FROM COLLABORATION WHERE collaborationId=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {collaborationId});	// JDBCUtil에 delete문과 매개 변수 설정

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
