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
		
		// �ۼ��ڿ� �α��� ���̵� �˻� �߰�
		
		// �˻��ؼ� ���� �� ä���!
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
		File file = null; // �޾ƿ� ����
		
		String content = request.getParameter("content");
		int readCount =  Integer.parseInt(request.getParameter("readCount"));
		int likeCount = Integer.parseInt(request.getParameter("likecount"));
		
		Music music = new Music(originalMusicId, priorMusicId, userId, userName, title, genre, file);
		MusicBoard musicBoard = new MusicBoard(music, content, readCount, likeCount);
		musicDAO.updateMusicBoard(musicBoard);
		
		return "redirect:/board/boardMain.jsp";
	}

}
