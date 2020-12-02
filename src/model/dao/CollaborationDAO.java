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
		jdbcUtil = new JDBCUtil(); // JDBCUtil ��ü ����
	}
	
	// Collaboration(���� ��û �Խñ�) ����
	public int create(Collaboration collaboration) throws SQLException {
		String sql = "INSERT INTO COLLABORATION VALUES (collaborationId_seq.nextval, ?, SYSDATE, ?, ?, ?)";		
		Object[] param = new Object[] { 
				collaboration.getCollaborationTitle(), 
				collaboration.getCollaborationContent(),
				collaboration.getPostId(), 
				collaboration.getPostArtistId()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
						
		String key[] = { "CollaborationId" };
		try {
			jdbcUtil.executeUpdate(key); // insert �� ����
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
			jdbcUtil.close(); // resource ��ȯ
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
	
	// Post�� �ڽ��� Collaboration�� postId�� �������� ã��
	public List<Collaboration> findCollaborationList(int postId) throws SQLException {
		String sql = "SELECT c.collaborationId, c.collaborationTitle, c.collaborationDate, c.collaborationContent, c.postId, c.artistId, p.artistId as pId "
    			+ "FROM Collaboration c, Post p "
    			+ "WHERE c.postId = p.postId "
    			+ "AND c.postId=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {postId});	
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
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
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	// �־��� collaborationId�� �ش��ϴ� collaboration�� �����ͺ��̽����� ã�� ������ Ŭ������ �����Ͽ� ��ȯ
	public Collaboration findCollaboration(int collaborationId) throws SQLException {
		String sql = "SELECT c.collaborationId, c.collaborationTitle, c.collaborationDate, c.collaborationContent, p.postId, p.artistId, a.artistId AS aId "
    			+ "FROM Collaboration c, Post p, Artist a "
    			+ "WHERE c.postId = p.postId "
    			+ "AND p.artistId = a.artistId " 
				+ "AND c.collaborationId=? ";          
		jdbcUtil.setSqlAndParameters(sql, new Object[] {collaborationId});	// JDBCUtil�� query���� �Ű� ���� ����
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			Collaboration collaboration = null;
			if (rs.next()) {						//  ���� �߰�
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
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
		
	}
	
}
