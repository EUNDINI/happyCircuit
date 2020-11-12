package model;

import java.awt.Image;

public class User {
	private int _id;
	private String userId;
	private String pw;
	private String nickname;
	private String profile; //introduction..?
	private Image image;
	
	public User() { }
	
	public User(int _id, String userId, String pw, String nickname, String profile, Image image) {
		this._id = _id;
		this.userId = userId;
		this.pw = pw;
		this.nickname = nickname;
		this.profile = profile;
		this.image = image;
	}
	
	public User(String userId, String pw, String nickname, String profile, Image image) {
		this.userId = userId;
		this.pw = pw;
		this.nickname = nickname;
		this.profile = profile;
		this.image = image;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getUserId() {
		return userId;
	}

	public void setId(String userId) {
		this.userId = userId;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	/* 비밀번호 검사 */
	public boolean matchPassword(String pw) {
		if (pw == null) {
			return false;
		}
		return this.pw.equals(pw);
	}
	
	/* user 검사 */
	public boolean isSameUser(String userId) {
        return this.userId.equals(userId);
    }

}
