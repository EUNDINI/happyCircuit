package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Artist;

public class ArtistDAO {
	private JDBCUtil jdbcUtil = null;

	public ArtistDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil ��ü ����
	}
	
	public int create(Artist artist) throws SQLException {
		String sql = "INSERT INTO ARTIST VALUES (?, ?, ?, ?, null)";		
		Object[] param = new Object[] {artist.getArtistId(), artist.getPw(),
					artist.getNickname(), artist.getProfile()};				
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
	
	public int update(Artist artist) throws SQLException {
		String sql = "UPDATE ARTIST "
					+ "SET password=?, nickname=?, profile=?, image=? "
					+ "WHERE artistId=?";
		Object[] param = new Object[] {artist.getPw(), artist.getNickname(),
						(!artist.getProfile().isEmpty()) ? artist.getProfile() : null, 
						(!artist.getImage().isEmpty()) ? artist.getImage() : null, 
						artist.getArtistId()};				
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

	public int remove(String artistId) throws SQLException {
		String sql = "DELETE FROM ARTIST WHERE userid=?";		
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

	// ArtistId�� �̿��� Artist ã��
	public Artist findArtistById(String artistId) throws SQLException {
        String sql = "SELECT password, nickname, profile, image "
        			+ "FROM artist "
        			+ "WHERE artistId=?";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {artistId});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {
				Artist artist = new Artist(artistId, rs.getString("password"),
								rs.getString("nickname"), rs.getString("profile"),
								rs.getString("image"));

				return artist;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}

	/**
	 * �־��� ����� ID�� �ش��ϴ� ����ڰ� �����ϴ��� �˻� 
	 */
	public boolean existingArtist(String artistId) throws SQLException {
		String sql = "SELECT count(*) FROM USERINFO WHERE userid=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {artistId});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		
		return false;
	}

}
