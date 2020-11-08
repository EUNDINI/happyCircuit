package model;

import java.io.File;
import java.text.SimpleDateFormat;

public class Post {

	private Artist artist; // post�� �ۼ��� ��Ƽ��Ʈ. FK
	
	private long postId; // ��û�� ��ȣ, pk(�⺻Ű)
	private String postTitle; // ��û�� ����
	private SimpleDateFormat postDate; // ��û���� �ۼ��� ��¥
	private long postView; // ��û�� ��ȸ��
	private String postContent; // ��û�� ����
	private File postAttachment; // ��û���� ÷���� �� �ִ� ����
	
	public Post(Artist artist, long postId, String postTitle, SimpleDateFormat postDate, long postView,
			String postContent, File postAttachment) {
		super();
		this.artist = artist;
		this.postId = postId;
		this.postTitle = postTitle;
		this.postDate = postDate;
		this.postView = postView;
		this.postContent = postContent;
		this.postAttachment = postAttachment;
	}
	
	public Artist getArtist() {
		return artist;
	}
	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	public long getPostId() {
		return postId;
	}
	public void setPostId(long postId) {
		this.postId = postId;
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
