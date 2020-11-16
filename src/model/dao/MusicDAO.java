package model.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.LikeChart;
import model.Music;
import model.MusicArticle;
import model.dao.JDBCUtil;

public class MusicDAO {
	private JDBCUtil jdbcUtil = null;

	// 음악에서 자동으로 id생성 -> board의 fk와 pk로 설정
	// Music에 관한 DAO
	// create = controller에서 2번
	// read = controller에서 1번
	// update = controller에서 1번
	// delete = controller에서 2번
	// isArticleWriter는 controller에서 부르자

	public MusicDAO() {
		jdbcUtil = new JDBCUtil();
	}

	public int createMusic(Music music) throws Exception {
		String sql = "INSERT INTO Music VALUES (music_seq.nextval,?,?,?,?,?,?,?)";
		Object[] param = new Object[] { music.getOriginalMusicId(), music.getPriorMusicId(), music.getArtistId(),
				music.getMusicName(), music.getMusicGenre(), music.getNth(), music.getMusicPath() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil 에 insert문과 매개 변수 설정

		String key[] = { "musicId" };
		try {
			jdbcUtil.executeUpdate(key); // insert 문 실행
			ResultSet rs = jdbcUtil.getGeneratedKeys();

			if (rs.next()) {
				int generatedKey = rs.getInt(1);
				music.setMusicId(generatedKey);
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

	public Music findMusic(int musicId) throws Exception {
		String sql = "SELECT * FROM Music WHERE musicId = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { musicId }); // JDBCUtil에 query문과 매개 변수 설정
		Music music = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			if (rs.next()) {
				music = new Music(musicId, rs.getInt("originalMusicId"), rs.getInt("priorMusicId"),
						rs.getString("artistId"), rs.getString("musicName"), rs.getString("musicGenre"), rs.getInt("nth"),
						rs.getString("musicPath"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}

		return music;
	}

	public int updateMusic(Music music) throws Exception {
		String sql = "UPDATE Music " + "SET musicName=?, musicGenre=?, musicPath=? " + "WHERE musicId=?";
		Object[] param = new Object[] { music.getMusicName(), music.getMusicGenre(), music.getMusicPath(),
				music.getMusicId() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil에 update문과 매개 변수 설정

		try {
			int result = jdbcUtil.executeUpdate(); // update 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 반환
		}
		return 0;

	}

	public int deleteMusic(int musicId) throws Exception {
		String sql = "DELETE FROM Music WHERE musicId=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { musicId }); // JDBCUtil에 delete문과 매개 변수 설정

		try {
			int result = jdbcUtil.executeUpdate(); // delete 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 반환
		}
		return 0;
	}

	// MusicArticle에 관한 DAO
	public List<MusicArticle> findMusicArticleList(int currentPage, int countPerPage) throws Exception {
		int start = countPerPage * (currentPage - 1) + 1;
		String sql = "SELECT * FROM MusicArticle where ROWNUM BETWEEN ? and ? ORDER BY regDate";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { start, start + countPerPage});

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			List<MusicArticle> musicArticleList = new ArrayList<MusicArticle>(); // Community들의 리스트 생성
			while (rs.next()) {
				MusicArticle musicArticle = new MusicArticle(rs.getString("content"), rs.getDate("regDate"),
						rs.getInt("readCount"), rs.getInt("likeCount"));
				Music music = findMusic(rs.getInt("musicId"));
				musicArticle.setMusic(music);
				musicArticleList.add(musicArticle); // List에 Community 객체 저장
			}
			return musicArticleList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}

	public List<MusicArticle> findMusicArticleList() throws Exception {
		String sql = "SELECT * FROM MusicArticle ORDER BY regDate";
		jdbcUtil.setSqlAndParameters(sql, null); // JDBCUtil에 query문 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			List<MusicArticle> musicArticleList = new ArrayList<MusicArticle>(); // Community들의 리스트 생성
			while (rs.next()) {
				MusicArticle musicArticle = new MusicArticle(rs.getString("content"), rs.getDate("regDate"),
						rs.getInt("readCount"), rs.getInt("likeCount"));
				Music music = findMusic(rs.getInt("musicId"));
				musicArticle.setMusic(music);
				musicArticleList.add(musicArticle); // List에 Community 객체 저장
			}
			return musicArticleList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}

	public int createMusicArticle(MusicArticle musicArticle) throws Exception {
		String sql = "INSERT INTO MusicArticle VALUES (?, ?, SYSDATE, ?, ?)";
		Object[] param = new Object[] { musicArticle.getMusic().getMusicId(), musicArticle.getContent(),
				musicArticle.getReadCount(), musicArticle.getLikeCount() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil 에 insert문과 매개 변수 설정

		try {
			int result = jdbcUtil.executeUpdate(); // insert 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 반환
		}
		return 0;
	}

	public MusicArticle findMusicArticle(int musicArticleId) throws Exception {
		int res = IncreaseReadCount(musicArticleId);
		if(res == 0)	return null;

		String sql = "SELECT * FROM MusicArticle WHERE musicId=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { musicArticleId }); // JDBCUtil에 query문과 매개 변수 설정
		MusicArticle musicArticle = null;
		Music music = findMusic(musicArticleId);
		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			if (rs.next()) { // 학생 정보 발견
				musicArticle = new MusicArticle(music, rs.getString("Content"), rs.getDate("regDate"),
						rs.getInt("readCount"), rs.getInt("likeCount"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return musicArticle;
	}

	public int updateMusicArticle(MusicArticle musicArticle) throws Exception {
		int res = IncreaseReadCount(musicArticle.getMusic().getMusicId());
		if (res == 0)
			return 0;

		res = updateMusic(musicArticle.getMusic());
		if (res == 0)
			return 0;

		String sql = "UPDATE MusicArticle SET content=? WHERE musicId=?";
		Object[] param = new Object[] { musicArticle.getContent(), musicArticle.getMusic().getMusicId() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil에 update문과 매개 변수 설정

		try {
			int result = jdbcUtil.executeUpdate(); // update 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 반환
		}
		return 0;

	}

	public int deleteMusicArticle(int musicArticleId) throws Exception {
		String sql = "DELETE FROM MusicArticle WHERE musicId=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { musicArticleId }); // JDBCUtil에 delete문과 매개 변수 설정

		try {
			int result = jdbcUtil.executeUpdate(); // delete 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 반환
		}
		return 0;
	}

	public List<MusicArticle> SearchMusicArticle(String condition, String search) throws Exception {
		String sql = "SELECT * FROM MusicArticle WHERE ?=? ORDER BY regDate";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { condition, search });

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			List<MusicArticle> musicArticleList = new ArrayList<MusicArticle>(); // Community들의 리스트 생성
			while (rs.next()) {
				MusicArticle musicArticle = new MusicArticle(rs.getString("content"), rs.getDate("regDate"),
						rs.getInt("readCount"), rs.getInt("likeCount"));
				Music music = findMusic(rs.getInt("musicId"));
				musicArticle.setMusic(music);
				musicArticleList.add(musicArticle); // List에 Community 객체 저장
			}
			return musicArticleList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}

	public List<MusicArticle> NthCreationMusicList(int musicId) throws Exception {
		String sql = "SELECT * FROM MusicArticle WHERE originalMusicId=? ORDER BY regDate";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { musicId });

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			List<MusicArticle> musicArticleList = new ArrayList<MusicArticle>(); // Community들의 리스트 생성
			while (rs.next()) {
				MusicArticle musicArticle = new MusicArticle(rs.getString("content"), rs.getDate("regDate"),
						rs.getInt("readCount"), rs.getInt("likeCount"));
				Music music = findMusic(rs.getInt("musicId"));
				musicArticle.setMusic(music);
				musicArticleList.add(musicArticle); // List에 Community 객체 저장
			}
			return musicArticleList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}

	public int IncreaseReadCount(int musicArticleId) {
		String sql = "UPDATE MusicArticle SET readCount=readCount+1 WHERE musicId=?";
		Object[] param = new Object[] { musicArticleId };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil에 update문과 매개 변수 설정

		try {
			int result = jdbcUtil.executeUpdate(); // update 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 반환
		}
		return 0;
	}

	public int IncreaseLikeCount(int musicArticleId) {
		String sql = "UPDATE MusicArticle SET likeCount=likeCount+1 WHERE musicId=?";
		Object[] param = new Object[] { musicArticleId };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil에 update문과 매개 변수 설정

		try {
			int result = jdbcUtil.executeUpdate(); // update 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 반환
		}
		return 0;
	}

	public boolean isArticleWriter(int musicArticleId, String artistId) {
		try {
			MusicArticle musicArticle = findMusicArticle(musicArticleId);
			if (musicArticle.getMusic().getArtistId().equals(artistId))
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//LikeChart
	
	// 오늘을 기준으로 좋아요수가 가장많은 10개를 가져오기
		public List<LikeChart> getLikeChart(String genre) throws Exception {
			String sql = null;
			if(genre.equals("all")) {
				sql = "SELECT musicId, likeCount, regDate, ROWNUM AS ranking FROM MusicArticle where ROWNUM <= 10 ORDER BY likeCount, regDate";
				jdbcUtil.setSqlAndParameters(sql, null);
			}
			else {
				sql = "SELECT musicId, likeCount, regDate, ROWNUM AS ranking FROM MusicArticle a, MUSIC m where a.musicId = m.musicId and"
						+ "m.musicGenre = ? and  ROWNUM <= 10 ORDER BY likeCount, regDate";
				jdbcUtil.setSqlAndParameters(sql, new Object[] { genre});
			}
			
			try {
				ResultSet rs = jdbcUtil.executeQuery(); // query 실행
				List<LikeChart> likeChartList = new ArrayList<LikeChart>(); // Community들의 리스트 생성
				while (rs.next()) {
					LikeChart likeChart = null;
					Music music = findMusic(rs.getInt("musicId"));
					likeChart = new LikeChart(music.getMusicId(), rs.getInt("ranking"), rs.getInt("likeCount"), music.getMusicName(), 
							music.getArtistId(), rs.getDate("regDate"));
					
					likeChartList.add(likeChart); // List에 Community 객체 저장
				}
				return likeChartList;

			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbcUtil.close(); // resource 반환
			}
			return null;
		}
}
