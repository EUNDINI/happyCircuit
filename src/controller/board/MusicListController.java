package controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dao.MusicDAO;
import model.dto.MusicBoard;

public class MusicListController implements Controller {
	private MusicDAO musicDAO = new MusicDAO();
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<MusicBoard> musicBoardList = musicDAO.findMusicBoardList(0, 0); //������������ �������� ǥ���� ���� ��
		request.setAttribute("musiBoardcList", musicBoardList);
		return "/board/boardMain.jsp";
	}

}
