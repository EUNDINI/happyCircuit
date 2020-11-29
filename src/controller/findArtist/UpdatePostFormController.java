package controller.findArtist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.artist.ArtistSessionUtils;
import model.Post;
import model.dao.PostDAO;

public class UpdatePostFormController implements Controller {

	PostDAO postDAO = new PostDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// �α��� ����
		if (!ArtistSessionUtils.hasLogined(request.getSession())) { // �α��� �ȵǾ��ִ� �ִ� ���
			return "redirect:/findArtist/list";	
        }
		
		// �α��� �� ����� post�� �ۼ��� ����� ������ �˻��ϴ� �κ� �߰�
		int updateId = Integer.parseInt(request.getParameter("postId"));
		System.out.println("(UpdatePostController) postId: " + updateId);
		Post post = postDAO.findPost(updateId);
		String artistId = post.getArtistId();
		HttpSession session = request.getSession();
		if (!ArtistSessionUtils.isLoginArtist(artistId, session)) { // �α��� �� ����� post�� �ۼ��� ����� �ٸ���
			return "redirect:/findArtist/list";	
		}
		
		request.setAttribute("post", post);	
		return "/findArtist/updatePost.jsp";

	}

}
