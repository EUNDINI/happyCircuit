package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Artist;
import model.DM;
import model.Message;

public class DMDAO {
	private JDBCUtil jdbcUtil = null;
	
	public DMDAO() {
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
	
	//DM ���̺� ���ο� DM ���� �� Membership ����
	public int createDMAndMembership(DM dm) throws SQLException {
		String sql = "INSERT INTO DM VALUES (DMId_seq.nextval)";	
		Object[] param = new Object[] {};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����	

		int result = 0;
		String key[] = {"dmId"};
		try {				
			result = jdbcUtil.executeUpdate(key);	// insert �� ����
		   	ResultSet rs = jdbcUtil.getGeneratedKeys();
		   	if(rs.next()) {
		   		int generatedKey = rs.getInt(1);   // ������ PK ��
		   		dm.setDmId(generatedKey); 	// id�ʵ忡 ����  
		   	}
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		
		for (int i = 0; i < 2; i++) {
			sql = "INSERT INTO Membership (id, dmId, artistId) VALUES (membershipId_seq.nextval, ?, ?)";
			param = new Object[] {dm.getDmId(), dm.getArtistList().get(i).getArtistId()}; 
			jdbcUtil.setSqlAndParameters(sql, param);
			
			try {				
				result += jdbcUtil.executeUpdate();	// insert �� ����
			} catch (Exception ex) {
				jdbcUtil.rollback();
				ex.printStackTrace();
			} finally {		
				jdbcUtil.commit();
				jdbcUtil.close();	// resource ��ȯ
			}		
		}
		
		return result;		//���������� ó���Ǹ� result == 3	
	}
	
	//�ش� artist���� DM���� �ִ��� ��, Membership�� �ִ���
	public int findMembership(List<Artist> artistList) throws SQLException {
		String sql = "SELECT m2.artistId, m2.dmId "
					+ "FROM Membership m1 JOIN Membership m2 ON m1.dmId=m2.dmId "
					+ "WHERE m1.artistId=? AND m2.artistId!=?";
		Object[] param = new Object[] {artistList.get(0).getArtistId(), artistList.get(0).getArtistId()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
	
		try {				
			ResultSet rs = jdbcUtil.executeQuery();
			while (rs.next()) {
				String artistId = rs.getString("artistId");
				if (artistId.equals(artistList.get(1).getArtistId())) {
					int dmId = rs.getInt("dmId");
					return dmId;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			jdbcUtil.rollback();
		} finally {		
			jdbcUtil.close();	// resource ��ȯ
		}	
		return 0;
	}
	
	//� artist�� � DM�� �����ϸ�(ä�ù� ������ ����) Membership���� ����
	//membership�� �ش� dmid�� �ϳ��� ������ dm ���̺����� dmid�� ����
	public int deleteMembership(String artistId, int dmId) throws SQLException {
		String sql = "DELETE FROM Membership WHERE artistId=? AND dmID=?";		
		Object[] param = new Object[] {artistId, dmId};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
		
		int result = 0;
		try {				
			result = jdbcUtil.executeUpdate();	
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}	
		
		sql = "SELECT FROM Membership WHERE dmId=?";
		param = new Object[] {dmId};					
		jdbcUtil.setSqlAndParameters(sql, param);		

		try {				
			result = jdbcUtil.executeUpdate();	
			if (result == 0) { //membership�� �ش� dmid�� �ϳ��� ������ dm ���̺����� dmid�� ����
				sql = "DELETE FROM DM WHERE dmId=?";
				param = new Object[] {dmId};					
				jdbcUtil.setSqlAndParameters(sql, param);		
				
				result = jdbcUtil.executeUpdate();	
			}
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.close();	// resource ��ȯ
		}	
		
		return result;
	}
	
	//DM�� ���� artist ���
	public List<Artist> findArtistListFromMembership(int dmId) throws SQLException {
		String sql = "SELECT a.artistId, password, nickname, profile, image "
					+ "FROM Membership m JOIN Artist a ON m.artistId = a.artistId "
					+ "WHERE dmId=?";
		Object[] param = new Object[] {dmId};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����

		try {				
			ResultSet rs = jdbcUtil.executeQuery();
			List<Artist> artistList = new ArrayList<Artist>();
			while (rs.next()) {
				Artist artist = new Artist(
						rs.getString("artistId"), rs.getString("password"),
						rs.getString("nickname"), rs.getString("profile"),
						rs.getString("image"));
				artistList.add(artist);
			}
			return artistList;
		} catch (Exception ex) {
			System.out.println("error111111");
			ex.printStackTrace();
			jdbcUtil.rollback();
		} finally {		
			jdbcUtil.close();	// resource ��ȯ
		}	
		return null;
	}
	
	//�ش� artist�� ���� DM ���
	public List<DM> findDMListByArtistId(String artistId) throws SQLException {
		String sql = "SELECT dmId FROM Membership WHERE artistId=? ORDER BY id desc ";
		Object[] param = new Object[] {artistId};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����

		try {		
			ResultSet rs = jdbcUtil.executeQuery();
			List<DM> dmList = new ArrayList<DM>();
			while (rs.next()) {
				int dmId = rs.getInt("dmId");
				DM dm = new DM(dmId, null);
				dmList.add(dm);	
			}
			return dmList;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.close();	// resource ��ȯ
		}	
		return null;
	}
	
	//Message ����
	public int createMessage(Message msg) throws SQLException {
		String sql = "INSERT INTO Message VALUES (messageId_seq.nextval, ?, SYSDATE, ?, ?)";	
		Object[] param = new Object[] {msg.getMessage(),
						msg.getArtist().getArtistId(), msg.getDmId()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����

		int result = 0;
		String key[] = {"msgId"};
		try {				
			result = jdbcUtil.executeUpdate(key);	// insert �� ����
		   	ResultSet rs = jdbcUtil.getGeneratedKeys();
		   	if(rs.next()) {
		   		int generatedKey = rs.getInt(1);   // ������ PK ��
		   		msg.setMsgId(generatedKey); 	// id�ʵ忡 ����  
		   	}
		} catch (Exception ex) {
			ex.printStackTrace();
			jdbcUtil.rollback();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}	
		return result;
	}
	 
	//�ش� DM���� Message ����Ʈ
	public List<Message> findMessageList(int dmId) throws SQLException {
		String sql = "SELECT a.artistId, password, nickname, profile, image, msgId, message, sentTime "
					+ "FROM Message m JOIN DM d ON m.dmId = d.dmId "
					+ "JOIN Artist a ON m.artistId = a.artistId "
					+ "WHERE d.dmId=? "
					+ "ORDER BY sentTime";
		Object[] param = new Object[] {dmId};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����

		try {			
			ResultSet rs = jdbcUtil.executeQuery();
			List<Message> msgList = new ArrayList<Message>();	
			while (rs.next()) {
				Artist artist = new Artist(rs.getString("artistId"),
								rs.getString("password"), 
								rs.getString("nickname"),
								rs.getString("profile"), 
								rs.getString("image"));
				Message msg = new Message(rs.getInt("msgId"),
							rs.getString("message"), 
							new Date(rs.getDate("sentTime").getTime()),
							artist, dmId);
				msgList.add(msg);
			}
			return msgList;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.close();	// resource ��ȯ
		}	
		return null;
	}
	
	//������ Message
	public Message findLastMessage(int dmId) throws SQLException {
		String sql = "SELECT ROWNUM, m.artistId, password, nickname, profile, image, msgId, message, sentTime "
					+ "FROM (SELECT msgId, message, sentTime, artistId "
					+ "FROM Message "
					+ "WHERE dmId=? "
					+ "ORDER BY sentTime desc) m JOIN Artist a ON m.artistId = a.artistId "
					+ "WHERE ROWNUM <= 1";
		Object[] param = new Object[] {dmId};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
	
		try {			
			ResultSet rs = jdbcUtil.executeQuery();
			if(rs.next()) {
				Artist artist = new Artist(rs.getString("artistId"),
								rs.getString("password"), 
								rs.getString("nickname"),
								rs.getString("profile"), 
								rs.getString("image"));
				Message msg = new Message(rs.getInt("msgId"),
							rs.getString("message"), 
							new Date(rs.getDate("sentTime").getTime()),
							artist, dmId);
				return msg;
			}
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.close();	// resource ��ȯ
		}	
		return null;
	}
	
}
