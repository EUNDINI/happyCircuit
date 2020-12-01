package controller.post;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.artist.ArtistSessionUtils;
import model.Post;
import model.dao.PostDAO;

public class SearchPostController implements Controller {
	
	PostDAO postDAO = new PostDAO();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("(SearchPostController) IN");
		// �α��� ����
		if (!ArtistSessionUtils.hasLogined(request.getSession())) { // �α��� �ȵǾ��ִ� �ִ� ���
			return "redirect:/post/list";	
        }
	
		try {
			List<Post> postList = postDAO.searchPostTitle(request.getParameter("postTitle"));
			System.out.println("(ListPostController) postList�� ����: " + postList.size());
			request.setAttribute("postList", postList);
			request.setAttribute("search", true);
			
			return "/post/listPost.jsp";
		} catch (Exception e) {
			return "redirect:/post/list";
		}

	}

}
