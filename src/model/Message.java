package model;

import java.util.Date;

public class Message {
	
	private int msgId;			//�޽����� id
	private String message;		//�޽��� ����
	private Date sentTime;		//�޽����� ���� �ð�
	private Artist artist;		//������ ���
	private int dmId;			//�޽����� ���Ե� DM���� id
	
	public Message(int msgId, String message, Date sentTime, Artist artist, int dmId) {
		super();
		this.msgId = msgId;
		this.message = message;
		this.sentTime = sentTime;
		this.artist = artist;
		this.dmId = dmId;
	}

	public int getMsgId() {
		return msgId;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getSentTime() {
		return sentTime;
	}

	public void setSentTime(Date sentTime) {
		this.sentTime = sentTime;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artistId) {
		this.artist = artistId;
	}

	public int getDmId() {
		return dmId;
	}

	public void setDmId(int dmId) {
		this.dmId = dmId;
	}
	
}
