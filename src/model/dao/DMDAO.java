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
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
	//DM 테이블에 새로운 DM 생성 및 Membership 생성
	public int createDMAndMembership(DM dm) throws SQLException {
		String sql = "INSERT INTO DM VALUES (dmId_seq.nextval)";		

		int result = 0;
		String key[] = {"dmId"};	// PK 컬럼의 이름     
		try {				
			result = jdbcUtil.executeUpdate();	// insert 문 실행
		   	ResultSet rs = jdbcUtil.getGeneratedKeys();
		   	if(rs.next()) {
		   		int generatedKey = rs.getInt(1);   // 생성된 PK 값
		   		dm.setDmId(generatedKey); 	// id필드에 저장  
		   	}
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		
		for (int i = 0; i < 2; i++) {
			sql = "INSERT INTO Membership VALUES (id.seq_nextval, ?, ?)";
			Object[] param = new Object[] {dm.getDmId(), dm.getArtistList().get(i).getArtistId()}; 
			jdbcUtil.setSqlAndParameters(sql, param);
			
			try {				
				result += jdbcUtil.executeUpdate();	// insert 문 실행
			} catch (Exception ex) {
				jdbcUtil.rollback();
				ex.printStackTrace();
			} finally {		
				jdbcUtil.commit();
				jdbcUtil.close();	// resource 반환
			}		
		}
		
		return result;		//정상적으로 처리되면 result == 3	
	}
	
	//어떤 artist가 어떤 DM을 삭제하면(채팅방 나가기 느낌) Membership에서 삭제
	//membership에 해당 dmid가 하나도 없으면 dm 테이블에서의 dmid도 삭제
	public int deleteMembership(String artistId, int dmId) throws SQLException {
		String sql1 = "DELETE FROM Membership WHERE artistId=? AND dmID=?";		
		Object[] param1 = new Object[] {artistId, dmId};				
		jdbcUtil.setSqlAndParameters(sql1, param1);	// JDBCUtil 에 insert문과 매개 변수 설정
		
		int result = 0;
		try {				
			result = jdbcUtil.executeUpdate();	
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}	
		
		String sql2 = "SELECT FROM Membership WHERE dmId=?";
		Object[] param2 = new Object[] {dmId};					
		jdbcUtil.setSqlAndParameters(sql2, param2);		

		try {				
			result = jdbcUtil.executeUpdate();	
			if (result == 0) { //membership에 해당 dmid가 하나도 없으면 dm 테이블에서의 dmid도 삭제
				jdbcUtil.commit();
				jdbcUtil.close();
				
				String sql3 = "DELETE FROM DM WHERE dmId=?";
				Object[] param3 = new Object[] {dmId};					
				jdbcUtil.setSqlAndParameters(sql3, param3);		
				
				result = jdbcUtil.executeUpdate();	
			}
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}	
		
		return result;
	}
	
	//DM에 속한 artist 목록
	public List<Artist> findArtistListFromMembership(int dmId) throws SQLException {
		String sql = "SELECT artistId, password, nickname, profile, image "
					+ "FROM Membership m JOIN Artist a ON m.artistId = a.artistId "
					+ "WHERE dmId=?";
		Object[] param = new Object[] {dmId};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정

		try {				
			ResultSet rs = jdbcUtil.executeQuery();
			List<Artist> artistList = new ArrayList<Artist>();
			while (rs.next()) {
				Artist artist = new Artist(
						rs.getString("artistId"), rs.getString("password"),
						rs.getString("nickname"), rs.getString("profile"),
						rs.getString("image"));
				artistList.add(artist);
				return artistList;
			}
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}	
		return null;
	}
	
	//해당 artist가 속한 DM 목록
	public List<DM> findDMListByArtistId(String artistId) throws SQLException {
		String sql = "SELECT dmId FROM Membership WHERE artistId=? ORDER BY id desc ";
		Object[] param = new Object[] {artistId};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정

		try {				
			ResultSet rs = jdbcUtil.executeQuery();
			List<DM> dmList = new ArrayList<DM>();
			while (rs.next()) {
				int dmId = rs.getInt("dmId");
				DM dm = new DM(dmId, findArtistListFromMembership(dmId));
				dmList.add(dm);
			}
			return dmList;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}	
		return null;
	}
	
	//Message 생성
	public int createMessage(Message msg) throws SQLException {
		String sql = "INSERT INTO Message VALUES (msgId_seq.nextval, ?, SYSDATE, ?, ?)";	
		Object[] param = new Object[] {msg.getMessage(),
						msg.getArtist().getArtistId(), msg.getDmId()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정

		int result = 0;
		String key[] = {"msgId"};
		try {				
			result = jdbcUtil.executeUpdate();	// insert 문 실행
		   	ResultSet rs = jdbcUtil.getGeneratedKeys();
		   	if(rs.next()) {
		   		int generatedKey = rs.getInt(1);   // 생성된 PK 값
		   		msg.setMsgId(generatedKey); 	// id필드에 저장  
		   	}
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}	
		return result;
	}
	 
	//해당 DM방의 Message 리스트
	public List<Message> findMessageList(int dmId) throws SQLException {
		String sql = "SELECT msgId, message, sentTime, artistId, password, nickname, profile, image "
					+ "FROM Message m JOIN DM d ON m.dmId = d.dmId "
					+ "JOIN Artist a ON m.artistId = a.artistId "
					+ "WHERE d.dmId=?";
		Object[] param = new Object[] {dmId};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정

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
				return msgList;
			}
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}	
		return null;
	}
	
	//마지막 Message
	public Message findLastMessage(int dmId) throws SQLException {
		String sql = "SELECT ROWNUM, msgId, message, sentTime, artistId, password, nickname, profile, image "
					+ "FROM (SELECT msgId, message, sentTime, artistId "
					+ "FROM Message "
					+ "WHERE dmId=? "
					+ "ORDER BY sentTime desc) m JOIN Artist a ON m.artistId = a.artistId "
					+ "WHERE ROWNUM <= 1";
		Object[] param = new Object[] {dmId};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
	
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
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}	
		return null;
	}
	
}
