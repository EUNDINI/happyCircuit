package model;

import java.sql.Date;

public class MusicArticle {
	private int musicId;
	private Music music; // ��������
	private String content; // �Խù� ����
	private int readCount; // ��ȸ��
	private int likeCount; // ���ƿ�
	private Date regDate; // �����
	private Artist artist;

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public MusicArticle() {
	}

	public MusicArticle(int musicId, String content, Date regDate, int readCount, int likeCount) {
		this.musicId = musicId;
		this.content = content;
		this.regDate = regDate;
		this.readCount = readCount;
		this.likeCount = likeCount;
	}

	public MusicArticle(Music music, String content, Date regDate, int readCount, int likeCount) {
		this.music = music;
		this.content = content;
		this.regDate = regDate;
		this.readCount = readCount;
		this.likeCount = likeCount;
	}

	public MusicArticle(Music music, String content, int readCount, int likeCount) {
		this.music = music;
		this.content = content;
		this.readCount = readCount;
		this.likeCount = likeCount;
	}

	public MusicArticle(String content, Date regDate, int readCount, int likeCount) {
		this.content = content;
		this.regDate = regDate;
		this.readCount = readCount;
		this.likeCount = likeCount;
	}

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getMusicId() {
		return musicId;
	}

	public void setMusicId(int musicId) {
		this.musicId = musicId;
	}
	
}