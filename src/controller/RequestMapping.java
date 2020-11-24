package controller;

import java.util.HashMap;
import java.util.Map;

import controller.article.*;
import controller.artist.*;

public class RequestMapping {
	// �� ��û uri�� ���� controller ��ü�� ������ HashMap ����
	private Map<String, Controller> mappings = new HashMap<String, Controller>();

	public void initMapping() {
		// Login & Logout / Register
		mappings.put("/artist/login/form", new ForwardController("/artist/login_register.jsp"));
		mappings.put("/artist/login", new LoginController());
		mappings.put("/artist/logout", new LogoutController());
		//mappings.put("/artist/register/form", new ForwardController("/artist/login_register.jsp"));
		mappings.put("/artist/register", new RegisterUserController());

		// MusicBoard Mapping
		mappings.put("/home", new LikeChartController());
		mappings.put("/article/articleMain", new GetMusicListController());
		
		mappings.put("/article/articleWrite/form", new ForwardController("/article/articleWrite.jsp"));
		mappings.put("/article/articleWrite", new CreateMusicController()); //ok
		mappings.put("/article/articleNthWrite/form", new ForwardController("/article/articleNthWrite.jsp"));
		mappings.put("/article/articleNthWrite", new CreateNthMusicController());
		
		mappings.put("/article/articleRead", new ReadMusicController());
		
		mappings.put("/article/articleModify/form", new UpdateMusicController());
		mappings.put("/article/articleModify", new UpdateMusicController());
		
		mappings.put("/article/articleDelete", new DeleteMusicController());
		
		mappings.put("/article/articleSearch", new SearchMusicController());
	}

	public Controller findController(String uri) {
		// �־��� uri�� �����Ǵ� controller ��ü�� ã�� ��ȯ
		return mappings.get(uri);
	}
}
