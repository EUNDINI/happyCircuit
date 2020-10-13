package controller;
import java.util.HashMap;
import java.util.Map;

import controller.board.*;

public class RequestMapping {
	// �� ��û uri�� ���� controller ��ü�� ������ HashMap ����
	private Map<String, Controller> mappings = new HashMap<String, Controller>();

	public void initMapping() {
		// �� uri�� �����Ǵ� controller ��ü�� ���� �� ����
		
		//MusicBoard Mapping - �³�?....
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
		// �־��� uri�� �����Ǵ� controller ��ü�� ã�� ��ȯ
		return mappings.get(uri);
	}
}
