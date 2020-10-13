package model;

import java.io.File;
import java.text.SimpleDateFormat;

public class Post {
	private String userId; // ����� ���̵�
	private String userNickname; // ����� �г��� 
	//���� 2���� fk(�ܷ�Ű)�� �޾ƿ� ��
	
	private long postNumber; // ��û�� ��ȣ, pk(�⺻Ű)
	private String postTitle; // ��û�� ����
	private SimpleDateFormat postDate; // ��û���� �ۼ��� ��¥
	private long postView; // ��û�� ��ȸ��
	private String postCategory; // ��û�� ī�װ�
	private String postContent; // ��û�� ����
	private File postAttachment; // ��û���� ÷���� �� �ִ� ����, List�� �ٲܱ� �����!!!!!
	
	
	// �ʵ� �߰� �ɸ��� �� ������ ���⿡ �� ������ �ɵ�..?
	
	// �ϴ� �����ڴ� ��� �ʵ带 ������ �� �ϳ��� ����, �ʵ� ���� �ÿ� �����ڵ� ������ �ʿ䰡 ����
	public Post(String userId, String userNickname, long postNumber, String postTitle, SimpleDateFormat postDate,
			long postView, String postCategory, String postContent, File postAttachment) {
		super();
		this.userId = userId;
		this.userNickname = userNickname;
		this.postNumber = postNumber;
		this.postTitle = postTitle;
		this.postDate = postDate;
		this.postView = postView;
		this.postCategory = postCategory;
		this.postContent = postContent;
		this.postAttachment = postAttachment;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public long getPostNumber() {
		return postNumber;
	}
	public void setPostNumber(long postNumber) {
		this.postNumber = postNumber;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public SimpleDateFormat getPostDate() {
		return postDate;
	}
	public void setPostDate(SimpleDateFormat postDate) {
		this.postDate = postDate;
	}
	public long getPostView() {
		return postView;
	}
	public void setPostView(long postView) {
		this.postView = postView;
	}
	public String getPostCategory() {
		return postCategory;
	}
	public void setPostCategory(String postCategory) {
		this.postCategory = postCategory;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public File getPostAttachment() {
		return postAttachment;
	}
	public void setPostAttachment(File postAttachment) {
		this.postAttachment = postAttachment;
	}	

}
