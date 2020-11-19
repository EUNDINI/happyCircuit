package model.dao;

import java.sql.SQLException;

import model.Collaboration;
import model.Post;

public class CollaborationDAO {
private JDBCUtil jdbcUtil = null;
	
	public CollaborationDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil ��ü ����
	}
	
	// Collaboration(���� ��û �Խñ�) ����
	public int create(Collaboration collaboration) throws SQLException {
		String sql = "INSERT INTO COLLABORATION VALUES (collaborationId_seq.nextval, ?, ?, ?, SYSDATE, ?)";		
		Object[] param = new Object[] { collaboration.getPostId(), 
				collaboration.getArtistId(), 
				collaboration.getCollaborationTitle(), 
				collaboration.getCollaborationDate(),
				collaboration.getCollaborationContent()};				
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
	public int update(Collaboration collaboration) throws SQLException {
		String sql = "UPDATE Collaboration "
					+ "SET collaborationTitle=?, collaborationContent=? "
					+ "WHERE collaborationId=? ";
		Object[] param = new Object[] { collaboration.getCollaborationTitle(), 
				collaboration.getCollaborationContent(),
				collaboration.getCollaborationId() };				
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
	public int remove(int collaborationId) throws SQLException {
		String sql = "DELETE FROM COLLABORATION WHERE collaborationId=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {collaborationId});	// JDBCUtil�� delete���� �Ű� ���� ����

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
