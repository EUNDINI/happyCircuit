package model.dao;

import java.io.File;
import java.sql.Blob;
import java.util.List;

import model.Music;
import model.MusicBoard;

public class MusicDAO {
	
	//음악에서 자동으로 id생성 -> board의 fk와 pk로 설정
	// Music에 관한 DAO	
	public long createMusic(Music music) throws Exception {

		
		// 생성후에 생성된 id를 찾아 반환하자
		return 0;
	}

	public Music readMusic(long id) throws Exception {

		return null;
	}

	public int updateMusic(Music music) throws Exception {

		return 0;

	}

	public int deleteMusic(long id) throws Exception {
		return 0;
	}
	
	//MusicBoard에 관한 DAO
	public int getMusicBoardList() {
		return 0;
	}

	public List<MusicBoard> findMusicBoardList(int currentPage, int countPerPage) throws Exception {

		return null;

	}
	
	public List<MusicBoard> findMusicBoardList() throws Exception {

		return null;

	}

	public int createMusicBoard(MusicBoard musicBoard) throws Exception {

		return 0;
	}

	public MusicBoard readMusicBoard(long id) throws Exception {

		return null;
	}

	public int updateMusicBoard(MusicBoard musicBoard) throws Exception {

		return 0;

	}

	public int deleteMusicBoard(long id) throws Exception {
		return 0;
	}

	public List<MusicBoard> SearchMusicBoard(String condition, String search) throws Exception {

		return null;

	}
	
	public MusicBoard findMusicBoard(long id) {
		return null;
	}

	public List<MusicBoard> NthCreationMusicList(long id) throws Exception {

		return null;
	}

	public void IncreaseReadCount(long id) {

	}

	public void IncreaseLikeCount(long id) {

	}
	
	public boolean isBoardWriter(long id) {
		
		return false;
	}
		
	//음악파일 변환
	public File blobToFile(Blob blob) {
		File music = null;
		
		return music;
	}
	
	public Blob fileToBlob(File musicFile) {
		Blob music = null;
		
		return music; 
	}

}
