package model.service;

import java.sql.SQLException;
import java.util.List;

import model.dao.ArtistDAO;
import model.Artist;

/**
 * ����� ���� API�� ����ϴ� �����ڵ��� ���� �����ϰ� �Ǵ� Ŭ����.
 * UserDAO�� �̿��Ͽ� �����ͺ��̽��� ������ ���� �۾��� �����ϵ��� �ϸ�,
 * �����ͺ��̽��� �����͵��� �̿��Ͽ� �����Ͻ� ������ �����ϴ� ������ �Ѵ�.
 * �����Ͻ� ������ ������ ��쿡�� �����Ͻ� �������� �����ϴ� Ŭ������ 
 * ������ �� �� �ִ�.
 */
public class ArtistManager {
	private static ArtistManager artistMan = new ArtistManager();
	private ArtistDAO artistDAO;

	private ArtistManager() {
		try {
			artistDAO = new ArtistDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	public static ArtistManager getInstance() {
		return artistMan;
	}
	
	public int create(Artist artist) throws SQLException, ExistingArtistException {
		if (artistDAO.existingArtist(artist.getArtistId())) {
			throw new ExistingArtistException(artist.getArtistId() + "�� �����ϴ� ���̵��Դϴ�.");
		}
		return artistDAO.create(artist);
	}

	public int update(Artist artist) throws SQLException {
		return artistDAO.update(artist);
	}	

	public int remove(String artistId) throws SQLException {
		return artistDAO.remove(artistId);
	}

	public Artist findArtist(String artistId)
		throws SQLException, ArtistNotFoundException {
		Artist artist = artistDAO.findArtistById(artistId);
		if (artist == null) {
			throw new ArtistNotFoundException(artistId + "�� �������� �ʴ� ���̵��Դϴ�.");
		}
		
		return artist;
	}

	public boolean login(String artistId, String password)
		throws SQLException, ArtistNotFoundException, PasswordMismatchException {
		Artist artist = findArtist(artistId);

		if (!artist.matchPassword(password)) {
			throw new PasswordMismatchException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
		return true;
	}
	
	public ArtistDAO getArtistDAO() {
		return this.artistDAO;
	}
}
