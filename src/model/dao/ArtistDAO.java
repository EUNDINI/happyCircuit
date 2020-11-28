package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Artist;

public class ArtistDAO {
	private JDBCUtil jdbcUtil = null;

	public ArtistDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
	}
	
	public int create(Artist artist) throws SQLException {
		String sql = "INSERT INTO ARTIST VALUES (?, ?, ?, ?, null)";		
		Object[] param = new Object[] {artist.getArtistId(), artist.getPw(),
					artist.getNickname(), artist.getProfile()};				
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
	
	public int update(Artist artist) throws SQLException {
		String sql = "UPDATE ARTIST "
					+ "SET password=?, nickname=?, profile=?, image=? "
					+ "WHERE artistId=?";
		Object[] param = new Object[] {artist.getPw(), artist.getNickname(),
						(!artist.getProfile().isEmpty()) ? artist.getProfile() : null, 
						(!artist.getImage().isEmpty()) ? artist.getImage() : null, 
						artist.getArtistId()};				
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

	public int remove(String artistId) throws SQLException {
		String sql = "DELETE FROM ARTIST WHERE userid=?";		
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

	// ArtistId를 이용해 Artist 찾기
	public Artist findArtistById(String artistId) throws SQLException {
        String sql = "SELECT password, nickname, profile, image "
        			+ "FROM artist "
        			+ "WHERE artistId=?";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {artistId});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {
				Artist artist = new Artist(artistId, rs.getString("password"),
								rs.getString("nickname"), rs.getString("profile"),
								rs.getString("image"));

				return artist;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}

	/**
	 * 주어진 사용자 ID에 해당하는 사용자가 존재하는지 검사 
	 */
	public boolean existingArtist(String artistId) throws SQLException {
		String sql = "SELECT count(*) FROM USERINFO WHERE userid=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {artistId});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		
		return false;
	}

}
