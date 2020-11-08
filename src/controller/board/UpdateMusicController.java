package controller.board;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Music;
import model.MusicBoard;
import model.dao.MusicDAO;

public class UpdateMusicController implements Controller {

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
		int originalMusicId = Integer.parseInt(request.getParameter("originalMusicId"));
		int priorMusicId = Integer.parseInt(request.getParameter("priorMusicId"));
		//String nickname = request.getParameter("nickname");
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId"); 
		File file = null; // 받아온 파일
		
		String content = request.getParameter("content");
		int readCount =  Integer.parseInt(request.getParameter("readCount"));
		int likeCount = Integer.parseInt(request.getParameter("likecount"));
		
		Music music = new Music(originalMusicId, priorMusicId, userId, title, genre, file);
		MusicBoard musicBoard = new MusicBoard(music, content, readCount, likeCount);
		musicDAO.updateMusicBoard(musicBoard);
		
		return "redirect:/board/boardMain.jsp";
	}

}
