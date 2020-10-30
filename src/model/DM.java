package model;

import java.util.ArrayList;

public class DM {
	
	private long _id;		//DM방의 id (채팅방같이)
	//roomId 이런 식으로 해도 될 듯? _id 안 쓴다면
	private ArrayList<String> userIdList;	//DM방에 속한 user의 id 리스트
	//그냥 ArrayList<User>해도 될 것 같기도 하고...

	public DM(long _id, ArrayList<String> userIdList) {
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
