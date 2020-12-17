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
		// �α��� ����
		HttpSession session = request.getSession();
		if (!ArtistSessionUtils.hasLogined(session)) { // �α��� �ȵǾ��ִ� �ִ� ���
			System.out.println("(CreatePostController) session: " + session);
			return "redirect:/post/list";	
        }
		
		// List<Post>�� request�� �����Ͽ� ����
		String artistId = ArtistSessionUtils.getLoginArtistId(session);
		List<Collaboration> collaborationList = collaborationDAO.findCollaborationList(artistId);
		
		request.setAttribute("collaborationList", collaborationList);
		request.setAttribute("search", false);

		return "/collaboration/listCollaboration.jsp";
	}

}
