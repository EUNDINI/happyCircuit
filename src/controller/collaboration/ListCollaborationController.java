package controller.collaboration;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.artist.ArtistSessionUtils;
import model.Collaboration;
import model.Post;
import model.dao.CollaborationDAO;
import model.dao.PostDAO;
import model.service.FindArtistManager;

public class ListCollaborationController implements Controller {

	private CollaborationDAO collaborationDAO = new CollaborationDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 여부
		HttpSession session = request.getSession();
		if (!ArtistSessionUtils.hasLogined(session)) { // 로그인 안되어있는 있는 경우
			System.out.println("(CreatePostController) session: " + session);
			return "redirect:/post/list";	
        }
		
		// List<Post>를 request에 저장하여 전달
		String artistId = ArtistSessionUtils.getLoginArtistId(session);
		List<Collaboration> collaborationList = collaborationDAO.findCollaborationList(artistId);
		
		request.setAttribute("collaborationList", collaborationList);
		request.setAttribute("search", false);

		return "/collaboration/listCollaboration.jsp";
	}

}
