package model;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Collaboration { // ���� ��û��
	
	private int collaborationId; // ��û�� ��ȣ, pk(�⺻Ű)
	private String collaborationTitle; // ��û�� ����
	private Date collaborationDate; // ��û���� �ۼ��� ��¥
	private String collaborationContent; // ��û�� ����
	
	private int postId; // �ڽ��� ������û�� ���� post�� id
	private String postArtistId; // post�� �ۼ��� ����� id
	private String collaborationArtistId; // ������û�� ����� id
	
	public Collaboration () {}

	public Collaboration(int collaborationId, String collaborationTitle, Date collaborationDate,
			String collaborationContent, int postId, String postArtistId,  String collaborationArtistId) {
		super();
		this.collaborationId = collaborationId;
		this.collaborationTitle = collaborationTitle;
		this.collaborationDate = collaborationDate;
		this.collaborationContent = collaborationContent;
		this.postId = postId;
		this.postArtistId = postArtistId;
		this.collaborationArtistId = collaborationArtistId;
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

	public String getPostArtistId() {
		return postArtistId;
	}

	public void setPostArtistId(String postArtistId) {
		this.postArtistId = postArtistId;
	}

	public String getCollaborationArtistId() {
		return collaborationArtistId;
	}

	public void setCollaborationArtistId(String collaborationArtistId) {
		this.collaborationArtistId = collaborationArtistId;
	}
	
}
