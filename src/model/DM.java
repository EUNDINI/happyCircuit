package model;

import java.util.ArrayList;

public class DM {
	
	private long _id;		//DM���� id (ä�ù氰��)
	//roomId �̷� ������ �ص� �� ��? _id �� ���ٸ�
	private ArrayList<String> userIdList;	//DM�濡 ���� user�� id ����Ʈ
	//�׳� ArrayList<User>�ص� �� �� ���⵵ �ϰ�...

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
