package model;

public class MusicBoardDTO {
	private int num;					// �Խù� ��ȣ
	private String title;				// �Խù� ����
	private String content;			// �Խù� ����
	private String originMusicLink;	// ���� ��ũ
	private String priorMusicLink;	// �ٷ� ���� ��ũ
	private String regUserID;			// ����� ���̵�
	private String regDate;			// �����
	private String fileName;			// ���� ���ϸ�
	private int readCount;				// ��ȸ��
	private int likeCount;				// ���ƿ�
	
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
