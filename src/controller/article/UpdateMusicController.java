package controller.article;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Music;
import model.MusicArticle;
import model.dao.MusicDAO;

public class UpdateMusicController implements Controller {

	private MusicDAO musicDAO = new MusicDAO();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(request.getParameter("content"));
		int musicId = Integer.parseInt(request.getParameter("musicId"));
		Music music = musicDAO.findMusic(musicId);
		MusicArticle musicArticle = musicDAO.findMusicArticle(musicId);

		// 검색해서 수정 폼 채우기!
		if (request.getMethod().equals("GET")) {
			// 작성자와 로그인 아이디 검사 추가
			// if(!musicDAO.isArticleWriter(musicId, artistId))
			// return "/article/home.jsp";

			request.setAttribute("musicArticle", musicArticle);

			return "/article/articleModify.jsp";
		}

		System.out.println(musicId);
		String musicName = request.getParameter("title");
		String genre = request.getParameter("genre");
		File file = null; // 받아온 파일
		String musicPath = null;

		String content = request.getParameter("content");

		music.setMusicName(musicName);
		music.setGenre(genre);
		music.setMusicPath(musicPath);
		musicDAO.updateMusic(music);

		musicArticle.setMusic(music);
		musicArticle.setContent(content);
		musicDAO.updateMusicArticle(musicArticle);

		return "redirect:/article/articleMain";
	}

}
