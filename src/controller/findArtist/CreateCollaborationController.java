package controller.findArtist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Artist;
import model.Collaboration;
import model.Post;
import model.dao.ArtistDAO;
import model.dao.CollaborationDAO;
import model.dao.PostDAO;

public class CreateCollaborationController implements Controller {

	PostDAO postDAO = new PostDAO();
	ArtistDAO artistDAO = new ArtistDAO();
	CollaborationDAO collaborationDAO = new CollaborationDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// �α��� ���� �߰�
		
		// httpsession�� �޾ƿͼ� collaborationArtistId(������û�� ����� id) �޾ƿ;� ��
		HttpSession session = request.getSession();
		String collaborationArtistId = (String)session.getAttribute("artistId");
		System.out.println("(CreateCollaborationController) collaborationArtistId: " + collaborationArtistId);
		
		int postId = Integer.parseInt(request.getParameter("postId"));
		Post post = postDAO.findPost(postId);
		String postArtistId = post.getArtistId();
		
		String collaborationTitle = request.getParameter("collaborationTitle");
		String collaborationContent = request.getParameter("collaborationContent");
		
		Collaboration collaboration = new Collaboration(
				0, collaborationTitle,
				null, collaborationContent,
				postId, postArtistId, "artist2"); // "artist2" �κ��� ���߿�  ���� �޾ƿͼ� collaborationArtistId�� ��ü
		System.out.println("(CreateCollaborationController) collaboration ��ü ���� �Ϸ� ");
		
		int collaborationId = collaborationDAO.create(collaboration);
		request.setAttribute("collaborationId", collaborationId);
		
//		return "/findArtist/view/collaboration?collaborationId=" + collaborationId;
		return "redirect:/findArtist/view/collaboration?collaborationId=" + collaborationId;
	}

}
