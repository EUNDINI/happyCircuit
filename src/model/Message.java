package model;

import java.text.SimpleDateFormat;

public class Message {
	
	private long msgId;			//메시지의 id
	private Artist artistId;	//보내는 사람 id
	private long dmId;			//메시지가 포함된 DM방의 id
	private String message;		//메시지 내용
	private SimpleDateFormat sentTime;	//메시지를 보낸 시간
	
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
