package model;

import java.util.List;

public class DM {
	
	private long dmId;		//DM방의 id (채팅방같이)
	private List<Artist> artistList;//해당 DM에 속한 artist의 id
	
	public DM(long dmId) {
		super();
		this.dmId = dmId;
	}

	public DM(long dmId, List<Artist> artistList) {
		super();
		this.dmId = dmId;
		this.artistList = artistList;
	}

	public long getDmId() {
		return dmId;
	}

	public void setDmId(long dmId) {
		this.dmId = dmId;
	}

	public List<Artist> getArtistList() {
		return artistList;
	}

	public void setArtistList(List<Artist> artistList) {
		this.artistList = artistList;
	}
			
}
