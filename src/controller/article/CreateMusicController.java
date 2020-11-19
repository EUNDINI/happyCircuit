package controller.article;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import controller.*;
import model.dao.MusicDAO;
import model.Music;
import model.MusicArticle;

public class CreateMusicController implements Controller {
	private MusicDAO MusicDAO = new MusicDAO();

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//파일 받아오코드 필요
		
		String musicName = request.getParameter("title");
		int originalMusicId = Integer.parseInt(request.getParameter("originalMusicId"));
		int priorMusicId = Integer.parseInt(request.getParameter("priorMusicId"));
		int nth = Integer.parseInt(request.getParameter("nth"));
		HttpSession session = request.getSession();
		String artistId = (String) session.getAttribute("artistId"); 
		File file = null;
		String musicPath = null;
		
		String genre = request.getParameter("genre");
		String content = request.getParameter("content");

		Music music = new Music(originalMusicId, priorMusicId, artistId, musicName, genre, nth, musicPath);
		MusicArticle musicArticle = new MusicArticle(music, content, 0, 0);
		MusicDAO.createMusicArticle(musicArticle);

		return "redirect:/article/articleMain.jsp";
	}

}
