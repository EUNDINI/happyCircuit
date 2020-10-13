package model.service;

import java.util.List;

import model.dao.MusicDAO;
import model.dto.MusicBoard;

public class MusicBoardManager {
	private static MusicBoardManager MusicManager = new MusicBoardManager();
	private MusicDAO MusicDAO;

	private MusicBoardManager() {
		MusicDAO = new MusicDAO();
	}

	public static MusicBoardManager getInstance() {
		return MusicManager;
	}

	public int create(MusicBoard musicBoard) throws Exception {
		return MusicDAO.createMusicBoard(musicBoard);
	}

	public int update(MusicBoard musicBoard) throws Exception {
		return MusicDAO.updateMusicBoard(musicBoard);
	}

	public int delete(Integer id) throws Exception {
		return MusicDAO.deleteMusicBoard(id);
	}
	
	public List findList(int currentPage, int countPerPage) throws Exception {
		return MusicDAO.findMusicBoardList(currentPage, countPerPage);
	}
	
	public List search(String condition, String search) throws Exception {
		return MusicDAO.SearchMusicBoard(condition, search);
	}
}
