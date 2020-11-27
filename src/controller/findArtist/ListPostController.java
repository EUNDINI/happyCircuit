package controller.findArtist;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Post;
import model.dao.PostDAO;
import model.service.FindArtistManager;

public class ListPostController implements Controller {

	PostDAO postDAO = new PostDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 여부 확인 추가해야 함
		
		// 나중에 페이징도 추가할 수도 있음
		
		// List<Post>를 request에 저장하여 전달
		List<Post> postList = postDAO.findPostList();
		System.out.println("(ListPostController) postList의 길이: " + postList.size());
		request.setAttribute("postList", postList);

		return "/findArtist/listPost.jsp";
	}

}
