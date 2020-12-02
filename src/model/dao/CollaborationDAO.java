package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Collaboration;
import model.Post;

public class CollaborationDAO {
private JDBCUtil jdbcUtil = null;
	
	public CollaborationDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
	}
	
	// Collaboration(협업 신청 게시글) 생성
	public int create(Collaboration collaboration) throws SQLException {
		String sql = "INSERT INTO COLLABORATION VALUES (collaborationId_seq.nextval, ?, SYSDATE, ?, ?, ?)";		
		Object[] param = new Object[] { 
				collaboration.getCollaborationTitle(), 
				collaboration.getCollaborationContent(),
				collaboration.getPostId(), 
				collaboration.getPostArtistId()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
						
		String key[] = { "CollaborationId" };
		try {
			jdbcUtil.executeUpdate(key); // insert 문 실행
			ResultSet rs = jdbcUtil.getGeneratedKeys();

			if (rs.next()) {
				int generatedKey = rs.getInt(1);
				return generatedKey;
			}
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 반환
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
	
	// Post의 자식인 Collaboration을 postId를 기준으로 찾기
	public List<Collaboration> findCollaborationList(int postId) throws SQLException {
		String sql = "SELECT c.collaborationId, c.collaborationTitle, c.collaborationDate, c.collaborationContent, c.postId, c.artistId, p.artistId as pId "
    			+ "FROM Collaboration c, Post p "
    			+ "WHERE c.postId = p.postId "
    			+ "AND c.postId=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {postId});	
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<Collaboration> CollaborationList = new ArrayList<Collaboration>();	
			while (rs.next()) {
				Collaboration collaboration = new Collaboration(		
						rs.getInt("collaborationId"),
						rs.getString("collaborationTitle"),
						rs.getDate("collaborationDate"),
						rs.getString("collaborationContent"),
						rs.getInt("postId"),
						rs.getString("artistId"),
						rs.getString("pId"));
				CollaborationList.add(collaboration);
			}		
			return CollaborationList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	// 주어진 collaborationId에 해당하는 collaboration를 데이터베이스에서 찾아 도메인 클래스에 저장하여 반환
	public Collaboration findCollaboration(int collaborationId) throws SQLException {
		String sql = "SELECT c.collaborationId, c.collaborationTitle, c.collaborationDate, c.collaborationContent, p.postId, p.artistId, a.artistId AS aId "
    			+ "FROM Collaboration c, Post p, Artist a "
    			+ "WHERE c.postId = p.postId "
    			+ "AND p.artistId = a.artistId " 
				+ "AND c.collaborationId=? ";          
		jdbcUtil.setSqlAndParameters(sql, new Object[] {collaborationId});	// JDBCUtil에 query문과 매개 변수 설정
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			Collaboration collaboration = null;
			if (rs.next()) {						//  정보 발견
				collaboration = new Collaboration(		
						rs.getInt("collaborationId"),
						rs.getString("collaborationTitle"),
						rs.getDate("collaborationDate"),
						rs.getString("collaborationContent"),
						rs.getInt("postId"),
						rs.getString("artistId"),
						rs.getString("aId"));
			}
			return collaboration;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
		
	}
	
}
