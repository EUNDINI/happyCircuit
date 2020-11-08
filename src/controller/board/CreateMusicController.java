package controller.board;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import controller.*;
import model.dao.MusicDAO;
import model.Music;
import model.MusicBoard;

public class CreateMusicController implements Controller {
	private MusicDAO MusicDAO = new MusicDAO();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//���� �޾ƿ��ڵ� �ʿ�
		
		String title = request.getParameter("title");
		int originalMusicId = Integer.parseInt(request.getParameter("originalMusicId"));
		int priorMusicId = Integer.parseInt(request.getParameter("priorMusicId"));
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId"); 
		File file = null;
		
		String genre = request.getParameter("genre");
		String content = request.getParameter("content");

		Music music = new Music(originalMusicId, priorMusicId, userId, title, genre, file);
		MusicBoard musicBoard = new MusicBoard(music, content, 0, 0);
		MusicDAO.createMusicBoard(musicBoard);

		return "redirect:/board/boardMain";
	}

}
