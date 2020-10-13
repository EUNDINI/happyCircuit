package controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dao.MusicDAO;
import model.dto.MusicBoard;

public class MusicSearchController implements Controller {
	private MusicDAO musicDAO = new MusicDAO();
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String condition = request.getParameter("condition");
		String search = request.getParameter("search");
		
		List<MusicBoard> musicBoardList = musicDAO.SearchMusicBoard(condition, search);
		request.setAttribute("musiBoardcList", musicBoardList);
		return "/board/boardMain.jsp";
	}

}
