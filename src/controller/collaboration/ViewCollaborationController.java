package controller.collaboration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.artist.ArtistSessionUtils;
import model.Collaboration;
import model.Post;
import model.dao.ArtistDAO;
import model.dao.CollaborationDAO;
import model.dao.PostDAO;

public class ViewCollaborationController implements Controller {

	PostDAO postDAO = new PostDAO();
	ArtistDAO artistDAO = new ArtistDAO();
	CollaborationDAO collaborationDAO = new CollaborationDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 여부
		if (!ArtistSessionUtils.hasLogined(request.getSession())) { // 로그인 안되어있는 있는 경우
			return "redirect:/post/list";	
        }
		
		int collaborationId = Integer.parseInt(request.getParameter("collaborationId"));
		Collaboration collaboration = collaborationDAO.findCollaboration(collaborationId);
		request.setAttribute("collaboration", collaboration);
		
		return "/collaboration/viewCollaboration.jsp";
	}

}
