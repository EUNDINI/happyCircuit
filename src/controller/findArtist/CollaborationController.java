package controller.findArtist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Post;
import model.dao.CollaborationDAO;
import model.dao.PostDAO;

public class CollaborationController implements Controller {

	PostDAO postDAO = new PostDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 추가
		
		System.out.println("(CollaborationController) IN ");
		int postId = Integer.parseInt(request.getParameter("postId"));
		System.out.println("(CollaborationController) postId: " + postId);
		
		Post post = postDAO.findPost(postId);
		request.setAttribute("post", post);	
		
		return "/findArtist/createCollaboration.jsp";
		
	}

}