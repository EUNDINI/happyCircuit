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
		// 로그인 여부
		HttpSession session = request.getSession();
		if (!ArtistSessionUtils.hasLogined(session)) { // 로그인 안되어있는 있는 경우
			return "redirect:/post/list";	
        }
		
		// 로그인 된 사람과 collaboration을 작성한 사람이 같은지 검사하는 부분
		int collaborationId = Integer.parseInt(request.getParameter("collaborationId"));
		Collaboration collaboration = collaborationDAO.findCollaboration(collaborationId);
		String collaborationArtistId = collaboration.getCollaborationArtistId();
		String artistId = collaboration.getPostArtistId();
		if (!ArtistSessionUtils.isLoginArtist(collaborationArtistId, session)) { // 로그인 된 사람과 post를 작성한 사람이 다르면
			return "redirect:/collaboration/list";
		}

		// GET method: 초기값 전송 및 form 화면 출력
		if (request.getMethod().equals("GET")) {
			request.setAttribute("collaboration", collaboration);	
			return "/collaboration/updateCollaboration.jsp";
		}

		// POST method: 업데이트 된 데이터를 받아와 DB 갱신
		String updateCollaborationTitle = request.getParameter("collaborationTitle");
		String updateCollaborationContent = request.getParameter("collaborationContent");
		
		List<Artist> artistList = new ArrayList<Artist>();
		Artist collaborationsArtist = artistDAO.findArtistById(collaborationArtistId);
		artistList.add(collaborationsArtist); //현재 로그인된 artist
		
		Artist artist = artistDAO.findArtistById(artistId);
		artistList.add(artist); //상대 artist
		
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
