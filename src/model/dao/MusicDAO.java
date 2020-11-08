package model.dao;

import java.io.File;
import java.sql.Blob;
import java.util.List;

import model.Music;
import model.MusicArticle;

public class MusicDAO {
	
	//음악에서 자동으로 id생성 -> board의 fk와 pk로 설정
	// Music에 관한 DAO	
	public long createMusic(Music music) throws Exception {

		
		// 생성후에 생성된 id를 찾아 반환하자
		return 0;
	}

	public Music readMusic(int id) throws Exception {

		return null;
	}

	public int updateMusic(Music music) throws Exception {

		return 0;

	}

	public int deleteMusic(int id) throws Exception {
		return 0;
	}
	
	//MusicArticle에 관한 DAO
	public int getMusicArticleList() {
		return 0;
	}

	public List<MusicArticle> findMusicArticleList(int currentPage, int countPerPage) throws Exception {

		return null;

	}
	
	public List<MusicArticle> findMusicArticleList() throws Exception {

		return null;

	}

	public int createMusicArticle(MusicArticle MusicArticle) throws Exception {

		return 0;
	}

	public MusicArticle readMusicArticle(int id) throws Exception {

		return null;
	}

	public int updateMusicArticle(MusicArticle MusicArticle) throws Exception {

		return 0;

	}

	public int deleteMusicArticle(int id) throws Exception {
		return 0;
	}

	public List<MusicArticle> SearchMusicArticle(String condition, String search) throws Exception {

		return null;

	}
	
	public MusicArticle findMusicArticle(int id) {
		return null;
	}

	public List<MusicArticle> NthCreationMusicList(int id) throws Exception {

		return null;
	}

	public void IncreaseReadCount(long id) {

	}

	public void IncreaseLikeCount(long id) {

	}
	
	public boolean isBoardWriter(long id) {
		
		return false;
	}
}
