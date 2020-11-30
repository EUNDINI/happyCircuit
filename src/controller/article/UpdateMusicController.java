package controller.article;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import controller.Controller;
import model.Music;
import model.MusicArticle;
import model.dao.MusicDAO;

public class UpdateMusicController implements Controller {

	private MusicDAO musicDAO = new MusicDAO();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
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

		String uploadPath = request.getServletContext().getRealPath("/") + "\\music";
		File Folder = new File(uploadPath);
		
		if (!Folder.exists()) {
			try{
			    Folder.mkdir(); //���� �����մϴ�.
			    System.out.println("������ �����Ǿ����ϴ�.");
		        } 
		        catch(Exception e){
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

		if (fileName != null) {
			String bePath = music.getMusicPath();
			String be = bePath.substring(bePath.lastIndexOf("/"));

			File f = new File(uploadPath + "\\" + be);
			if (f.exists()) {
				f.delete();
				System.out.println("���� ������");
			} else {
				System.out.println("���� ����");
			}
			
			music.setMusicPath(musicPath);
		}

		music.setMusicName(musicName);
		music.setGenre(genre);
		musicDAO.updateMusic(music);

		musicArticle.setMusic(music);
		musicArticle.setContent(content);
		musicDAO.updateMusicArticle(musicArticle);

		return "redirect:/article/articleMain";
	}

}
