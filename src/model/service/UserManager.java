package model.service;

import java.sql.SQLException;
import java.util.List;

import model.dao.ArtistDAO;
import model.Artist;
import model.User;

/**
 * 사용자 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * UserDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 * 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는 클래스를 
 * 별도로 둘 수 있다.
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
			throw new ExistingUserException(artist.getArtistId() + "는 존재하는 아이디입니다.");
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
			throw new UserNotFoundException(artistId + "는 존재하지 않는 아이디입니다.");
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
//			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
//		}
//		return true;
//	}

	public ArtistDAO getArtistDAO() {
		return this.artistDAO;
	}
}
