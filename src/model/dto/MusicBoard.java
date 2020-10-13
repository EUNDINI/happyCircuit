package model.dto;

public class MusicBoard {
	private Music music; 		// 음악정보 
	private String content; 	// 게시물 내용
	private int readCount; 		// 조회수
	private int likeCount; 		// 좋아요
	
	public MusicBoard(Music music, String content, int readCount, int likeCount) {
		this.music = music;
		this.content = content;
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