package model;

import java.util.List;

public class DM {
	
	private long dmId;		//DM���� id (ä�ù氰��)
	private List<Artist> artistList;//�ش� DM�� ���� artist�� id
	
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
