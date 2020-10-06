package model;

public class LikeChartDTO {
	private long MusicId;			// 음악 아이디
	private int ranking;			// 순위
	private int likeCount;			// 좋아요
	private String musicName;	// 음악이름
	private String regName;		// 등록자 이름
	private String musicLink;		// 등록 링크
	private String regDate; 		// 등록일
	
	public long getMusicId() {
		return MusicId;
	}
	public void setMusicId(long musicId) {
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
	public String getRegName() {
		return regName;
	}
	public void setRegName(String regName) {
		this.regName = regName;
	}
	public String getMusicLink() {
		return musicLink;
	}
	public void setMusicLink(String musicLink) {
		this.musicLink = musicLink;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
}
