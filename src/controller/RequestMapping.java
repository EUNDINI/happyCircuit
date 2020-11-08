package controller;

import java.util.HashMap;
import java.util.Map;

import controller.board.*;
import controller.user.LoginController;
import controller.user.LogoutController;
import controller.user.RegisterUserController;

public class RequestMapping {
	// 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
	private Map<String, Controller> mappings = new HashMap<String, Controller>();

	public void initMapping() {
		// 각 uri에 대응되는 controller 객체를 생성 및 저장

		// Login & Logout / Register
		mappings.put("/user/login/form", new ForwardController("/user/login.jsp"));
		mappings.put("/user/login", new LoginController());
		mappings.put("/user/logout", new LogoutController());
		mappings.put("/user/register/form", new ForwardController("/user/register.jsp"));
		mappings.put("/user/register", new RegisterUserController());

		// MusicBoard Mapping - 맞나?....
		mappings.put("/home", new LikeChartController());
		mappings.put("/board/boardMain", new GetMusicListController());
		mappings.put("/board/boardWrite/form", new ForwardController("/board/boardWrite.jsp"));
		mappings.put("/board/boardWrite", new CreateMusicController());
		mappings.put("/board/boardModify/form", new UpdateMusicController());
		mappings.put("/board/boardModify", new UpdateMusicController());
		mappings.put("/board/boardDelete", new DeleteMusicController());
		mappings.put("/board/boardSearch", new SearchMusicController());
	}

	public Controller findController(String uri) {
		// 주어진 uri에 대응되는 controller 객체를 찾아 반환
		return mappings.get(uri);
	}
}
