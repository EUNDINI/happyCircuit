package controller.board;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import controller.*;
import model.dao.MusicDAO;
import model.dto.Music;
import model.dto.MusicBoard;

public class MusicCreateController implements Controller {
	private MusicDAO MusicDAO = new MusicDAO();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//파일 받아오코드 필요
		
		String title = request.getParameter("title");
		long originalMusicId = Integer.parseInt(request.getParameter("originalMusicId"));
		long priorMusicId = Integer.parseInt(request.getParameter("priorMusicId"));
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		File file = null;
		
		String genre = request.getParameter("genre");
		String content = request.getParameter("content");

		Music music = new Music(originalMusicId, priorMusicId, userId, userName, title, genre, file);
		MusicBoard musicBoard = new MusicBoard(music, content, 0, 0);
		MusicDAO.createMusicBoard(musicBoard);

		return "redirect:/board/boardMain";
	}

}
