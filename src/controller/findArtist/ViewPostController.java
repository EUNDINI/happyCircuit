package controller.findArtist;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Post;
import model.dao.PostDAO;
import model.service.FindArtistManager;

public class ViewPostController implements Controller {

	PostDAO postDAO = new PostDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 여부 확인 추가해야 함
		
		int postId = Integer.parseInt(request.getParameter("postId"));
		Post post = postDAO.findPost(postId);
		request.setAttribute("post", post);
		
		postDAO.updateView(post);
		
		return "/findArtist/viewPost.jsp";
	}

}