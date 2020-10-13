package model;

import java.io.File;
import java.text.SimpleDateFormat;

public class Collaboration { // 협업 신청폼
	private String postId; // post를 작성한 사용자의 아이디
	private long postNumber; // post의 번호
	//위에 2개는 fk(외래키)로 받아올 거
	
	private String colllaborationId; // Collaboration을 보내는 사용자 아이디
	private String userNickname; // Collaboration을 보내는 사용자 닉네임 
	
	private long collaborationNumber; // 신청폼 번호, pk(기본키)
	private String collaborationTitle; // 신청폼 제목, 작성한 post의 이름으로 저장됨, post 객체의 postTitle를 가져올 예정!!!!!,  fk(외래키)로 받아올 거
	private SimpleDateFormat collaborationDate; // 신청폼을 작성한 날짜
	private String applyContent; // 신청폼 내용
	
	public Collaboration(String postId, long postNumber, String colllaborationId, String userNickname,
			long collaborationNumber, String collaborationTitle, SimpleDateFormat collaborationDate,
			String applyContent) {
		super();
		this.postId = postId;
		this.postNumber = postNumber;
		this.colllaborationId = colllaborationId;
		this.userNickname = userNickname;
		this.collaborationNumber = collaborationNumber;
		this.collaborationTitle = collaborationTitle;
		this.collaborationDate = collaborationDate;
		this.applyContent = applyContent;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public long getPostNumber() {
		return postNumber;
	}

	public void setPostNumber(long postNumber) {
		this.postNumber = postNumber;
	}

	public String getColllaborationId() {
		return colllaborationId;
	}

	public void setColllaborationId(String colllaborationId) {
		this.colllaborationId = colllaborationId;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public long getCollaborationNumber() {
		return collaborationNumber;
	}

	public void setCollaborationNumber(long collaborationNumber) {
		this.collaborationNumber = collaborationNumber;
	}

	public String getCollaborationTitle() {
		return collaborationTitle;
	}

	public void setCollaborationTitle(String collaborationTitle) {
		this.collaborationTitle = collaborationTitle;
	}

	public SimpleDateFormat getCollaborationDate() {
		return collaborationDate;
	}

	public void setCollaborationDate(SimpleDateFormat collaborationDate) {
		this.collaborationDate = collaborationDate;
	}

	public String getApplyContent() {
		return applyContent;
	}

	public void setApplyContent(String applyContent) {
		this.applyContent = applyContent;
	}

	
}
