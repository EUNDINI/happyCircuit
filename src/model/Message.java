package model;

import java.text.SimpleDateFormat;

public class Message {
	
	private long _id;			//메시지의 id
	//_id 안 쓴다면 msg_id나 msgId 이런식 
	private long roomId;		//메시지가 포함된 DM방의 id
	private String message;		//메시지 내용
	private String senderId;	//보내는 사람 id string id로 받아올지 long _id로 받아올지
	//아니면 그냥 User sender 이렇게 받아와도...
	private SimpleDateFormat time;	//메시지를 보낸 시간
	
	public Message(String message, String senderId, SimpleDateFormat time) {
		this.message = message;
		this.senderId = senderId;
		this.time = time;
	}
	
	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSender() {
		return senderId;
	}

	public void setSender(String senderId) {
		this.senderId = senderId;
	}

	public SimpleDateFormat getTime() {
		return time;
	}

	public void setTime(SimpleDateFormat time) {
		this.time = time;
	}
}
