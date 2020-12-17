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
		// 로그인 여부
		HttpSession session = request.getSession();
		if (!ArtistSessionUtils.hasLogined(session)) { // 로그인 안되어있는 있는 경우
			return "redirect:/post/list";	
        }
		
		// 로그인 된 사람과 collaboration을 작성한 사람이 같은지 검사하는 부분
		int collaborationId = Integer.parseInt(request.getParameter("collaborationId"));
		Collaboration collaboration = collaborationDAO.findCollaboration(collaborationId);
		String collaborationArtistId = collaboration.getCollaborationArtistId();
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
		
		Collaboration updateCollaboration = new Collaboration(
				collaborationId, updateCollaborationTitle,
				collaboration.getCollaborationDate(), updateCollaborationContent,
				collaboration.getPostId(), collaboration.getPostArtistId(),
				collaboration.getCollaborationArtistId());  
		collaborationDAO.update(updateCollaboration);
		
		return "redirect:/collaboration/view?collaborationId=" + collaborationId;
	}

}
