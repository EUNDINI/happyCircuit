package controller.findArtist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.artist.ArtistSessionUtils;
import model.Post;
import model.dao.CollaborationDAO;
import model.dao.PostDAO;

public class CollaborationController implements Controller {

	PostDAO postDAO = new PostDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 여부
		if (!ArtistSessionUtils.hasLogined(request.getSession())) { // 로그인 안되어있는 있는 경우
			return "redirect:/findArtist/list";	
        }
		
		System.out.println("(CollaborationController) IN ");
		int postId = Integer.parseInt(request.getParameter("postId"));
		System.out.println("(CollaborationController) postId: " + postId);
		
		Post post = postDAO.findPost(postId);
		request.setAttribute("post", post);	
		
		return "/findArtist/createCollaboration.jsp";
		
	}

}