package controller.collaboration;

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

public class DeleteCollaborationController implements Controller {

	private PostDAO postDAO = new PostDAO();
	private CollaborationDAO collaborationDAO = new CollaborationDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 여부
		if (!ArtistSessionUtils.hasLogined(request.getSession())) { // 로그인 안되어있는 있는 경우
			return "redirect:/post/list";	
        }
		
		// 로그인 된 사람과 collaboration을 작성한 사람이 같은지 검사하는 부분
		int collaborationId = Integer.parseInt(request.getParameter("collaborationId"));
		Collaboration collaboration = collaborationDAO.findCollaboration(collaborationId);
		String collaborationArtistId = collaboration.getCollaborationArtistId();
		HttpSession session = request.getSession();
		if (!ArtistSessionUtils.isLoginArtist(collaborationArtistId, session)) { // 로그인 된 사람과 post를 작성한 사람이 다르면
			return "redirect:/collaboration/list";
		}
		
		// collaboration 삭제
		collaborationDAO.remove(collaborationId);
		
		return "redirect:/collaboration/list";
	}

}
