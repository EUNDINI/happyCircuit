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
		// �α��� ����
		if (!ArtistSessionUtils.hasLogined(request.getSession())) { // �α��� �ȵǾ��ִ� �ִ� ���
			return "redirect:/post/list";	
        }
		
		// �α��� �� ����� collaboration�� �ۼ��� ����� ������ �˻��ϴ� �κ�
		int collaborationId = Integer.parseInt(request.getParameter("collaborationId"));
		Collaboration collaboration = collaborationDAO.findCollaboration(collaborationId);
		String collaborationArtistId = collaboration.getCollaborationArtistId();
		HttpSession session = request.getSession();
		if (!ArtistSessionUtils.isLoginArtist(collaborationArtistId, session)) { // �α��� �� ����� post�� �ۼ��� ����� �ٸ���
			return "redirect:/collaboration/list";
		}
		
		// collaboration ����
		collaborationDAO.remove(collaborationId);
		
		return "redirect:/collaboration/list";
	}

}
