package controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dao.MusicDAO;

public class MusicDeleteController implements Controller {

	private MusicDAO musicDAO = new MusicDAO();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 작성자와 로그인 아이디 검사 추가
		
		int musicId = Integer.parseInt(request.getParameter("musicId"));
		musicDAO.deleteMusicBoard(musicId);
		
		return "redirect:/board/boardMain.jsp";
	}
}
