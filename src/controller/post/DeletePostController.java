package controller.post;

import java.io.PrintWriter;
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
		// �α��� ����
		if (!ArtistSessionUtils.hasLogined(request.getSession())) { // �α��� �ȵǾ��ִ� �ִ� ���
			return "redirect:/post/list";	
        }
		
		// �α��� �� ����� post�� �ۼ��� ����� ������ �˻��ϴ� �κ� �߰�
		int postId = Integer.parseInt(request.getParameter("postId"));
		Post post = postDAO.findPost(postId);
		String artistId = post.getArtistId();
		HttpSession session = request.getSession();
		if (!ArtistSessionUtils.isLoginArtist(artistId, session)) { // �α��� �� ����� post�� �ۼ��� ����� �ٸ���
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('�ڽ��� �ۼ��� �Խñ۸� ������ �����մϴ�.'); history.go(-1);</script>");
			out.flush();
			return "redirect:/post/list";
		}
		
		// Post�� �ڽĵ��� Collabortion�� �����ϴ� �۾� ���� ����
		List<Collaboration> CollaborationList = collaborationDAO.findCollaborationList(postId);
		for (int i = 0; i < CollaborationList.size(); i++) {
			collaborationDAO.remove(CollaborationList.get(i).getCollaborationId());
		}
		
		// Post ����
		postDAO.remove(postId);
		
		return "redirect:/post/list";
	}

}
