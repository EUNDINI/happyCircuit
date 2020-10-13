package controller.board;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dto.Music;
import model.dto.MusicBoard;
import model.dao.MusicDAO;

public class MusicUpdateController implements Controller {

	private MusicDAO musicDAO = new MusicDAO();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 작성자와 로그인 아이디 검사 추가
		
		// 검색해서 수정 폼 채우기!
		if (request.getMethod().equals("GET")) {
			long musicId = Integer.parseInt(request.getParameter("id"));
			MusicBoard musicBoard = musicDAO.findMusicBoard(musicId);
			request.setAttribute("musicboard", musicBoard);

			return "/board/boardModify.jsp";
		}
		
		String title = request.getParameter("title");
		String genre = request.getParameter("genre");
		long originalMusicId = Integer.parseInt(request.getParameter("originalMusicId"));
		long priorMusicId = Integer.parseInt(request.getParameter("priorMusicId"));
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		File file = null; // 받아온 파일
		
		String content = request.getParameter("content");
		int readCount =  Integer.parseInt(request.getParameter("readCount"));
		int likeCount = Integer.parseInt(request.getParameter("likecount"));
		
		Music music = new Music(originalMusicId, priorMusicId, userId, userName, title, genre, file);
		MusicBoard musicBoard = new MusicBoard(music, content, readCount, likeCount);
		musicDAO.updateMusicBoard(musicBoard);
		
		return "redirect:/board/boardMain.jsp";
	}

}
