package controller.article;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import controller.*;
import model.dao.MusicDAO;
import model.Music;
import model.MusicArticle;

public class CreateMusicController implements Controller {
	private MusicDAO MusicDAO = new MusicDAO();

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// ���� �۾���
		String uploadPath = request.getServletContext().getRealPath("/") + "\\music";
		File Folder = new File(uploadPath);
		System.out.println(uploadPath);
		if (!Folder.exists()) {
			try {
				Folder.mkdir(); // ���� �����մϴ�.
				System.out.println("������ �����Ǿ����ϴ�.");
			} catch (Exception e) {
				e.getStackTrace();
			}
		}

		int maxSize = 1024 * 1024 * 10 * 10;

		MultipartRequest multi = new MultipartRequest(request, uploadPath, maxSize, "utf-8",
				new DefaultFileRenamePolicy());

		String fileName = multi.getFilesystemName("music"); // ���ϸ�
		String musicPath = "../music/" + fileName;
		String musicName = multi.getParameter("title");
		HttpSession session = request.getSession();
		String artistId = (String) session.getAttribute("artistId");
		String genre = multi.getParameter("genre");
		String content = multi.getParameter("content");

		Music music = new Music(0, 0, artistId, musicName, genre, 1, musicPath);
		MusicArticle musicArticle = new MusicArticle(music, content, 0, 0);
		try {

			int musicId = MusicDAO.createMusic(music);
			music.setMusicId(musicId);
			System.out.println(music.getMusicId());
			musicArticle.setMusicId(musicId);
			musicArticle.getMusic().setMusicId(musicId);
			
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
