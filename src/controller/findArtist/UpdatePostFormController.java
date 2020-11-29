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
		// 로그인 여부
		if (!ArtistSessionUtils.hasLogined(request.getSession())) { // 로그인 안되어있는 있는 경우
			return "redirect:/findArtist/list";	
        }
		
		// 로그인 된 사람과 post를 작성한 사람이 같은지 검사하는 부분 추가
		int updateId = Integer.parseInt(request.getParameter("postId"));
		System.out.println("(UpdatePostController) postId: " + updateId);
		Post post = postDAO.findPost(updateId);
		String artistId = post.getArtistId();
		HttpSession session = request.getSession();
		if (!ArtistSessionUtils.isLoginArtist(artistId, session)) { // 로그인 된 사람과 post를 작성한 사람이 다르면
			return "redirect:/findArtist/list";	
		}
		
		request.setAttribute("post", post);	
		return "/findArtist/updatePost.jsp";

	}

}
