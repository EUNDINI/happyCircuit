package model.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.LikeChart;
import model.Music;
import model.MusicArticle;
import model.dao.JDBCUtil;

public class MusicDAO {
	private JDBCUtil jdbcUtil = null;

	// ���ǿ��� �ڵ����� id���� -> board�� fk�� pk�� ����
	// Music�� ���� DAO
	// create = controller���� 1��
	// read = controller���� 1��
	// update = controller���� 1��
	// delete = controller���� 2��
	// isArticleWriter�� controller���� �θ���

	public MusicDAO() {
		jdbcUtil = new JDBCUtil();
	}

	public int createMusic(Music music) throws Exception {
		/*
		 * musicId NUMBER NOT NULL , artistId VARCHAR2(30) NULL , musicName VARCHAR2(20)
		 * NULL , genre VARCHAR2(20) NULL , musicPath VARCHAR2(20) NULL ,
		 * originalMusicId NUMBER NULL , priorMusicId NUMBER NULL , nth NUMBER NULL
		 */

		Object[] param;
		String sql;
		if (music.getPriorMusicId() != 0) {
			sql = "INSERT INTO Music VALUES (music_seq.nextval,?,?,?,?,?,?,?)";
			param = new Object[] { music.getArtistId(), music.getMusicName(), music.getGenre(), music.getMusicPath(),
					music.getOriginalMusicId(), music.getPriorMusicId(), music.getNth() };
		} else {
			sql = "INSERT INTO Music VALUES (music_seq.nextval,?,?,?,?,null,null,?)";
			param = new Object[] { music.getArtistId(), music.getMusicName(), music.getGenre(), music.getMusicPath(),
					music.getNth() };
		}

		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil �� insert���� �Ű� ���� ����

		String key[] = { "musicId" };
		try {
			jdbcUtil.executeUpdate(key); // insert �� ����
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
			jdbcUtil.close(); // resource ��ȯ
		}

		return 0;
	}

	public Music findMusic(int musicId) throws Exception {
		String sql = "SELECT NVL(originalMusicId,0) originalMusicId, NVL(priorMusicId,0) priorMusicId, artistId, musicName, genre, nth, musicPath FROM Music WHERE musicId = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { musicId }); // JDBCUtil�� query���� �Ű� ���� ����
		Music music = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			if (rs.next()) {
				music = new Music(musicId, rs.getInt("originalMusicId"), rs.getInt("priorMusicId"),
						rs.getString("artistId"), rs.getString("musicName"), rs.getString("genre"), rs.getInt("nth"),
						rs.getString("musicPath"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// jdbcUtil.close(); // resource ��ȯ
		}

		return music;
	}

	public int updateMusic(Music music) throws Exception {
		String sql = "UPDATE Music " + "SET musicName=?, genre=?, musicPath=? " + "WHERE musicId=?";
		Object[] param = new Object[] { music.getMusicName(), music.getGenre(), music.getMusicPath(),
				music.getMusicId() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil�� update���� �Ű� ���� ����

		try {
			int result = jdbcUtil.executeUpdate(); // update �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			// jdbcUtil.close(); // resource ��ȯ
		}
		return 0;

	}

	public int deleteMusic(int musicId) throws Exception {
		String sql = "DELETE FROM Music WHERE musicId=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { musicId }); // JDBCUtil�� delete���� �Ű� ���� ����

		try {
			int result = jdbcUtil.executeUpdate(); // delete �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource ��ȯ
		}
		return 0;
	}

	public int findNth(int priorMusicId) {

		String sql = "SELECT nth FROM Music WHERE musicId=?";
		Object[] param = new Object[] { priorMusicId };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil�� update���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			int nth;
			if (rs.next()) {
				nth = rs.getInt("nth");
				return nth;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return 0;
	}

	public int findOriginalMusicId(int musicId) {
		String sql = "SELECT NVL(originalMusicId, 0) FROM Music WHERE musicId=?";
		Object[] param = new Object[] { musicId };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil�� update���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			int original;
			if (rs.next()) {
				original = rs.getInt(1);
				return original;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}

		return 0;
	}

	// MusicArticle�� ���� DAO

	public int countMusicArticle() {
		String sql = "SELECT count(*) FROM MusicArticle";
		jdbcUtil.setSqlAndParameters(sql, null);

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	public List<MusicArticle> findMusicArticleList(int currentPage, int countPerPage) throws Exception {
		int start = countPerPage * (currentPage - 1) + 1;
		String sql = "SELECT * FROM (SELECT ROWNUM AS RNUM, A.* FROM (select * from musicarticle order by regdate desc ) A WHERE ROWNUM <= ?) WHERE RNUM >= ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { start + countPerPage - 1, start });

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<MusicArticle> musicArticleList = new ArrayList<MusicArticle>();
			while (rs.next()) {
				MusicArticle musicArticle = new MusicArticle(rs.getInt("musicId"), rs.getString("content"),
						rs.getDate("regDate"), rs.getInt("readCount"), rs.getInt("likeCount"));
				musicArticleList.add(musicArticle);
			}

			for (int i = 0; i < musicArticleList.size(); i++) {
				MusicArticle musicArticle = musicArticleList.get(i);
				Music music = findMusic(musicArticle.getMusicId());
				musicArticle.setMusic(music);
			}

			return musicArticleList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return null;
	}

	public List<MusicArticle> findMusicArticleList() throws Exception {
		String sql = "SELECT * FROM MusicArticle ORDER BY regDate desc";
		jdbcUtil.setSqlAndParameters(sql, null); // JDBCUtil�� query�� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			List<MusicArticle> musicArticleList = new ArrayList<MusicArticle>();
			while (rs.next()) {
				MusicArticle musicArticle = new MusicArticle(rs.getInt("musicId"), rs.getString("content"),
						rs.getDate("regDate"), rs.getInt("readCount"), rs.getInt("likeCount"));
				musicArticleList.add(musicArticle);
			}

			for (int i = 0; i < musicArticleList.size(); i++) {
				MusicArticle musicArticle = musicArticleList.get(i);
				Music music = findMusic(musicArticle.getMusicId());
				musicArticle.setMusic(music);
			}

			return musicArticleList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return null;
	}

	public int createMusicArticle(MusicArticle musicArticle) throws Exception {
		int musicId = createMusic(musicArticle.getMusic());
		String sql = "INSERT INTO MusicArticle VALUES (?, ?, SYSDATE, ?, ?)";
		Object[] param = new Object[] { musicId, musicArticle.getContent(), musicArticle.getReadCount(),
				musicArticle.getLikeCount() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil �� insert���� �Ű� ���� ����

		try {
			int result = jdbcUtil.executeUpdate(); // insert �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource ��ȯ
		}
		return 0;
	}

	public MusicArticle findMusicArticle(int musicArticleId) throws Exception {
		int res = increaseReadCount(musicArticleId);

		if (res == 0)
			return null;

		String sql = "SELECT * FROM MusicArticle where musicId = ? ORDER BY regDate";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { musicArticleId });

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			MusicArticle musicArticle;
			if (rs.next()) {
				musicArticle = new MusicArticle(rs.getInt("musicId"), rs.getString("content"), rs.getDate("regDate"),
						rs.getInt("readCount"), rs.getInt("likeCount"));

				musicArticle.setMusic(findMusic(musicArticleId));
				return musicArticle;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return null;
	}

	public int updateMusicArticle(MusicArticle musicArticle) throws Exception {
		String sql = "UPDATE MusicArticle SET content=? WHERE musicId=?";
		Object[] param = new Object[] { musicArticle.getContent(), musicArticle.getMusic().getMusicId() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil�� update���� �Ű� ���� ����

		try {
			int result = jdbcUtil.executeUpdate(); // update �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource ��ȯ
		}
		return 0;

	}

	public int deleteMusicArticle(int musicArticleId) throws Exception {
		String sql = "DELETE FROM MusicArticle WHERE musicId=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { musicArticleId }); // JDBCUtil�� delete���� �Ű� ���� ����

		try {
			int result = jdbcUtil.executeUpdate(); // delete �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource ��ȯ
		}
		return 0;
	}

	public int countSearchMusicArticle(String condition, String search) {
		String contain = "%" + search + "%";
		String sql = "SELECT count(*) AS total FROM MusicArticle a, Music m WHERE a.musicId = m.musicId and "
				+ condition + " like " + "'" + contain + "'";
		// jdbcUtil.setSqlAndParameters(sql,new Object[] { condition, contain } );
		jdbcUtil.setSqlAndParameters(sql, null);

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	public List<MusicArticle> SearchMusicArticle(String condition, String search, int currentPage, int countPerPage)
			throws Exception {
		String sql;
		int start = countPerPage * (currentPage - 1) + 1;
		String contain = "%" + search + "%";

		sql = "SELECT * FROM ( SELECT ROWNUM AS RNUM, A.* FROM (select * from musicArticle where musicarticle.musicId in (SELECT music.musicid from music where "
				+ condition + " like " + "'" + contain + "')"
				+ " order by regdate desc) A WHERE ROWNUM <= ? ) WHERE RNUM >= ?";
		// jdbcUtil.setSqlAndParameters(sql, new Object[] { condition, search, start +
		// countPerPage - 1, start });
		jdbcUtil.setSqlAndParameters(sql, new Object[] { start + countPerPage - 1, start });
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<MusicArticle> musicArticleList = new ArrayList<MusicArticle>();
			while (rs.next()) {
				MusicArticle musicArticle = new MusicArticle(rs.getInt("musicId"), rs.getString("content"),
						rs.getDate("regDate"), rs.getInt("readCount"), rs.getInt("likeCount"));
				musicArticleList.add(musicArticle);
			}

			for (int i = 0; i < musicArticleList.size(); i++) {
				MusicArticle musicArticle = musicArticleList.get(i);
				Music music = findMusic(musicArticle.getMusicId());
				musicArticle.setMusic(music);
			}

			return musicArticleList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return null;
	}

	public int countNthCreationMusicArticle(int musicId) {
		String sql = "SELECT count(*) FROM MusicArticle a, Music m WHERE a.musicId=m.musicId and originalMusicId=? ORDER BY regDate";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { musicId });
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	public List<MusicArticle> NthCreationMusicList(int musicId, int currentPage, int countPerPage) throws Exception {
		int start = countPerPage * (currentPage - 1) + 1;
		String sql = "SELECT * FROM ( SELECT ROWNUM AS RNUM, A.* FROM (select * from musicArticle where musicarticle.musicId in "
				+ "(SELECT music.musicid from music where (originalmusicid = ? or priormusicid = ?)) order by regdate desc) A WHERE ROWNUM <= ? ) WHERE RNUM >= ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { musicId, musicId, start + countPerPage - 1, start });

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<MusicArticle> musicArticleList = new ArrayList<MusicArticle>();
			while (rs.next()) {
				MusicArticle musicArticle = new MusicArticle(rs.getInt("musicId"), rs.getString("content"),
						rs.getDate("regDate"), rs.getInt("readCount"), rs.getInt("likeCount"));
				musicArticleList.add(musicArticle);
			}

			for (int i = 0; i < musicArticleList.size(); i++) {
				MusicArticle musicArticle = musicArticleList.get(i);
				Music music = findMusic(musicArticle.getMusicId());
				musicArticle.setMusic(music);
			}

			return musicArticleList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return null;
	}

	public int increaseReadCount(int musicArticleId) {
		String sql = "UPDATE MusicArticle SET readCount=readCount+1 WHERE musicId=?";
		Object[] param = new Object[] { musicArticleId };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil�� update���� �Ű� ���� ����

		try {
			int result = jdbcUtil.executeUpdate(); // update �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource ��ȯ
		}
		return 0;
	}

	public int decreaseReadCount(int musicArticleId) {
		String sql = "UPDATE MusicArticle SET readCount=readCount-1 WHERE musicId=?";
		Object[] param = new Object[] { musicArticleId };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil�� update���� �Ű� ���� ����

		try {
			int result = jdbcUtil.executeUpdate(); // update �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource ��ȯ
		}
		return 0;
	}

	public int increaseLikeCount(int musicArticleId) {
		String sql = "UPDATE MusicArticle SET likeCount=likeCount+1 WHERE musicId=?";
		Object[] param = new Object[] { musicArticleId };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil�� update���� �Ű� ���� ����

		try {
			int result = jdbcUtil.executeUpdate(); // update �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			// jdbcUtil.close(); // resource ��ȯ
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

	// LikeChart

	// �Ѵ��� �������� ���ƿ���� ���帹�� 10���� ��������
	public List<LikeChart> getLikeChart(String genre) throws Exception {
		String sql;

		if (genre.equals("all")) {
			sql = "select ROWNUM AS ranking, A.* FROM (select m.musicId, regdate, likeCount, musicName, artistId from musicArticle a, music m "
					+ "where a.musicId=m.musicId order by likecount desc) A where ROWNUM <= 10 and regdate between (sysdate - 30) and sysdate";
			jdbcUtil.setSqlAndParameters(sql, null);
		} else {
			sql = "select ROWNUM AS ranking, A.* FROM (select m.musicId, regdate, likeCount, musicName, artistId from musicArticle a, music m "
					+ "where a.musicId=m.musicId and genre=? order by likecount desc) A where ROWNUM <= 10 and regdate between (sysdate - 30) and sysdate";
			jdbcUtil.setSqlAndParameters(sql, new Object[] { genre });
		}

		try {

			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			List<LikeChart> likeChartList = new ArrayList<LikeChart>();
			while (rs.next()) {
				LikeChart likeChart = new LikeChart(rs.getInt("musicId"), rs.getInt("ranking"), rs.getInt("likeCount"),
						rs.getString("musicName"), rs.getString("artistId"), rs.getDate("regDate"));

				likeChartList.add(likeChart);
			}

			return likeChartList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return null;
	}
}
