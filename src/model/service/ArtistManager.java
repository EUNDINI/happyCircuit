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
	private static ArtistManager userMan = new ArtistManager();
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
		return userMan;
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

	public Artist findUser(String artistId)
		throws SQLException, ArtistNotFoundException {
		Artist artist = artistDAO.findArtistById(artistId);
		
		if (artist == null) {
			throw new ArtistNotFoundException(artistId + "�� �������� �ʴ� ���̵��Դϴ�.");
		}		
		return artist;
	}

//	public List<Artist> findArtistList(int currentPage, int countPerPage)
//		throws SQLException {
//		return userDAO.findArtistList(currentPage, countPerPage);
//	}

	public boolean login(String artistId, String password)
		throws SQLException, ArtistNotFoundException, PasswordMismatchException {
		Artist artist = findArtist(artistId);

		if (!artist.matchPassword(password)) {
			throw new PasswordMismatchException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
		return true;
	}

	private Artist findArtist(String artistId) {
		
		return null;
	}

	public ArtistDAO getArtistDAO() {
		return this.artistDAO;
	}
}
