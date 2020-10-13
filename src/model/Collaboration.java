package model;

import java.io.File;
import java.text.SimpleDateFormat;

public class Collaboration { // ���� ��û��
	private String postId; // post�� �ۼ��� ������� ���̵�
	private long postNumber; // post�� ��ȣ
	//���� 2���� fk(�ܷ�Ű)�� �޾ƿ� ��
	
	private String colllaborationId; // Collaboration�� ������ ����� ���̵�
	private String userNickname; // Collaboration�� ������ ����� �г��� 
	
	private long collaborationNumber; // ��û�� ��ȣ, pk(�⺻Ű)
	private String collaborationTitle; // ��û�� ����, �ۼ��� post�� �̸����� �����, post ��ü�� postTitle�� ������ ����!!!!!,  fk(�ܷ�Ű)�� �޾ƿ� ��
	private SimpleDateFormat collaborationDate; // ��û���� �ۼ��� ��¥
	private String applyContent; // ��û�� ����
	
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
