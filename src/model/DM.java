package model;

public class DM {
	
	private long dmId;		//DM방의 id (채팅방같이)
	private Artist artistId;//해당 DM에 속한 artist의 id
	
	public DM(long dmId, Artist artistId) {
		super();
		this.dmId = dmId;
		this.artistId = artistId;
	}

	public long getDmId() {
		return dmId;
	}

	public void setDmId(long dmId) {
		this.dmId = dmId;
	}

	public Artist getArtistId() {
		return artistId;
	}

	public void setArtistId(Artist artistId) {
		this.artistId = artistId;
	}
		
}
