package model.service;

import java.sql.SQLException;
import java.util.List;

import model.dao.ArtistDAO;
import model.Artist;
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
			throw new ExistingArtistException(artist.getArtistId() + "�뒗 議댁옱�븯�뒗 �븘�씠�뵒�엯�땲�떎.");
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
			throw new ArtistNotFoundException(artistId + "�뒗 議댁옱�븯吏� �븡�뒗 �븘�씠�뵒�엯�땲�떎.");
		}
		
		return artist;
	}

	public boolean login(String artistId, String password)
		throws SQLException, ArtistNotFoundException, PasswordMismatchException {
		Artist artist = findArtist(artistId);

		if (!artist.matchPassword(password)) {
			throw new PasswordMismatchException("鍮꾨�踰덊샇媛� �씪移섑븯吏� �븡�뒿�땲�떎.");
		}
		return true;
	}
	
	public ArtistDAO getArtistDAO() {
		return this.artistDAO;
	}
}
