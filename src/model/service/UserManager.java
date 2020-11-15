package model.service;

import java.sql.SQLException;
import java.util.List;

import model.dao.ArtistDAO;
import model.Artist;
import model.User;

/**
 * ����� ���� API�� ����ϴ� �����ڵ��� ���� �����ϰ� �Ǵ� Ŭ����.
 * UserDAO�� �̿��Ͽ� �����ͺ��̽��� ������ ���� �۾��� �����ϵ��� �ϸ�,
 * �����ͺ��̽��� �����͵��� �̿��Ͽ� �����Ͻ� ������ �����ϴ� ������ �Ѵ�.
 * �����Ͻ� ������ ������ ��쿡�� �����Ͻ� �������� �����ϴ� Ŭ������ 
 * ������ �� �� �ִ�.
 */
public class UserManager {
	private static UserManager userMan = new UserManager();
	private ArtistDAO artistDAO;

	private UserManager() {
		try {
			artistDAO = new ArtistDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	public static UserManager getInstance() {
		return userMan;
	}
	
	public int create(Artist artist) throws SQLException, ExistingUserException {
		if (artistDAO.existingArtist(artist.getArtistId())) {
			throw new ExistingUserException(artist.getArtistId() + "�� �����ϴ� ���̵��Դϴ�.");
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
		throws SQLException, UserNotFoundException {
		Artist artist = artistDAO.findArtist(artistId);
		
		if (artist == null) {
			throw new UserNotFoundException(artistId + "�� �������� �ʴ� ���̵��Դϴ�.");
		}		
		return artist;
	}

//	public List<Artist> findArtistList(int currentPage, int countPerPage)
//		throws SQLException {
//		return userDAO.findArtistList(currentPage, countPerPage);
//	}
//
//	public boolean login(String userId, String password)
//		throws SQLException, UserNotFoundException, PasswordMismatchException {
//		User user = findArtist(userId);
//
//		if (!user.matchPassword(password)) {
//			throw new PasswordMismatchException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
//		}
//		return true;
//	}

	public ArtistDAO getArtistDAO() {
		return this.artistDAO;
	}
}
