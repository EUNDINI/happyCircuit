package controller.findArtist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
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
		// 로그인 여부 추가
		
		int collaborationId = Integer.parseInt(request.getParameter("collaborationId"));
		Collaboration collaboration = collaborationDAO.findCollaboration(collaborationId);
		request.setAttribute("collaboration", collaboration);
		
		return "/findArtist/viewCollaboration.jsp";
	}

}
