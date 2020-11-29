package controller.findArtist;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.artist.ArtistSessionUtils;
import model.Post;
import model.dao.PostDAO;
import model.service.FindArtistManager;

public class ListPostController implements Controller {

	PostDAO postDAO = new PostDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// ���� �Խñ� ����� �α��� ���ص� �� �� �ְ� ��
		System.out.println("(CreatePostController): IN");

		// ���߿� ����¡ �߰�
		
		// List<Post>�� request�� �����Ͽ� ����
		List<Post> postList = postDAO.findPostList();
		System.out.println("(ListPostController) postList�� ����: " + postList.size());
		request.setAttribute("postList", postList);

		return "/findArtist/listPost.jsp";
	}

}
