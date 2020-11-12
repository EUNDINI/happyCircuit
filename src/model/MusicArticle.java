package model;

public class MusicArticle {
	private Music music; 		// 음악정보 
	private String content; 	// 게시물 내용
	private int readCount; 		// 조회수
	private int likeCount; 		// 좋아요
	private String regDate; 			// 등록일
	
	public MusicArticle(Music music, String content, int readCount, int likeCount, String regDate) {
		this.music = music;
		this.content = content;
		this.readCount = readCount;
		this.likeCount = likeCount;
		this.regDate = regDate;
	}
	
	public MusicArticle(Music music, String content, int readCount, int likeCount) {
		this.music = music;
		this.content = content;
		this.readCount = readCount;
		this.likeCount = likeCount;
	}
	
	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
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
	
	
	
	
}