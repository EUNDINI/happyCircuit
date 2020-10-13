package model.dto;

import java.io.File;

public class Music {
	private long _id;						// ������ ���̵�
	private long originalMusicId;		// ������ ���̵�	- ������ ���� ������ ����
	private long priorMusicId;			// ���������� ���̵� - ������ 2��
	private String userId;				// ����� ���̵�
	private String userName;			// ����� �̸� - �ʿ��Ѱ�?
	private String musicName;			// ���� �̸�
	private String musicGenre;		// ���� �帣
	private File musicFile;				// ���� ���� - DB���� BLOB�� ����
	
	public Music(long _id, long originalMusicId, long priorMusicId, String userId, String userName, String musicName,
			String musicGenre, File musicFile) {
		this._id = _id;
		this.originalMusicId = originalMusicId;
		this.priorMusicId = priorMusicId;
		this.userId = userId;
		this.userName = userName;
		this.musicName = musicName;
		this.musicGenre = musicGenre;
		this.musicFile = musicFile;
	}
	
	public Music(long originalMusicId, long priorMusicId, String userId, String userName, String musicName,
			String musicGenre, File musicFile) {
		this.originalMusicId = originalMusicId;
		this.priorMusicId = priorMusicId;
		this.userId = userId;
		this.userName = userName;
		this.musicName = musicName;
		this.musicGenre = musicGenre;
		this.musicFile = musicFile;
	}
	
	public long get_id() {
		return _id;
	}
	public void set_id(long _id) {
		this._id = _id;
	}
	public long getOriginalMusicId() {
		return originalMusicId;
	}
	public void setOriginalMusicId(long originalMusicId) {
		this.originalMusicId = originalMusicId;
	}
	public long getPriorMusicId() {
		return priorMusicId;
	}
	public void setPriorMusicId(long priorMusicId) {
		this.priorMusicId = priorMusicId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMusicName() {
		return musicName;
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
