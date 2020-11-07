package model;

public class PostCategory {

	private int postCategoryId;
	private String postCategoryName;
	
	public PostCategory(int postCategoryId, String postCategoryName) {
		super();
		this.postCategoryId = postCategoryId;
		this.postCategoryName = postCategoryName;
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
	
}
