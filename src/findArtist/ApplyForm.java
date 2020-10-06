package findArtist;

import java.io.File;
import java.text.SimpleDateFormat;

public class ApplyForm {
	private String userId; // ����� ���̵�
	private String userName; // ����� �̸�
	private String userNickname; // ����� �г��� 
	// �̸� �Ǵ� �г��� �� �� �ϳ��� ���ܵ� �� �� ����....!
	//���� 3���� fk(�ܷ�Ű)�� �޾ƿ� ��
	
	private long applyNumber; // ��û�� ��ȣ, pk(�⺻Ű)
	private String applyTitle; // ��û�� ����
	private SimpleDateFormat applyDate; // ��û���� �ۼ��� ��¥
	private long applyviews; // ��û�� ��ȸ��
	private String applyCategory; // ��û�� ī�װ�
	private String applyContent; // ��û�� ����
	private File applyAttachment; // ��û���� ÷���� �� �ִ� ����, List�� �ٲܱ� �����!!!!!
	
	// �ʵ� �߰� �ɸ��� �� ������ ���⿡ �� ������ �ɵ�..?
	
	// �ϴ� �����ڴ� ��� �ʵ带 ������ �� �ϳ��� ����, �ʵ� ���� �ÿ� �����ڵ� ������ �ʿ䰡 ����
	ApplyForm(String userId, String userName, String userNickname, long applyNumber, String applyTitle,
			SimpleDateFormat applyDate, long applyviews, String applyCategory, String applyContent,
			File applyAttachment) {
		this.userId = userId;
		this.userName = userName;
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

	public File getApplyAttachment() {
		return applyAttachment;
	}

	public void setApplyAttachment(File applyAttachment) {
		this.applyAttachment = applyAttachment;
	}
}
