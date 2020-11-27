package controller.findArtist;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Collaboration;
import model.dao.CollaborationDAO;
import model.dao.PostDAO;

public class DeletePostController implements Controller {

	private PostDAO postDAO = new PostDAO();
	private CollaborationDAO collaborationDAO = new CollaborationDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 된 사람과 post를 작성한 사람이 같은지 검사하는 부분 추가
		HttpSession session = request.getSession();
		
		int postId = Integer.parseInt(request.getParameter("postId"));
		
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
