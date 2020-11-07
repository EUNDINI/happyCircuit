package model;

import java.io.File;
import java.text.SimpleDateFormat;

public class Collaboration { // ���� ��û��
	
	private Post post; // �ڽ��� ������û�� ���� post�� ��ü
	private Artist artist; // ������û�� ���
	
	private int collaborationId; // ��û�� ��ȣ, pk(�⺻Ű)
	private String collaborationTitle; // ��û�� ����
	private SimpleDateFormat collaborationDate; // ��û���� �ۼ��� ��¥
	private String applyContent; // ��û�� ����
	
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
