package controller;
import java.util.HashMap;
import java.util.Map;

import controller.board.*;

public class RequestMapping {
	// 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
	private Map<String, Controller> mappings = new HashMap<String, Controller>();

	public void initMapping() {
		// 각 uri에 대응되는 controller 객체를 생성 및 저장
		
		//MusicBoard Mapping - 맞나?....
		mappings.put("/home", new LikeChartController());
		mappings.put("/board/boardMain", new MusicListController());
		mappings.put("/board/boardWrite/form", new ForwardController("/board/boardWrite.jsp"));
		mappings.put("/board/boardWrite", new MusicCreateController());
		mappings.put("/board/boardModify/form", new MusicUpdateController());
		mappings.put("/board/boardModify", new MusicUpdateController());
		mappings.put("/board/boardDelete", new MusicDeleteController());
		mappings.put("/board/boardSearch", new MusicSearchController());
	}

	public Controller findController(String uri) {
		// 주어진 uri에 대응되는 controller 객체를 찾아 반환
		return mappings.get(uri);
	}
}
