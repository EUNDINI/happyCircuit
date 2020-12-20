package model;
public class Music {
	private int musicId;								// ������ ���̵�
	private int originalMusicId;					// ������ ���̵�	- ������ ���� ������ ����
	private int priorMusicId;						// ���������� ���̵� - ������ 2��
	private String artistId;							// ����� ���̵�
	private String musicName;						// ���� �̸�
	private String genre;							// ���� �帣
	private int nth;
	private String musicPath;						// ���� ���� - DB���� BLOB�� ����
	
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
