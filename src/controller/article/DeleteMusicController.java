package controller.article;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.dao.MusicDAO;

public class DeleteMusicController implements Controller {

	private MusicDAO musicDAO = new MusicDAO();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 작성자와 로그인 아이디 검사 추가
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId"); 
		int musicId = Integer.parseInt(request.getParameter("musicId"));
	
		/*
		 * if (!musicDAO.isArticleWriter(musicId, userId)) return
		 * "/article/articleMain.jsp";
		 */

		musicDAO.deleteMusicArticle(musicId);
		musicDAO.deleteMusic(musicId);
		
		return "redirect:/article/articleMain";
	}
}
