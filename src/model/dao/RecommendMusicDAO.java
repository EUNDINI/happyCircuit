package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Artist;
import model.Music;

public class RecommendMusicDAO {
	
	private JDBCUtil jdbcUtil = null;
	
	public RecommendMusicDAO() {
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
	
//	LikeMusic ���̺� ���ο� like ����
	public int create(String artistId, int musicId) throws SQLException {
		String sql = "INSERT INTO LikeMusic (likeId, artistId, musicId) VALUES (likeId_seq.nextval, ?, ?)";		
		Object[] param = new Object[] {artistId, musicId};				
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
	
	//LikeMusic ���̺��� ����
	public int remove(String artistId) throws SQLException {
		String sql = "DELETE FROM LikeMusic WHERE artistId=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {artistId});	// JDBCUtil�� delete���� �Ű� ���� ����

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
	
//	//�ش� music�� ���ƿ並 ���� artist ���
//	public List<Artist> findArtistListByMusicId(int musicId) throws SQLException {
//		String sql = "SELECT a.artistId, a.password, a.nickname, a.profile, a.image "
//					+ "FROM LikeMusic l JOIN Artist a ON l.artistId=a.artistId "
//					+ "WHERE musicId=?";
//		Object[] param = new Object[] {musicId};				
//		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
//
//		try {				
//			ResultSet rs = jdbcUtil.executeQuery();
//			List<Artist> artistList = new ArrayList<Artist>();
//			while (rs.next()) {
//				Artist artist = new Artist(rs.getString("artistId"), rs.getString("password"),
//						rs.getString("nickname"), rs.getString("profile"),
//						rs.getString("image"));
//				artistList.add(artist);
//			}
//			return artistList;
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			System.out.println("error111111");
//			jdbcUtil.rollback();
//		} finally {		
//			jdbcUtil.close();	// resource ��ȯ
//		}	
//		return null;
//	}
	
	//�ش� music�� ���ƿ並 ���� artistId ���
		public List<String> findArtistIdListByMusicId(int musicId) throws SQLException {
			String sql = "SELECT a.artistId "
						+ "FROM LikeMusic l JOIN Artist a ON l.artistId=a.artistId "
						+ "WHERE musicId=?";
			Object[] param = new Object[] {musicId};				
			jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����

			try {				
				ResultSet rs = jdbcUtil.executeQuery();
				List<String> artistIdList = new ArrayList<String>();
				while (rs.next()) {
					String artistId = rs.getString("artistId");
					artistIdList.add(artistId);
				}
				return artistIdList;
			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("error111111");
				jdbcUtil.rollback();
			} finally {		
				jdbcUtil.close();	// resource ��ȯ
			}	
			return null;
		}
	
	//�ش� artist�� ���ƿ並 ���� music ���
	public List<Music> findMusicListByArtistId(String artistId) throws SQLException {
		String sql = "SELECT m.musicId, NVL(originalMusicId,0) originalMusicId, NVL(priorMusicId,0) priorMusicId, m.artistId, m.musicName, m.genre, m.nth, m.musicPath "
					+ "FROM LikeMusic l JOIN Music m ON l.musicId=m.musicId "
					+ "WHERE (l.musicId, likeId) in (SELECT musicId, max(likeId) " 
												+ "FROM LikeMusic " 
												+ "WHERE artistId=? "
												+ "GROUP BY musicId) " 
												+ "ORDER BY likeId";
		Object[] param = new Object[] {artistId};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
	
		try {				
			ResultSet rs = jdbcUtil.executeQuery();
			List<Music> musicList = new ArrayList<Music>();
			while (rs.next()) {
				Music music = new Music(rs.getInt("musicId"), rs.getInt("originalMusicId"), rs.getInt("priorMusicId"),
						rs.getString("artistId"), rs.getString("musicName"), rs.getString("genre"), rs.getInt("nth"),
						rs.getString("musicPath"));
				musicList.add(music);
			}
			return musicList;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("error222222");
			jdbcUtil.rollback();
		} finally {		
			jdbcUtil.close();	// resource ��ȯ
		}	
		return null;
	}
	
	//�ش� artist�� ���ƿ並 ���� musicId ���
	public List<Integer> findMusicIdListByArtistId(String artistId) throws SQLException {
		String sql = "SELECT m.musicId "
					+ "FROM LikeMusic l JOIN Music m ON l.musicId=m.musicId "
					+ "WHERE l.artistId=?";
		Object[] param = new Object[] {artistId};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
	
		try {				
			ResultSet rs = jdbcUtil.executeQuery();
			List<Integer> musicIdList = new ArrayList<Integer>();
			while (rs.next()) {
				int musicId = rs.getInt("musicId");
				musicIdList.add(musicId);
			}
			return musicIdList;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("error222222");
			jdbcUtil.rollback();
		} finally {		
			jdbcUtil.close();	// resource ��ȯ
		}	
		return null;
	}
	
//	//�ش� artist�� create�� music�� ���
//	public List<Music> findCreateMusicListByArtistId(String artistId) throws SQLException {
//		String sql = "SELECT musicId, NVL(originalMusicId,0) originalMusicId, NVL(priorMusicId,0) priorMusicId, artistId, musicName, genre, nth, musicPath "
//					+ "FROM Music "
//					+ "WHERE artistId=?";
//		Object[] param = new Object[] {artistId};				
//		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
//	
//		try {				
//			ResultSet rs = jdbcUtil.executeQuery();
//			List<Music> musicList = new ArrayList<Music>();
//			while (rs.next()) {
//				Music music = new Music(rs.getInt("musicId"), rs.getInt("originalMusicId"), rs.getInt("priorMusicId"),
//						rs.getString("artistId"), rs.getString("musicName"), rs.getString("genre"), rs.getInt("nth"),
//						rs.getString("musicPath"));
//				musicList.add(music);
//			}
//			return musicList;
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			System.out.println("error333333");
//			jdbcUtil.rollback();
//		} finally {		
//			jdbcUtil.close();	// resource ��ȯ
//		}	
//		return null;
//	}
	
	//�ش� artist�� create�� musicId�� ���
		public List<Integer> findCreateMusicIdListByArtistId(String artistId) throws SQLException {
			String sql = "SELECT musicId "
						+ "FROM Music "
						+ "WHERE artistId=?";
			Object[] param = new Object[] {artistId};				
			jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
		
			try {				
				ResultSet rs = jdbcUtil.executeQuery();
				List<Integer> musicIdList = new ArrayList<Integer>();
				while (rs.next()) {
					int musicId = rs.getInt("musicId");
					musicIdList.add(musicId);
				}
				return musicIdList;
			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("error333333");
				jdbcUtil.rollback();
			} finally {		
				jdbcUtil.close();	// resource ��ȯ
			}	
			return null;
		}
	
	//�ش� artist�� ���� ���� ���ƿ並 ���� music�� ������
	public Music findTopLikeMusicByArtistId(String artistId) throws SQLException {
		String sql = "SELECT m.musicId, NVL(originalMusicId,0) originalMusicId, NVL(priorMusicId,0) priorMusicId, m.artistId, m.musicName, m.genre, m.nth, m.musicPath "
					+ "FROM (SELECT m.musicId, count(*) "
						  + "FROM LikeMusic l JOIN Music m ON l.musicId=m.musicId " 
						  + "WHERE l.artistId=? "
						  + "GROUP BY l.musicId " 
						  + "ORDER BY count(*) desc) l JOIN Music m ON l.musicId=m.musicId " 
					+ "WHERE ROWNUM <= 1";
		Object[] param = new Object[] {artistId};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
	
		try {				
			ResultSet rs = jdbcUtil.executeQuery();
			Music music = null;
			if (rs.next()) {
				music = new Music(rs.getInt("musicId"), rs.getInt("originalMusicId"), rs.getInt("priorMusicId"),
						rs.getString("artistId"), rs.getString("musicName"), rs.getString("genre"), rs.getInt("nth"),
						rs.getString("musicPath"));
			}
			return music;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("error444");
			jdbcUtil.rollback();
		} finally {		
			jdbcUtil.close();	// resource ��ȯ
		}	
		return null;
	}
	
//	//�ش� artist�� ���� ���� ���ƿ並 ���� music�� Id
//		public int findTopLikeMusicIdByArtistId(String artistId) throws SQLException {
//			String sql = "SELECT m.musicId "
//						+ "FROM (SELECT m.musicId, count(*) "
//							  + "FROM LikeMusic l JOIN Music m ON l.musicId=m.musicId " 
//							  + "WHERE l.artistId=? "
//							  + "GROUP BY l.musicId " 
//							  + "ORDER BY count(*) desc) l JOIN Music m ON l.musicId=m.musicId " 
//						+ "WHERE ROWNUM <= 1";
//			Object[] param = new Object[] {artistId};				
//			jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
//		
//			try {				
//				ResultSet rs = jdbcUtil.executeQuery();
//				int musicId = -1;
//				if (rs.next()) {
//					musicId = rs.getInt("musicId");
//				}
//				return musicId;
//			} catch (Exception ex) {
//				ex.printStackTrace();
//				System.out.println("error444");
//				jdbcUtil.rollback();
//			} finally {		
//				jdbcUtil.close();	// resource ��ȯ
//			}	
//			return -1;
//		}
	
	//�ش� music�� ���� ���� ���ƿ� ���� ��Ƽ��Ʈ
	public Artist findTopLikeArtistByMusicId(int musicId) throws SQLException {
		String sql = "SELECT a.artistId, a.password, a.nickname, a.profile, a.image "
					+ "FROM (SELECT a.artistId, count(*) "
						  + "FROM LikeMusic l JOIN Artist a ON l.artistId=a.artistId " 
						  + "WHERE l.musicId=? "
						  + "GROUP BY l.artistId " 
						  + "ORDER BY count(*) desc) l JOIN Artist a ON l.artistId=a.artistId " 
					+ "WHERE ROWNUM <= 1";
		Object[] param = new Object[] {musicId};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
	
		try {				
			ResultSet rs = jdbcUtil.executeQuery();
			Artist artist = null;
			if (rs.next()) {
				artist = new Artist(rs.getString("artistId"),
									rs.getString("password"),
									rs.getString("nickname"),
									rs.getString("profile"),
									rs.getString("image"));
			}
			return artist;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("error444");
			jdbcUtil.rollback();
		} finally {		
			jdbcUtil.close();	// resource ��ȯ
		}	
		return null;
	}
	
//	//�ش� music�� ���� ���� ���ƿ� ���� ��Ƽ��Ʈ�� id
//		public String findTopLikeArtistIdByMusicId(int musicId) throws SQLException {
//			String sql = "SELECT a.artistId "
//						+ "FROM (SELECT a.artistId, count(*) "
//							  + "FROM LikeMusic l JOIN Artist a ON l.artistId=a.artistId " 
//							  + "WHERE l.musicId=? "
//							  + "GROUP BY l.artistId " 
//							  + "ORDER BY count(*) desc) l JOIN Artist a ON l.artistId=a.artistId " 
//						+ "WHERE ROWNUM <= 1";
//			Object[] param = new Object[] {musicId};				
//			jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
//		
//			try {				
//				ResultSet rs = jdbcUtil.executeQuery();
//				String artistId = null;
//				if (rs.next()) {
//					artistId = rs.getString("artistId");
//				}
//				return artistId;
//			} catch (Exception ex) {
//				ex.printStackTrace();
//				System.out.println("error444");
//				jdbcUtil.rollback();
//			} finally {		
//				jdbcUtil.close();	// resource ��ȯ
//			}	
//			return null;
//		}
	
	public void close() {
		if (jdbcUtil != null) {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
	}
}
