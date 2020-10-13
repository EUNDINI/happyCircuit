package model;

import java.io.File;

public class MusicDTO {
	private long _id;					// 음악의 아이디
	private long originalMusicId;		// 원곡의 아이디	- 없으면 현재 음악이 원곡
	private long priorMusicId;		// 이전음악의 아이디 - 없으면 2차
	private String userId;				// 사용자 아이디
	private String userName;			// 사용자 이름 - 필요한가?
	private String musicName;		// 음악 이름
	private String musicGenre;		// 음악 장르
	private File musicFile;				// 음악 파일 - DB에는 BLOB로 저장
	private String comment;			// 기타 커멘트 - 소개 등등 (필요한가?)
	
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
