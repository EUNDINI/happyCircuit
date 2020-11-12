package model;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Collaboration { // 협업 신청폼
	
	private int collaborationId; // 신청폼 번호, pk(기본키)
	private String collaborationTitle; // 신청폼 제목
	private Date collaborationDate; // 신청폼을 작성한 날짜
	private String collaborationContent; // 신청폼 내용
	
	private int postId; // 자신이 협업신청을 누른 post의 객체
	private int artistId; // 협업신청한 사람
	
	public Collaboration () {}
	
	public Collaboration(int collaborationId, String collaborationTitle, Date collaborationDate,
			String collaborationContent, int postId, int artistId) {
		super();
		this.collaborationId = collaborationId;
		this.collaborationTitle = collaborationTitle;
		this.collaborationDate = collaborationDate;
		this.collaborationContent = collaborationContent;
		this.postId = postId;
		this.artistId = artistId;
	}

	public int getCollaborationId() {
		return collaborationId;
	}

	public void setCollaborationId(int collaborationId) {
		this.collaborationId = collaborationId;
	}

	public String getCollaborationTitle() {
		return collaborationTitle;
	}

	public void setCollaborationTitle(String collaborationTitle) {
		this.collaborationTitle = collaborationTitle;
	}

	public Date getCollaborationDate() {
		return collaborationDate;
	}

	public void setCollaborationDate(Date collaborationDate) {
		this.collaborationDate = collaborationDate;
	}

	public String getCollaborationContent() {
		return collaborationContent;
	}

	public void setCollaborationContent(String collaborationContent) {
		this.collaborationContent = collaborationContent;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getArtistId() {
		return artistId;
	}

	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}
	
}
