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
	private int postAttachment; // ��û���� ÷���� �� �ִ� ����
	
	private int artistId; // post�� �ۼ��� ��Ƽ��Ʈ. FK
	private int postCategoryId; // post�� ī�װ�, FK
	
	public Post() {}

	public Post(int postId, String postTitle, Date postDate, int postView, String postContent, int postAttachment,
			int artistId, int postCategoryId) {
		super();
		this.postId = postId;
		this.postTitle = postTitle;
		this.postDate = postDate;
		this.postView = postView;
		this.postContent = postContent;
		this.postAttachment = postAttachment;
		this.artistId = artistId;
		this.postCategoryId = postCategoryId;
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

	public int getPostAttachment() {
		return postAttachment;
	}

	public void setPostAttachment(int postAttachment) {
		this.postAttachment = postAttachment;
	}

	public int getArtistId() {
		return artistId;
	}

	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}

	public int getPostCategoryId() {
		return postCategoryId;
	}

	public void setPostCategoryId(int postCategoryId) {
		this.postCategoryId = postCategoryId;
	}

}
