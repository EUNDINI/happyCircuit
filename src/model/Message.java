package model;

import java.text.SimpleDateFormat;

public class Message {
	
	private long _id;			//�޽����� id
	//_id �� ���ٸ� msg_id�� msgId �̷��� 
	private long roomId;		//�޽����� ���Ե� DM���� id
	private String message;		//�޽��� ����
	private String senderId;	//������ ��� id string id�� �޾ƿ��� long _id�� �޾ƿ���
	//�ƴϸ� �׳� User sender �̷��� �޾ƿ͵�...
	private SimpleDateFormat time;	//�޽����� ���� �ð�
	
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
