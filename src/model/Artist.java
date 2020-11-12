package model;

import java.awt.Image;

public class Artist {
	
	private String artistId;			//artist의 id
	private String pw;			//artist의 비밀번호
	private String nickname;	//artist의 nickname 수정 가능?
	private String profile;		//artist 소개 introduction..? null일 수 있다
	private String image;		//artist의 이미지 null일 수 있다
	
	public Artist(String artistId, String pw, String nickname, String profile, String image) {
		super();
		this.artistId = artistId;
		this.pw = pw;
		this.nickname = nickname;
		this.profile = profile;
		this.image = image;
	}

	public Artist() { }

	public String getArtistId() {
		return artistId;
	}

	public void setArtistId(String artistId) {
		this.artistId = artistId;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}
