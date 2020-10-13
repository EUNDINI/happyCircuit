package controller;
import java.util.HashMap;
import java.util.Map;

import controller.board.*;
import controller.findArtist.CollaborateController;
import controller.findArtist.CreateCollaborationController;
import controller.findArtist.CreatePostController;
import controller.findArtist.DeletePostController;
import controller.findArtist.ListPostController;
import controller.findArtist.SearchPostController;
import controller.findArtist.UpdatePostController;
import controller.findArtist.ViewPostController;

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
		
		// findArtist 관련 RequestMapping
		mappings.put("/findArtist/list", new ListPostController());
		mappings.put("/findArtist/create/post", new ForwardController("/findArtist/createPost.jsp"));
		mappings.put("/findArtist/create", new CreatePostController());
		mappings.put("/findArtist/view/post", new ViewPostController());
		mappings.put("/findArtist/delete/post", new DeletePostController());
		mappings.put("/findArtist/update", new UpdatePostController());
		mappings.put("/findArtist/search/post", new SearchPostController());
		mappings.put("/findArtist/collaborate", new CollaborateController());
		mappings.put("/findArtist/create/collaboration", new CreateCollaborationController());
	}

	public Controller findController(String uri) {
		// 주어진 uri에 대응되는 controller 객체를 찾아 반환
		return mappings.get(uri);
	}
}
