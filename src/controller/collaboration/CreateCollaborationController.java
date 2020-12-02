package controller.collaboration;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.artist.ArtistSessionUtils;
import model.Artist;
import model.Collaboration;
import model.DM;
import model.Message;
import model.Post;
import model.dao.ArtistDAO;
import model.dao.CollaborationDAO;
import model.dao.DMDAO;
import model.dao.PostDAO;

public class CreateCollaborationController implements Controller {

	PostDAO postDAO = new PostDAO();
	ArtistDAO artistDAO = new ArtistDAO();
	CollaborationDAO collaborationDAO = new CollaborationDAO();
	private DMDAO dmDAO = new DMDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// �α��� ����
		if (!ArtistSessionUtils.hasLogined(request.getSession())) { // �α��� �ȵǾ��ִ� �ִ� ���
			return "redirect:/post/list";	
        }
		
		// GET method: �ʱⰪ ���� �� form ȭ�� ���
		if (request.getMethod().equals("GET")) {
			int postId = Integer.parseInt(request.getParameter("postId"));
			
			try {
				Post post = postDAO.findPost(postId);
				request.setAttribute("post", post);	
			} catch (Exception e) {
				return "redirect:/findArtist/view/post?postId=" + postId;
			}
			
			return "/collaboration/createCollaboration.jsp";
		}
		
		// POST method: �ۼ��� �����͸� �޾ƿ� DB ����
		// httpsession�� �޾ƿͼ� collaborationArtistId(������û�� ����� id) �޾ƿ�
		HttpSession session = request.getSession();
		String collaborationArtistId = ArtistSessionUtils.getLoginArtistId(session);

		List<Artist> artistList = new ArrayList<Artist>();
		Artist collaborationsArtist = artistDAO.findArtistById(collaborationArtistId);
		artistList.add(collaborationsArtist); //���� �α��ε� artist
		
		String artistId = request.getParameter("artistId");
		artistList.add(artistDAO.findArtistById(artistId)); //��� artist
		
		int dmId = dmDAO.findMembership(artistList);
		if (dmId == 0) {
			try {
				DM dm = new DM(0, artistList);
				dmDAO.createDMAndMembership(dm);
				dmId = dm.getDmId();
			} catch (Exception e) {
				return "/collaboration/createCollaboration.jsp";
			}
		}

		Message msg = new Message(
				0, request.getParameter("collaborationContent"), 
				null, collaborationsArtist, dmId);
		
		try {
			dmDAO.createMessage(msg);
		} catch (Exception e) {
			return "/collaboration/createCollaboration.jsp";
		}
		
		try {
			int postId = Integer.parseInt(request.getParameter("postId"));
			Post post = postDAO.findPost(postId);
			String postArtistId = post.getArtistId();
			
			String collaborationTitle = request.getParameter("collaborationTitle");
			String collaborationContent = request.getParameter("collaborationContent");
			
			Collaboration collaboration = new Collaboration(
					0, collaborationTitle,
					null, collaborationContent,
					postId, postArtistId, collaborationArtistId);
			
			int collaborationId = collaborationDAO.create(collaboration);
			request.setAttribute("collaborationId", collaborationId);
			
			return "redirect:/collaboration/view?collaborationId=" + collaborationId;
		} catch (Exception e) {
			return "/collaboration/createCollaboration.jsp";
		}
		
	}

}
