package controller.collaboration;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

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

public class UpdateCollaborationController implements Controller {

	private CollaborationDAO collaborationDAO = new CollaborationDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// �α��� ����
		HttpSession session = request.getSession();
		if (!ArtistSessionUtils.hasLogined(session)) { // �α��� �ȵǾ��ִ� �ִ� ���
			return "redirect:/post/list";	
        }
		
		// �α��� �� ����� collaboration�� �ۼ��� ����� ������ �˻��ϴ� �κ�
		int collaborationId = Integer.parseInt(request.getParameter("collaborationId"));
		Collaboration collaboration = collaborationDAO.findCollaboration(collaborationId);
		String collaborationArtistId = collaboration.getCollaborationArtistId();
		if (!ArtistSessionUtils.isLoginArtist(collaborationArtistId, session)) { // �α��� �� ����� post�� �ۼ��� ����� �ٸ���
			return "redirect:/collaboration/list";
		}

		// GET method: �ʱⰪ ���� �� form ȭ�� ���
		if (request.getMethod().equals("GET")) {
			request.setAttribute("collaboration", collaboration);	
			return "/collaboration/updateCollaboration.jsp";
		}

		// POST method: ������Ʈ �� �����͸� �޾ƿ� DB ����
		String updateCollaborationTitle = request.getParameter("collaborationTitle");
		String updateCollaborationContent = request.getParameter("collaborationContent");
		
		Collaboration updateCollaboration = new Collaboration(
				collaborationId, updateCollaborationTitle,
				collaboration.getCollaborationDate(), updateCollaborationContent,
				collaboration.getPostId(), collaboration.getPostArtistId(),
				collaboration.getCollaborationArtistId());  
		collaborationDAO.update(updateCollaboration);
		
		return "redirect:/collaboration/view?collaborationId=" + collaborationId;
	}

}
