package controller;

import java.util.HashMap;
import java.util.Map;

import controller.findArtist.CollaborationController;
import controller.findArtist.CreateCollaborationController;
import controller.findArtist.CreatePostController;
import controller.findArtist.DeletePostController;
import controller.findArtist.ListPostController;
import controller.findArtist.SearchPostController;
import controller.findArtist.UpdatePostController;
import controller.findArtist.UpdatePostFormController;
import controller.findArtist.ViewCollaborationController;
import controller.findArtist.ViewPostController;
import controller.myPage.*;
import controller.DM.*;
import controller.article.CreateMusicController;
import controller.article.DeleteMusicController;
import controller.article.GetMusicListController;
import controller.article.LikeChartController;
import controller.article.SearchMusicController;
import controller.article.UpdateMusicController;
import controller.artist.LoginController;
import controller.artist.LogoutController;
import controller.artist.RegisterArtistController;

public class RequestMapping {
	// 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
	private Map<String, Controller> mappings = new HashMap<String, Controller>();

	public void initMapping() {
		// 각 uri에 대응되는 controller 객체를 생성 및 저장
		
		// Login & Logout / Register
		mappings.put("/artist/login/form", new ForwardController("/artist/login_register.jsp"));
		mappings.put("/artist/login", new LoginController());
		mappings.put("/artist/logout", new LogoutController());
		mappings.put("/artist/register/form", new ForwardController("/artist/artist_register.jsp"));
		mappings.put("/artist/register", new RegisterArtistController());

		// MusicBoard Mapping - 맞나?....
		mappings.put("/home", new LikeChartController());
		mappings.put("/board/boardMain", new GetMusicListController());
		mappings.put("/board/boardWrite/form", new ForwardController("/board/boardWrite.jsp"));
		mappings.put("/board/boardWrite", new CreateMusicController());
		mappings.put("/board/boardModify/form", new UpdateMusicController());
		mappings.put("/board/boardModify", new UpdateMusicController());
		mappings.put("/board/boardDelete", new DeleteMusicController());
		mappings.put("/board/boardSearch", new SearchMusicController());

		// findArtist 관련 RequestMapping
		mappings.put("/findArtist/list", new ListPostController());
		mappings.put("/findArtist/create/post", new ForwardController("/findArtist/createPost.jsp"));
		mappings.put("/findArtist/create", new CreatePostController());
		mappings.put("/findArtist/view/post", new ViewPostController());
		mappings.put("/findArtist/delete/post", new DeletePostController());
		mappings.put("/findArtist/update", new UpdatePostFormController());
		mappings.put("/findArtist/update/post", new UpdatePostController());
		mappings.put("/findArtist/search/post", new SearchPostController());
		mappings.put("/findArtist/collaborate", new CollaborationController());
		mappings.put("/findArtist/create/collaboration", new CreateCollaborationController());
		mappings.put("/findArtist/view/collaboration", new ViewCollaborationController());
	


		//myPage
		mappings.put("/mypage", new MyPageController());
		mappings.put("/mypage/update", new UpdateArtistController());;
		mappings.put("/mypage/recommendMusic", new RecommendMusicController());

		//DM

		mappings.put("/DM", new ListDMController());
		mappings.put("/DM/create", new CreateDMController());
		mappings.put("/DM/room", new ViewDMController());
		mappings.put("/DM/delete", new DeleteDMController());
		mappings.put("/DM/message/create", new CreateMessageController());

		//user.......... 이거 일단 마이페이지에 있는데 어카지
		mappings.put("/artist/delete", new DeleteArtistController());
	}

	public Controller findController(String uri) {
		// 주어진 uri에 대응되는 controller 객체를 찾아 반환
		return mappings.get(uri);
	}
}
