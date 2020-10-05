
public class ApplyFormDTO {
	
	private String userId; // 사용자 아이디
	private String userName; // 사용자 이름
	private String userNickname; // 사용자 닉네임 
	// 이름 또는 닉네임 둘 중 하나만 남겨도 될 거 같음....!
	
	private String applyTitle; // 신청폼 제목
	private String applyCategory; // 신청폼 카테고리
	private String applyContent; // 신청폼 내용
	
	// 필드 추가 될만한 거 있으면 여기에 더 넣으면 될듯..?
	
	// 일단 생성자는 모든 필드를 포함한 거 하나만 만듬, 필드 수정 시에 생성자도 수정할 필요가 있음
	public ApplyFormDTO(String userId, String userName, String userNickname, String applyTitle, String applyCategory,
			String applyContent) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userNickname = userNickname;
		this.applyTitle = applyTitle;
		this.applyCategory = applyCategory;
		this.applyContent = applyContent;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	
	
	
}
