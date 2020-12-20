package model;
public class Music {
	private int musicId;								// 음악의 아이디
	private int originalMusicId;					// 원곡의 아이디	- 없으면 현재 음악이 원곡
	private int priorMusicId;						// 이전음악의 아이디 - 없으면 2차
	private String artistId;							// 사용자 아이디
	private String musicName;						// 음악 이름
	private String genre;							// 음악 장르
	private int nth;
	private String musicPath;						// 음악 파일 - DB에는 BLOB로 저장
	
	public Music() {};
	
	public Music(int musicId, int originalMusicId, int priorMusicId, String artistId, String musicName,
			String genre, int nth, String musicPath) {
		this.musicId = musicId;
		this.originalMusicId = originalMusicId;
		this.priorMusicId = priorMusicId;
		this.artistId = artistId;
		this.musicName = musicName;
		this.genre = genre;
		this.nth = nth;
		this.musicPath = musicPath;
	}
	
	public Music(int originalMusicId, int priorMusicId, String artistId, String musicName,
			String genre, int nth, String musicPath) {
		this.originalMusicId = originalMusicId;
		this.priorMusicId = priorMusicId;
		this.artistId = artistId;
		this.musicName = musicName;
		this.genre = genre;
		this.nth = nth;
		this.musicPath = musicPath;
	}
	
	public int getMusicId() {
		return musicId;
	}
	public void setMusicId(int musicId) {
		this.musicId= musicId;
	}
	public int getOriginalMusicId() {
		return originalMusicId;
	}
	public void setOriginalMusicId(int originalMusicId) {
		this.originalMusicId = originalMusicId;
	}
	public int getPriorMusicId() {
		return priorMusicId;
	}
	public void setPriorMusicId(int priorMusicId) {
		this.priorMusicId = priorMusicId;
	}
	
	public String getArtistId() {
		return artistId;
	}

	public void setArtistId(String userId) {
		this.artistId = userId;
	}

	public String getMusicName() {
		return musicName;
	}
	public int getNth() {
		return nth;
	}

	public void setNth(int nth) {
		this.nth = nth;
	}

	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getMusicPath() {
		return musicPath;
	}

	public void setMusicPath(String musicPath) {
		this.musicPath = musicPath;
	}
	
}
