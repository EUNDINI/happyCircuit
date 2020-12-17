package controller.post;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.artist.ArtistSessionUtils;
import model.Post;
import model.dao.PostDAO;
import model.service.FindArtistManager;

public class ListPostController implements Controller {

	private PostDAO postDAO = new PostDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 구인 게시글 목록은 로그인 안해도 볼 수 있게 함

		// 나중에 페이징 추가
		
		// List<Post>를 request에 저장하여 전달
		List<Post> postList = postDAO.findPostList();
		request.setAttribute("postList", postList);
		request.setAttribute("search", false);

		return "/post/listPost.jsp";
	}

}
