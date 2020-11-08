package model;

import java.io.File;

public class Music {
	private int musicId;						// ������ ���̵�
	private int originalMusicId;		// ������ ���̵�	- ������ ���� ������ ����
	private int priorMusicId;			// ���������� ���̵� - ������ 2��
	private String userId;				// ����� ���̵�
	private String musicName;			// ���� �̸�
	private String musicGenre;		// ���� �帣
	private int nth;
	private File musicFile;				// ���� ���� - DB���� BLOB�� ����
	
	public Music(int musicId, int originalMusicId, int priorMusicId, String userId, String musicName,
			String musicGenre, int nth, File musicFile) {
		this.musicId = musicId;
		this.originalMusicId = originalMusicId;
		this.priorMusicId = priorMusicId;
		this.userId = userId;
		this.musicName = musicName;
		this.musicGenre = musicGenre;
		this.nth = nth;
		this.musicFile = musicFile;
	}
	
	public Music(int originalMusicId, int priorMusicId, String userId, String musicName,
			String musicGenre, int nth, File musicFile) {
		this.originalMusicId = originalMusicId;
		this.priorMusicId = priorMusicId;
		this.userId = userId;
		this.musicName = musicName;
		this.musicGenre = musicGenre;
		this.nth = nth;
		this.musicFile = musicFile;
	}
	
	public int getMusicId() {
		return musicId;
	}
	public void setMusicId(int musicId) {
		this.musicId= musicId;
	}
	public int getOriginalMusicId() {
		return originalMusicId;
	}
	public void setOriginalMusicId(int originalMusicId) {
		this.originalMusicId = originalMusicId;
	}
	public int getPriorMusicId() {
		return priorMusicId;
	}
	public void setPriorMusicId(int priorMusicId) {
		this.priorMusicId = priorMusicId;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMusicName() {
		return musicName;
	}
	public int getNth() {
		return nth;
	}

	public void setNth(int nth) {
		this.nth = nth;
	}

	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}
	public String getMusicGenre() {
		return musicGenre;
	}
	public void setMusicGenre(String musicGenre) {
		this.musicGenre = musicGenre;
	}
	public File getMusicFile() {
		return musicFile;
	}
	public void setMusicFile(File musicFile) {
		this.musicFile = musicFile;
	}
}
