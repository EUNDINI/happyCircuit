package model;

import java.sql.Date;

public class LikeChart {
	private int MusicId;						// 음악 아이디
	private int ranking;						// 순위
	private int likeCount;					// 좋아요
	private String musicName;				// 음악이름
	private String artistId;					// 등록자 이름
	private Date regDate; 					// 등록일
	private String nickname;
	
	public LikeChart(int musicId, int ranking, int likeCount, String musicName, String artistId,
			Date regDate) {
		MusicId = musicId;
		this.ranking = ranking;
		this.likeCount = likeCount;
		this.musicName = musicName;
		this.artistId = artistId;
		this.regDate = regDate;
	}
	
	
	public int getMusicId() {
		return MusicId;
	}
	public void setMusicId(int musicId) {
		MusicId = musicId;
	}

	public int getRanking() {
		return ranking;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public String getMusicName() {
		return musicName;
	}
	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}
	public String getArtistId() {
		return artistId;
	}
	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}

