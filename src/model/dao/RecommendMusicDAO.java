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
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
//	LikeMusic 테이블에 새로운 like 생성
	public int create(String artistId, int musicId) throws SQLException {
		String sql = "INSERT INTO LikeMusic (likeId, artistId, musicId) VALUES (likeId_seq.nextval, ?, ?)";		
		Object[] param = new Object[] {artistId, musicId};				
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
	
	//LikeMusic 테이블에서 삭제
	public int remove(String artistId) throws SQLException {
		String sql = "DELETE FROM LikeMusic WHERE artistId=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {artistId});	// JDBCUtil에 delete문과 매개 변수 설정

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
	
//	//해당 music에 좋아요를 누른 artist 목록
//	public List<Artist> findArtistListByMusicId(int musicId) throws SQLException {
//		String sql = "SELECT a.artistId, a.password, a.nickname, a.profile, a.image "
//					+ "FROM LikeMusic l JOIN Artist a ON l.artistId=a.artistId "
//					+ "WHERE musicId=?";
//		Object[] param = new Object[] {musicId};				
//		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
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
//			jdbcUtil.close();	// resource 반환
//		}	
//		return null;
//	}
	
	//해당 music에 좋아요를 누른 artistId 목록
		public List<String> findArtistIdListByMusicId(int musicId) throws SQLException {
			String sql = "SELECT a.artistId "
						+ "FROM LikeMusic l JOIN Artist a ON l.artistId=a.artistId "
						+ "WHERE musicId=?";
			Object[] param = new Object[] {musicId};				
			jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정

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
				jdbcUtil.close();	// resource 반환
			}	
			return null;
		}
	
	//해당 artist가 좋아요를 누른 music 목록
	public List<Music> findMusicListByArtistId(String artistId) throws SQLException {
		String sql = "SELECT m.musicId, NVL(originalMusicId,0) originalMusicId, NVL(priorMusicId,0) priorMusicId, m.artistId, m.musicName, m.genre, m.nth, m.musicPath "
					+ "FROM LikeMusic l JOIN Music m ON l.musicId=m.musicId "
					+ "WHERE (l.musicId, likeId) in (SELECT musicId, max(likeId) " 
												+ "FROM LikeMusic " 
												+ "WHERE artistId=? "
												+ "GROUP BY musicId) " 
												+ "ORDER BY likeId";
		Object[] param = new Object[] {artistId};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
	
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
			jdbcUtil.close();	// resource 반환
		}	
		return null;
	}
	
	//해당 artist가 좋아요를 누른 musicId 목록
	public List<Integer> findMusicIdListByArtistId(String artistId) throws SQLException {
		String sql = "SELECT m.musicId "
					+ "FROM LikeMusic l JOIN Music m ON l.musicId=m.musicId "
					+ "WHERE l.artistId=?";
		Object[] param = new Object[] {artistId};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
	
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
			jdbcUtil.close();	// resource 반환
		}	
		return null;
	}
	
//	//해당 artist가 create한 music의 목록
//	public List<Music> findCreateMusicListByArtistId(String artistId) throws SQLException {
//		String sql = "SELECT musicId, NVL(originalMusicId,0) originalMusicId, NVL(priorMusicId,0) priorMusicId, artistId, musicName, genre, nth, musicPath "
//					+ "FROM Music "
//					+ "WHERE artistId=?";
//		Object[] param = new Object[] {artistId};				
//		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
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
//			jdbcUtil.close();	// resource 반환
//		}	
//		return null;
//	}
	
	//해당 artist가 create한 musicId의 목록
		public List<Integer> findCreateMusicIdListByArtistId(String artistId) throws SQLException {
			String sql = "SELECT musicId "
						+ "FROM Music "
						+ "WHERE artistId=?";
			Object[] param = new Object[] {artistId};				
			jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
		
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
				jdbcUtil.close();	// resource 반환
			}	
			return null;
		}
	
	//해당 artist가 가장 많이 좋아요를 누른 music을 가져옴
	public Music findTopLikeMusicByArtistId(String artistId) throws SQLException {
		String sql = "SELECT m.musicId, NVL(originalMusicId,0) originalMusicId, NVL(priorMusicId,0) priorMusicId, m.artistId, m.musicName, m.genre, m.nth, m.musicPath "
					+ "FROM (SELECT m.musicId, count(*) "
						  + "FROM LikeMusic l JOIN Music m ON l.musicId=m.musicId " 
						  + "WHERE l.artistId=? "
						  + "GROUP BY l.musicId " 
						  + "ORDER BY count(*) desc) l JOIN Music m ON l.musicId=m.musicId " 
					+ "WHERE ROWNUM <= 1";
		Object[] param = new Object[] {artistId};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
	
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
			jdbcUtil.close();	// resource 반환
		}	
		return null;
	}
	
//	//해당 artist가 가장 많이 좋아요를 누른 music의 Id
//		public int findTopLikeMusicIdByArtistId(String artistId) throws SQLException {
//			String sql = "SELECT m.musicId "
//						+ "FROM (SELECT m.musicId, count(*) "
//							  + "FROM LikeMusic l JOIN Music m ON l.musicId=m.musicId " 
//							  + "WHERE l.artistId=? "
//							  + "GROUP BY l.musicId " 
//							  + "ORDER BY count(*) desc) l JOIN Music m ON l.musicId=m.musicId " 
//						+ "WHERE ROWNUM <= 1";
//			Object[] param = new Object[] {artistId};				
//			jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
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
//				jdbcUtil.close();	// resource 반환
//			}	
//			return -1;
//		}
	
	//해당 music을 가장 많이 좋아요 누른 아티스트
	public Artist findTopLikeArtistByMusicId(int musicId) throws SQLException {
		String sql = "SELECT a.artistId, a.password, a.nickname, a.profile, a.image "
					+ "FROM (SELECT a.artistId, count(*) "
						  + "FROM LikeMusic l JOIN Artist a ON l.artistId=a.artistId " 
						  + "WHERE l.musicId=? "
						  + "GROUP BY l.artistId " 
						  + "ORDER BY count(*) desc) l JOIN Artist a ON l.artistId=a.artistId " 
					+ "WHERE ROWNUM <= 1";
		Object[] param = new Object[] {musicId};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
	
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
			jdbcUtil.close();	// resource 반환
		}	
		return null;
	}
	
//	//해당 music을 가장 많이 좋아요 누른 아티스트의 id
//		public String findTopLikeArtistIdByMusicId(int musicId) throws SQLException {
//			String sql = "SELECT a.artistId "
//						+ "FROM (SELECT a.artistId, count(*) "
//							  + "FROM LikeMusic l JOIN Artist a ON l.artistId=a.artistId " 
//							  + "WHERE l.musicId=? "
//							  + "GROUP BY l.artistId " 
//							  + "ORDER BY count(*) desc) l JOIN Artist a ON l.artistId=a.artistId " 
//						+ "WHERE ROWNUM <= 1";
//			Object[] param = new Object[] {musicId};				
//			jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
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
//				jdbcUtil.close();	// resource 반환
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
