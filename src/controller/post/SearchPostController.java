package controller.post;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.artist.ArtistSessionUtils;
import model.Post;
import model.dao.PostDAO;

public class SearchPostController implements Controller {
	
	PostDAO postDAO = new PostDAO();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("(SearchPostController) IN");
		// 로그인 여부
		if (!ArtistSessionUtils.hasLogined(request.getSession())) { // 로그인 안되어있는 있는 경우
			return "redirect:/post/list";	
        }
	
		try {
			List<Post> postList = postDAO.searchPostTitle(request.getParameter("postTitle"));
			System.out.println("(ListPostController) postList의 길이: " + postList.size());
			request.setAttribute("postList", postList);
			request.setAttribute("search", true);
			
			return "/post/listPost.jsp";
		} catch (Exception e) {
			return "redirect:/post/list";
		}

	}

}
