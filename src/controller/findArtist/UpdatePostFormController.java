package controller.findArtist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Post;
import model.dao.PostDAO;

public class UpdatePostFormController implements Controller {

	PostDAO postDAO = new PostDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// �α��� �߰�
		
		if (request.getMethod().equals("GET")) {	
    		// GET request: post ���� form ��û	
    		// ������ UpdateUserFormController�� ó���ϴ� �۾��� ���⼭ ����
			int updateId = Integer.parseInt(request.getParameter("postId"));
			System.out.println("(UpdatePostController) postId: " + updateId);
			
			Post post = postDAO.findPost(updateId);
			
			// �ڱ��� �Խñ۸� ���� �߰�
			
			request.setAttribute("post", post);	
		}
		
		return "/findArtist/updatePost.jsp";

	}

}
