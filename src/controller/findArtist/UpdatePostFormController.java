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
		// 로그인 추가
		
		if (request.getMethod().equals("GET")) {	
    		// GET request: post 수정 form 요청	
    		// 원래는 UpdateUserFormController가 처리하던 작업을 여기서 수행
			int updateId = Integer.parseInt(request.getParameter("postId"));
			System.out.println("(UpdatePostController) postId: " + updateId);
			
			Post post = postDAO.findPost(updateId);
			
			// 자기의 게시글만 수정 추가
			
			request.setAttribute("post", post);	
		}
		
		return "/findArtist/updatePost.jsp";

	}

}
