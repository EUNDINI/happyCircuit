package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Artist;
import model.Music;

public class RecommendMusicDAO {
	
	private JDBCUtil jdbcUtil = null;
	private ArtistDAO artistDAO = new ArtistDAO();
	private MusicDAO musicDAO = new MusicDAO();
	
	public RecommendMusicDAO() {
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
//	LikeMusic 테이블에 새로운 like 생성
	public int create(String artistId, int musicId) throws SQLException {
		String sql = "INSERT INTO LikeMusic VALUES (likeId_seq.nextval, ?, ?)";		
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
	
//	해당 music에 좋아요를 누른 artist 목록
	public List<Artist> findArtistListByMusicId(int musicId) throws SQLException {
		String sql = "SELECT a.artistId, a.password, a.nickname, a.profile, a.image "
					+ "FROM LikeMusic l JOIN Artist a ON l.artistId=a.artistId "
					+ "WHERE musicId=?";
		Object[] param = new Object[] {musicId};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정

		try {				
			ResultSet rs = jdbcUtil.executeQuery();
			List<Artist> artistList = new ArrayList<Artist>();
			while (rs.next()) {
				Artist artist = new Artist(rs.getString("artistId"), rs.getString("password"),
						rs.getString("nickname"), rs.getString("profile"),
						rs.getString("image"));
				artistList.add(artist);
			}
			return artistList;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("error111111");
			jdbcUtil.rollback();
		} finally {		
			jdbcUtil.close();	// resource 반환
		}	
		return null;
	}
	
//	해당 artist의 좋아요를 누른 music 목록
	public List<Music> findMusicListByArtistId(String artistId) throws SQLException {
		String sql = "SELECT m.musicId, NVL(originalMusicId,0) originalMusicId, NVL(priorMusicId,0) priorMusicId, m.artistId, m.musicName, m.genre, m.nth, m.musicPath "
					+ "FROM LikeMusic l JOIN Music m ON l.musicId=m.musicId "
					+ "WHERE l.artistId=?";
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
	
	public void close() {
		if (jdbcUtil != null) {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
	}
}
