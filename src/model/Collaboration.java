package model;

import java.io.File;
import java.text.SimpleDateFormat;

public class Collaboration { // 협업 신청폼
	
	private Post post; // 자신이 협업신청을 누른 post의 객체
	private Artist artist; // 협업신청한 사람
	
	private int collaborationId; // 신청폼 번호, pk(기본키)
	private String collaborationTitle; // 신청폼 제목
	private SimpleDateFormat collaborationDate; // 신청폼을 작성한 날짜
	private String applyContent; // 신청폼 내용
	
	public Collaboration(Post post, Artist artist, int collaborationId, String collaborationTitle,
			SimpleDateFormat collaborationDate, String applyContent) {
		super();
		this.post = post;
		this.artist = artist;
		this.collaborationId = collaborationId;
		this.collaborationTitle = collaborationTitle;
		this.collaborationDate = collaborationDate;
		this.applyContent = applyContent;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
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
