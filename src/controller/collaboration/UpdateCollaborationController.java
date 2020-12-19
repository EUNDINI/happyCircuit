package controller.collaboration;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

public class UpdateCollaborationController implements Controller {

	private ArtistDAO artistDAO = new ArtistDAO();
	private CollaborationDAO collaborationDAO = new CollaborationDAO();
	private DMDAO dmDAO = new DMDAO();
	
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
		String artistId = collaboration.getPostArtistId();
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
		
		List<Artist> artistList = new ArrayList<Artist>();
		Artist collaborationsArtist = artistDAO.findArtistById(collaborationArtistId);
		artistList.add(collaborationsArtist); //���� �α��ε� artist
		
		Artist artist = artistDAO.findArtistById(artistId);
		artistList.add(artist); //��� artist
		
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
				0, updateCollaborationContent, 
				null, collaborationsArtist, dmId);
		
		try {
			dmDAO.createMessage(msg);
		} catch (Exception e) {
			return "/collaboration/createCollaboration.jsp";
		}
		
		Collaboration updateCollaboration = new Collaboration(
				collaborationId, updateCollaborationTitle,
				collaboration.getCollaborationDate(), updateCollaborationContent,
				collaboration.getPostId(), collaboration.getPostArtistId(),
				collaboration.getCollaborationArtistId());  
		collaborationDAO.update(updateCollaboration);
		
		return "redirect:/collaboration/view?collaborationId=" + collaborationId;
	}

}
