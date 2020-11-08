package model;

public class DM {
	
	private long dmId;		//DM���� id (ä�ù氰��)
	private Artist artistId;//�ش� DM�� ���� artist�� id
	
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
