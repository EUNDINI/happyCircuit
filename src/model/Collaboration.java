package model;

import java.io.File;
import java.text.SimpleDateFormat;

public class Collaboration {
	private String postId; // post를 작성한 사용자의 아이디
	private String userId; // Collaboration을 보내는 사용자 아이디
	private String userNickname; // 사용자 닉네임 
	//위에 2개는 fk(외래키)로 받아올 거
	
	private long collaborationNumber; // 신청폼 번호, pk(기본키)
	private String collaborationTitle; // 신청폼 제목, 작성한 post의 이름으로 저장됨
	private SimpleDateFormat collaborationDate; // 신청폼을 작성한 날짜
	private String applyContent; // 신청폼 내용
}
