package model;

public class MusicArticle {
	private Music music; 		// �������� 
	private String content; 	// �Խù� ����
	private int readCount; 		// ��ȸ��
	private int likeCount; 		// ���ƿ�
	private String regDate; 			// �����
	
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