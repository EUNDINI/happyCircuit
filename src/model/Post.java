package model;

import java.io.File;
import java.text.SimpleDateFormat;

public class Post {

	private Artist artist; // post를 작성한 아티스트. FK
	
	private int postId; // 신청폼 번호, pk(기본키)
	private String postTitle; // 신청폼 제목
	private SimpleDateFormat postDate; // 신청폼을 작성한 날짜
	private int postView; // 신청폼 조회수
	private String postContent; // 신청폼 내용
	private File postAttachment; // 신청폼에 첨부할 수 있는 파일
	
	public Post(Artist artist, int postId, String postTitle, SimpleDateFormat postDate, int postView,
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
	public SimpleDateFormat getPostDate() {
		return postDate;
	}
	public void setPostDate(SimpleDateFormat postDate) {
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
	public File getPostAttachment() {
		return postAttachment;
	}
	public void setPostAttachment(File postAttachment) {
		this.postAttachment = postAttachment;
	}

}
