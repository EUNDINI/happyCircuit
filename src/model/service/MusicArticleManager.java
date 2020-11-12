package model.service;

import java.util.List;

import model.MusicArticle;
import model.dao.MusicDAO;

public class MusicArticleManager {
	private static MusicArticleManager MusicManager = new MusicArticleManager();
	private MusicDAO MusicDAO;

	private MusicArticleManager() {
		MusicDAO = new MusicDAO();
	}

	public static MusicArticleManager getInstance() {
		return MusicManager;
	}

	public int create(MusicArticle musicArticle) throws Exception {
		return MusicDAO.createMusicArticle(musicArticle);
	}

	public int update(MusicArticle musicArticle) throws Exception {
		return MusicDAO.updateMusicArticle(musicArticle);
	}

	public int delete(Integer id) throws Exception {
		return MusicDAO.deleteMusicArticle(id);
	}
	
	public List findList(int currentPage, int countPerPage) throws Exception {
		return MusicDAO.findMusicArticleList(currentPage, countPerPage);
	}
	
	public List findList() throws Exception {
		return MusicDAO.findMusicArticleList();
	}
	
	public List search(String condition, String search) throws Exception {
		return MusicDAO.SearchMusicArticle(condition, search);
	}
}
