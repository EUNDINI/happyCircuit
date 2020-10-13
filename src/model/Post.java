package model;

import java.io.File;
import java.text.SimpleDateFormat;

public class Post {
	private String userId; // 사용자 아이디
	private String userNickname; // 사용자 닉네임 
	//위에 2개는 fk(외래키)로 받아올 거
	
	private long postNumber; // 신청폼 번호, pk(기본키)
	private String postTitle; // 신청폼 제목
	private SimpleDateFormat postDate; // 신청폼을 작성한 날짜
	private long postView; // 신청폼 조회수
	private String postCategory; // 신청폼 카테고리
	private String postContent; // 신청폼 내용
	private File postAttachment; // 신청폼에 첨부할 수 있는 파일, List로 바꿀까 고민중!!!!!
	
	
	// 필드 추가 될만한 거 있으면 여기에 더 넣으면 될듯..?
	
	// 일단 생성자는 모든 필드를 포함한 거 하나만 만듬, 필드 수정 시에 생성자도 수정할 필요가 있음
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
