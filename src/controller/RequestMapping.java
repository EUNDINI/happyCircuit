package controller;

import java.util.HashMap;
import java.util.Map;

import controller.board.*;
import controller.user.LoginController;
import controller.user.LogoutController;
import controller.user.RegisterUserController;

public class RequestMapping {
	// �� ��û uri�� ���� controller ��ü�� ������ HashMap ����
	private Map<String, Controller> mappings = new HashMap<String, Controller>();

	public void initMapping() {
		// �� uri�� �����Ǵ� controller ��ü�� ���� �� ����

		// Login & Logout / Register
		mappings.put("/user/login/form", new ForwardController("/user/login.jsp"));
		mappings.put("/user/login", new LoginController());
		mappings.put("/user/logout", new LogoutController());
		mappings.put("/user/register/form", new ForwardController("/user/register.jsp"));
		mappings.put("/user/register", new RegisterUserController());

		// MusicBoard Mapping - �³�?....
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
		// �־��� uri�� �����Ǵ� controller ��ü�� ã�� ��ȯ
		return mappings.get(uri);
	}
}
