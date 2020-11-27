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

		// �˻��ؼ� ���� �� ä���!
		if (request.getMethod().equals("GET")) {
			// �ۼ��ڿ� �α��� ���̵� �˻� �߰�
			// if(!musicDAO.isArticleWriter(musicId, artistId))
			// return "/article/home.jsp";

			request.setAttribute("musicArticle", musicArticle);

			return "/article/articleModify.jsp";
		}

		System.out.println(musicId);
		String musicName = request.getParameter("title");
		String genre = request.getParameter("genre");
		File file = null; // �޾ƿ� ����
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
