package model;

import java.io.File;
import java.text.SimpleDateFormat;

public class Collaboration {
	private String postId; // post�� �ۼ��� ������� ���̵�
	private String userId; // Collaboration�� ������ ����� ���̵�
	private String userNickname; // ����� �г��� 
	//���� 2���� fk(�ܷ�Ű)�� �޾ƿ� ��
	
	private long collaborationNumber; // ��û�� ��ȣ, pk(�⺻Ű)
	private String collaborationTitle; // ��û�� ����, �ۼ��� post�� �̸����� �����
	private SimpleDateFormat collaborationDate; // ��û���� �ۼ��� ��¥
	private String applyContent; // ��û�� ����
}
