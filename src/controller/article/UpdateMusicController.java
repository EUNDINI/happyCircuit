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
		HttpSession session = request.getSession();
		String artistId = (String) session.getAttribute("artistId"); 
		
		
		// �˻��ؼ� ���� �� ä���!
		if (request.getMethod().equals("GET")) {
			// �ۼ��ڿ� �α��� ���̵� �˻� �߰�
			int musicId = Integer.parseInt(request.getParameter("id"));
			if(!musicDAO.isArticleWriter(musicId, artistId))
				return "/article/articleMain.jsp";
			
			MusicArticle musicArticle = musicDAO.findMusicArticle(musicId);
			request.setAttribute("musicArticle", musicArticle);

			return "/article/articleModify.jsp";
		}
		
		String musicName = request.getParameter("title");
		String genre = request.getParameter("genre");
		int originalMusicId = Integer.parseInt(request.getParameter("originalMusicId"));
		int priorMusicId = Integer.parseInt(request.getParameter("priorMusicId"));
		int nth = 0;
		File file = null; // �޾ƿ� ����
		String musicPath = null;
		
		String content = request.getParameter("content");
		int readCount =  Integer.parseInt(request.getParameter("readCount"));
		int likeCount = Integer.parseInt(request.getParameter("likecount"));
		
		Music music = new Music(originalMusicId, priorMusicId, artistId, musicName, genre, nth, musicPath);
		MusicArticle musicArticle = new MusicArticle(music, content, readCount, likeCount);
		musicDAO.updateMusicArticle(musicArticle);
		
		return "redirect:/article/articleMain.jsp";
	}

}
