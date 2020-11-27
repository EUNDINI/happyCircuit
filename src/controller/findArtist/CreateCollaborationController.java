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
		// 로그인 여부 추가
		
		// httpsession을 받아와서 collaborationArtistId(협업신청한 사람의 id) 받아와야 함
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
				postId, postArtistId, "artist2"); // "artist2" 부분은 나중에  세션 받아와서 collaborationArtistId로 대체
		System.out.println("(CreateCollaborationController) collaboration 객체 생성 완료 ");
		
		int collaborationId = collaborationDAO.create(collaboration);
		request.setAttribute("collaborationId", collaborationId);
		
//		return "/findArtist/view/collaboration?collaborationId=" + collaborationId;
		return "redirect:/findArtist/view/collaboration?collaborationId=" + collaborationId;
	}

}
