package model.dao;

import java.io.File;
import java.sql.Blob;
import java.util.List;

import model.Music;
import model.MusicArticle;

public class MusicDAO {
	
	//���ǿ��� �ڵ����� id���� -> board�� fk�� pk�� ����
	// Music�� ���� DAO	
	public long createMusic(Music music) throws Exception {

		
		// �����Ŀ� ������ id�� ã�� ��ȯ����
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
	
	//MusicArticle�� ���� DAO
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
