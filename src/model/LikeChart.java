package model;

import java.sql.Date;

public class LikeChart {
	private int MusicId;			// ���� ���̵�
	private int ranking;				// ����
	private int likeCount;			// ���ƿ�
	private String musicName;		// �����̸�
	private String regName;		// ����� �̸�
	private Date regDate; 			// �����
	
	public LikeChart(int musicId, int ranking, int likeCount, String musicName, String regName,
			Date regDate) {
		MusicId = musicId;
		this.ranking = ranking;
		this.likeCount = likeCount;
		this.musicName = musicName;
		this.regName = regName;
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
	public String getRegName() {
		return regName;
	}
	public void setRegName(String regName) {
		this.regName = regName;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
}
