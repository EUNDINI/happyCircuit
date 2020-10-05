package happy;

import java.util.ArrayList;

public class DMDTO {
	
	private long _id;
	private ArrayList<String> userIdList;

	public DMDTO(long _id, ArrayList<String> userIdList) {
		this._id = _id;
		this.userIdList = userIdList;
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public ArrayList<String> getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(ArrayList<String> userIdList) {
		this.userIdList = userIdList;
	}
	
}
