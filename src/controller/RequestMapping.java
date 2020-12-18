package controller;

import java.util.HashMap;
import java.util.Map;

import controller.myPage.*;
import controller.post.CreatePostController;
import controller.post.DeletePostController;
import controller.post.ListPostController;
import controller.post.SearchPostController;
import controller.post.UpdatePostController;
import controller.post.ViewPostController;
import controller.DM.*;
import controller.article.CreateMusicController;
import controller.article.CreateNthMusicController;
import controller.article.DeleteMusicController;
import controller.article.GetMusicListController;
import controller.article.HistoryMusicController;
import controller.article.LikeChartController;
import controller.article.ReadMusicController;
import controller.article.SearchMusicController;
import controller.article.UpdateMusicController;
import controller.artist.DeleteArtistController;
import controller.artist.LoginController;
import controller.artist.LogoutController;
import controller.artist.RegisterArtistController;
import controller.artist.UpdateArtistController;
import controller.collaboration.CreateCollaborationController;
import controller.collaboration.DeleteCollaborationController;
import controller.collaboration.ListCollaborationController;
import controller.collaboration.SearchCollaborationController;
import controller.collaboration.UpdateCollaborationController;
import controller.collaboration.ViewCollaborationController;

public class RequestMapping {
	// 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
	private Map<String, Controller> mappings = new HashMap<String, Controller>();

	public void initMapping() {
		// 각 uri에 대응되는 controller 객체를 생성 및 저장
		
		// Login & Logout / Register
		mappings.put("/artist/login/form", new ForwardController("/artist/login_register.jsp"));
		mappings.put("/artist/login", new LoginController());
		mappings.put("/artist/logout", new LogoutController());
		//mappings.put("/artist/register/form", new ForwardController("/artist/login_register.jsp"));
		mappings.put("/artist/register", new RegisterArtistController());

		// MusicBoard Mapping
		mappings.put("/home", new LikeChartController());
		mappings.put("/article/articleMain", new GetMusicListController());
		
		mappings.put("/article/articleWrite/form", new ForwardController("/article/articleWrite.jsp"));
		mappings.put("/article/articleWrite", new CreateMusicController()); //ok
		mappings.put("/article/articleNthWrite/form", new ForwardController("/article/articleNthWrite.jsp"));
		mappings.put("/article/articleNthWrite", new CreateNthMusicController());
		
		mappings.put("/article/articleRead", new ReadMusicController());
		mappings.put("/article/articleHistory", new HistoryMusicController());
		
		mappings.put("/article/articleModify/form", new UpdateMusicController());
		mappings.put("/article/articleModify", new UpdateMusicController());
		
		mappings.put("/article/articleDelete", new DeleteMusicController());
		
		mappings.put("/article/articleSearch", new SearchMusicController());

		// findArtist의 post 관련 RequestMapping	
		mappings.put("/post/list", new ListPostController());
		mappings.put("/post/create/form", new ForwardController("/post/createPost.jsp"));
		mappings.put("/post/create/post", new CreatePostController());
		mappings.put("/post/view", new ViewPostController());
		mappings.put("/post/delete", new DeletePostController());
		mappings.put("/post/update", new UpdatePostController());
		mappings.put("/post/search", new SearchPostController());
		
		//findArtist의 collaboration 관련 RequestMapping	
		mappings.put("/collaboration/list", new ListCollaborationController());
		mappings.put("/collaboration/create", new CreateCollaborationController());
		mappings.put("/collaboration/view", new ViewCollaborationController());
		mappings.put("/collaboration/delete", new DeleteCollaborationController());
		mappings.put("/collaboration/update", new UpdateCollaborationController());
		mappings.put("/collaboration/search", new SearchCollaborationController());
	
		//myPage
		mappings.put("/mypage", new MyPageController());
		mappings.put("/mypage/update", new UpdateArtistController());;
		mappings.put("/mypage/recommendMusic", new RecommendMusicController());

		//DM
		mappings.put("/DM/list", new ListDMController());
		mappings.put("/DM/create", new CreateDMController());
		mappings.put("/DM/room", new ViewDMController());
		mappings.put("/DM/delete", new DeleteDMController());
		mappings.put("/DM/message/create", new CreateMessageController());
		mappings.put("/DM/message/delete", new DeleteMessageController());

		//user.......... 이거 일단 마이페이지에 있는데 어카지
		mappings.put("/artist/delete", new DeleteArtistController());
	}

	public Controller findController(String uri) {
		// 주어진 uri에 대응되는 controller 객체를 찾아 반환
		return mappings.get(uri);
	}
}
