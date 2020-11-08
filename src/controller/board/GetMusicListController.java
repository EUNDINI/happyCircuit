package controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.MusicBoard;
import model.dao.MusicDAO;

public class GetMusicListController implements Controller {
	private MusicDAO musicDAO = new MusicDAO();
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<MusicBoard> musicBoardList = musicDAO.findMusicBoardList(0, 0); //������������ �������� ǥ���� ���� ��
		request.setAttribute("musiBoardcList", musicBoardList);
		return "/board/boardMain.jsp";
	}

}
