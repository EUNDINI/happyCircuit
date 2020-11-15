package model;

import java.util.Date;

public class Message {
	
	private int msgId;			//메시지의 id
	private String message;		//메시지 내용
	private Date sentTime;		//메시지를 보낸 시간
	private Artist artist;		//보내는 사람
	private DM dm;			//메시지가 포함된 DM방의 id
	
	public Message(int msgId, String message, Date sentTime, Artist artist, DM dm) {
		super();
		this.msgId = msgId;
		this.message = message;
		this.sentTime = sentTime;
		this.artist = artist;
		this.dm = dm;
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

	public DM getDm() {
		return dm;
	}

	public void setDmId(DM dm) {
		this.dm = dm;
	}
	
}
