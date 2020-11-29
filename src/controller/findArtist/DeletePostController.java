package controller.findArtist;

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

public class DeletePostController implements Controller {

	private PostDAO postDAO = new PostDAO();
	private CollaborationDAO collaborationDAO = new CollaborationDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 여부
		if (!ArtistSessionUtils.hasLogined(request.getSession())) { // 로그인 안되어있는 있는 경우
			return "redirect:/findArtist/list";	
        }
		
		// 로그인 된 사람과 post를 작성한 사람이 같은지 검사하는 부분 추가
		int postId = Integer.parseInt(request.getParameter("postId"));
		Post post = postDAO.findPost(postId);
		String artistId = post.getArtistId();
		HttpSession session = request.getSession();
		if (!ArtistSessionUtils.isLoginArtist(artistId, session)) { // 로그인 된 사람과 post를 작성한 사람이 다르면
			return "redirect:/findArtist/list";
		}
		
		// Post의 자식들인 Collabortion을 삭제하는 작업 먼저 수행
		List<Collaboration> CollaborationList = collaborationDAO.findCollaborationList(postId);
		for (int i = 0; i < CollaborationList.size(); i++) {
			collaborationDAO.remove(CollaborationList.get(i).getCollaborationId());
		}
		
		// Post 삭제
		postDAO.remove(postId);
		
		return "redirect:/findArtist/list";
	}

}
