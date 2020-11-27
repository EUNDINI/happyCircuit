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
		// ���� �޾ƿ��ڵ� �ʿ�

		//���� �۾���
		String musicName = request.getParameter("title");
		HttpSession session = request.getSession();
		String artistId = (String) session.getAttribute("artistId");
		File file = null;
		String musicPath = null;

		String genre = request.getParameter("genre");
		String content = request.getParameter("content");

		Music music = new Music(0, 0, artistId, musicName, genre, 1, musicPath);
		MusicArticle musicArticle = new MusicArticle(music, content, 0, 0);

		try {
			MusicDAO.createMusicArticle(musicArticle);
			return "redirect:/article/articleMain";

		} catch (Exception e) { // ���� �߻� �� �Է� form���� forwarding
			request.setAttribute("creationFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("musicArticle", musicArticle);
			request.setAttribute("music", music);
			return "/article/articleWrite/form";
		}
	}

}
