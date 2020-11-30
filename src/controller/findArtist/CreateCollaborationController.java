package controller.findArtist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.artist.ArtistSessionUtils;
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
		// 로그인 여부
		if (!ArtistSessionUtils.hasLogined(request.getSession())) { // 로그인 안되어있는 있는 경우
			return "redirect:/findArtist/list";	
        }
		
		// GET method: 초기값 전송 및 form 화면 출력
		if (request.getMethod().equals("GET")) {
			System.out.println("(CreateCollaborationController) IN ");
			int postId = Integer.parseInt(request.getParameter("postId"));
			System.out.println("(CreateCollaborationController) postId: " + postId);
			
			try {
				Post post = postDAO.findPost(postId);
				request.setAttribute("post", post);	
			} catch (Exception e) {
				return "redirect:/findArtist/view/post?postId=" + postId;
			}
			
			return "/findArtist/createCollaboration.jsp";
		}
		
		// POST method: 작성된 데이터를 받아와 DB 갱신
		// httpsession을 받아와서 collaborationArtistId(협업신청한 사람의 id) 받아옴
		HttpSession session = request.getSession();
		String collaborationArtistId = ArtistSessionUtils.getLoginArtistId(session);
		System.out.println("(CreateCollaborationController) collaborationArtistId: " + collaborationArtistId);
		
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
			System.out.println("(CreateCollaborationController) collaboration 객체 생성 완료 ");
			
			int collaborationId = collaborationDAO.create(collaboration);
			request.setAttribute("collaborationId", collaborationId);
			
			return "redirect:/findArtist/view/collaboration?collaborationId=" + collaborationId;
		} catch (Exception e) {
			return "/findArtist/createCollaboration.jsp";
		}
		
	}

}
