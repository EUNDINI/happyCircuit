package model;

import java.awt.Image;

public class User {
	
	private long _id;			//user의 _id
	private String id;			//user의 id
	private String pw;			//user의 비밀번호
	private String nickname;	//user의 nickname 수정 가능?
	private String profile;		//user 소개 introduction..?
	private Image image;		//user의 이미지
	
	public User(long _id, String id, String pw, String nickname, String profile, Image image) {
		this._id = _id;
		this.id = id;
		this.pw = pw;
		this.nickname = nickname;
		this.profile = profile;
		this.image = image;
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
	
}
