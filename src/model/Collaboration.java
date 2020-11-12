package model;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Collaboration { // ���� ��û��
	
	private int collaborationId; // ��û�� ��ȣ, pk(�⺻Ű)
	private String collaborationTitle; // ��û�� ����
	private Date collaborationDate; // ��û���� �ۼ��� ��¥
	private String collaborationContent; // ��û�� ����
	
	private int postId; // �ڽ��� ������û�� ���� post�� ��ü
	private int artistId; // ������û�� ���
	
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
