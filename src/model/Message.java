package model;

import java.text.SimpleDateFormat;

public class Message {
	
	private long msgId;			//�޽����� id
	private Artist artistId;	//������ ��� id
	private long dmId;			//�޽����� ���Ե� DM���� id
	private String message;		//�޽��� ����
	private SimpleDateFormat sentTime;	//�޽����� ���� �ð�
	
	public Message(long msgId, Artist artistId, long dmId, String message, SimpleDateFormat sentTime) {
		super();
		this.msgId = msgId;
		this.artistId = artistId;
		this.dmId = dmId;
		this.message = message;
		this.sentTime = sentTime;
	}

	public long getMsgId() {
		return msgId;
	}

	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}

	public Artist getArtistId() {
		return artistId;
	}

	public void setArtistId(Artist artistId) {
		this.artistId = artistId;
	}

	public long getDmId() {
		return dmId;
	}

	public void setDmId(long dmId) {
		this.dmId = dmId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public SimpleDateFormat getSentTime() {
		return sentTime;
	}

	public void setSentTime(SimpleDateFormat sentTime) {
		this.sentTime = sentTime;
	}
	
}
