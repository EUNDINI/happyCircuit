package model;

import java.io.File;
import java.text.SimpleDateFormat;

public class ApplyForm {
	private String userId; // 사용자 아이디
	private String userNickname; // 사용자 닉네임 
	//위에 2개는 fk(외래키)로 받아올 거
	
	private long applyNumber; // 신청폼 번호, pk(기본키)
	private String applyTitle; // 신청폼 제목
	private SimpleDateFormat applyDate; // 신청폼을 작성한 날짜
	private long applyviews; // 신청폼 조회수
	private String applyCategory; // 신청폼 카테고리
	private String applyContent; // 신청폼 내용
	private File applyAttachment; // 신청폼에 첨부할 수 있는 파일, List로 바꿀까 고민중!!!!!
	
	// 필드 추가 될만한 거 있으면 여기에 더 넣으면 될듯..?
	
	// 일단 생성자는 모든 필드를 포함한 거 하나만 만듬, 필드 수정 시에 생성자도 수정할 필요가 있음
	ApplyForm(String userId, String userNickname, long applyNumber, String applyTitle,
			SimpleDateFormat applyDate, long applyviews, String applyCategory, String applyContent,
			File applyAttachment) {
		this.userId = userId;
		this.userNickname = userNickname;
		this.applyNumber = applyNumber;
		this.applyTitle = applyTitle;
		this.applyDate = applyDate;
		this.applyviews = applyviews;
		this.applyCategory = applyCategory;
		this.applyContent = applyContent;
		this.applyAttachment = applyAttachment;
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
	public String getApplyTitle() {
		return applyTitle;
	}
	public void setApplyTitle(String applyTitle) {
		this.applyTitle = applyTitle;
	}
	public String getApplyCategory() {
		return applyCategory;
	}
	public void setApplyCategory(String applyCategory) {
		this.applyCategory = applyCategory;
	}
	public String getApplyContent() {
		return applyContent;
	}
	public void setApplyContent(String applyContent) {
		this.applyContent = applyContent;
	}

	public File getApplyAttachment() {
		return applyAttachment;
	}

	public void setApplyAttachment(File applyAttachment) {
		this.applyAttachment = applyAttachment;
	}
}
