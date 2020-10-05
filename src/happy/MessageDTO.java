package happy;

public class MessageDTO {
	
	private long _id;
	private String message;
	private String senderId; //username... nickname... name...
	//이걸 그냥 user의 _id나 id만 받아와도 될 것 같은데...
	private String time;
	
	public MessageDTO(String message, String senderId, String time) {
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
