package model;

import java.awt.Image;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {

	private int postId; // ��û�� ��ȣ, pk(�⺻Ű)
	private String postTitle; // ��û�� ����
	private Date postDate; // ��û���� �ۼ��� ��¥
	private int postView; // ��û�� ��ȸ��
	private String postContent; // ��û�� ����
	private String postAttachment; // ��û���� ÷���� �� �ִ� ����
	
	private int postCategoryId; // post�� ī�װ� PK, FK	
	private String postCategoryName; // post�� ī�װ��̸�, FK
	
	private String artistId; // post�� �ۼ��� ��Ƽ��Ʈ�� PK, FK
	private String nickname; // post�� �ۼ��� ��Ƽ��Ʈ�� �г���, FK
	
	public Post() {}

	public Post(int postId, String postTitle, Date postDate, int postView, String postContent, String postAttachment,
			int postCategoryId, String postCategoryName, String artistId, String nickname) {
		super();
		this.postId = postId;
		this.postTitle = postTitle;
		this.postDate = postDate;
		this.postView = postView;
		this.postContent = postContent;
		this.postAttachment = postAttachment;
		this.postCategoryId = postCategoryId;
		this.postCategoryName = postCategoryName;
		this.artistId = artistId;
		this.nickname = nickname;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public int getPostView() {
		return postView;
	}

	public void setPostView(int postView) {
		this.postView = postView;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getPostAttachment() {
		return postAttachment;
	}

	public void setPostAttachment(String postAttachment) {
		this.postAttachment = postAttachment;
	}

	public int getPostCategoryId() {
		return postCategoryId;
	}

	public void setPostCategoryId(int postCategoryId) {
		this.postCategoryId = postCategoryId;
	}

	public String getPostCategoryName() {
		return postCategoryName;
	}

	public void setPostCategoryName(String postCategoryName) {
		this.postCategoryName = postCategoryName;
	}

	public String getArtistId() {
		return artistId;
	}

	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
