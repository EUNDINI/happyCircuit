package controller.post;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.artist.ArtistSessionUtils;
import model.Post;
import model.dao.PostDAO;
import model.service.FindArtistManager;

public class ViewPostController implements Controller {

	private PostDAO postDAO = new PostDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// �α��� ����
		if (!ArtistSessionUtils.hasLogined(request.getSession())) { // �α��� �ȵǾ��ִ� �ִ� ���
			return "redirect:/post/list";	
        }
		
		int postId = Integer.parseInt(request.getParameter("postId"));
		Post post = postDAO.findPost(postId);
		request.setAttribute("post", post);
		
		postDAO.updateView(post);
		
		return "/post/viewPost.jsp";
	}

}