package model;

import java.util.List;

public class DM {
	
	private int dmId;		//DM���� id (ä�ù氰��)
	private List<Artist> artistList;	//DM�� ���� artist�� list
	
	public DM(int dmId) {
		super();
		this.dmId = dmId;
	}

	public DM(int dmId, List<Artist> artistList) {
		super();
		this.dmId = dmId;
		this.artistList = artistList;
	}

	public int getDmId() {
		return dmId;
	}

	public void setDmId(int dmId) {
		this.dmId = dmId;
	}

	public List<Artist> getArtistList() {
		return artistList;
	}

	public void setArtistList(List<Artist> artistList) {
		this.artistList = artistList;
	}
			
}
