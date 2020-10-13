package model;

public class MusicBoardDTO {
	private int num;					// 게시물 번호
	private String title;				// 게시물 제목
	private String content;			// 게시물 내용
	private String originMusicLink;	// 원곡 링크
	private String priorMusicLink;	// 바로 이전 링크
	private String regUserID;			// 등록자 아이디
	private String regDate;			// 등록일
	private String fileName;			// 음악 파일명
	private int readCount;				// 조회수
	private int likeCount;				// 좋아요
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getOriginMusicLink() {
		return originMusicLink;
	}
	public void setOriginMusicLink(String originMusicLink) {
		this.originMusicLink = originMusicLink;
	}
	public String getPriorMusicLink() {
		return priorMusicLink;
	}
	public void setPriorMusicLink(String priorMusicLink) {
		this.priorMusicLink = priorMusicLink;
	}
	public String getRegUserID() {
		return regUserID;
	}
	public void setRegUserID(String regUserID) {
		this.regUserID = regUserID;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
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
