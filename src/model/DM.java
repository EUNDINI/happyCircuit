package model;

import java.util.ArrayList;

public class DM {
	
	private long _id;		//DM방의 id (채팅방같이)
	private ArrayList<String> userIdList;	//DM방에 속한 user의 id 리스트

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
