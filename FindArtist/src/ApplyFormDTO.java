
public class ApplyFormDTO {
	
	private String userId; // ����� ���̵�
	private String userName; // ����� �̸�
	private String userNickname; // ����� �г��� 
	// �̸� �Ǵ� �г��� �� �� �ϳ��� ���ܵ� �� �� ����....!
	
	private String applyTitle; // ��û�� ����
	private String applyCategory; // ��û�� ī�װ�
	private String applyContent; // ��û�� ����
	
	// �ʵ� �߰� �ɸ��� �� ������ ���⿡ �� ������ �ɵ�..?
	
	// �ϴ� �����ڴ� ��� �ʵ带 ������ �� �ϳ��� ����, �ʵ� ���� �ÿ� �����ڵ� ������ �ʿ䰡 ����
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
