package controller.collaboration;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.artist.ArtistSessionUtils;
import model.Collaboration;
import model.Post;
import model.dao.CollaborationDAO;
import model.dao.PostDAO;

public class SearchCollaborationController implements Controller {
	
	private PostDAO postDAO = new PostDAO();
	private CollaborationDAO collaborationDAO = new CollaborationDAO();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 여부
		if (!ArtistSessionUtils.hasLogined(request.getSession())) { // 로그인 안되어있는 있는 경우
			return "redirect:/collaboration/list";	
        }
	
		try {
			List<Collaboration> collaborationList = collaborationDAO.searchCollaborationTitle(request.getParameter("collaborationTitle"));
			request.setAttribute("collaborationList", collaborationList);
			request.setAttribute("search", true);
			
			return "/collaboration/listCollaboration.jsp";
		} catch (Exception e) {
			return "redirect:/collaboration/list";
		}

	}

}
