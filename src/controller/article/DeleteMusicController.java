package controller.article;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Music;
import model.dao.MusicDAO;

public class DeleteMusicController implements Controller {

	private MusicDAO musicDAO = new MusicDAO();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// �ۼ��ڿ� �α��� ���̵� �˻� �߰�
		int musicId = Integer.parseInt(request.getParameter("musicId"));
		
		String uploadPath = request.getServletContext().getRealPath("music");
		Music music = musicDAO.findMusic(musicId);
		String bePath = music.getMusicPath();
		String be = bePath.substring(bePath.lastIndexOf("/"));

		File f = new File(uploadPath + "\\" + be);
		if (f.exists()) {
			f.delete();
			System.out.println("���� ������");
		} else {
			System.out.println("���� ����");
		}
		
		musicDAO.deleteMusicArticle(musicId);
		musicDAO.deleteMusic(musicId);
		
		return "redirect:/article/articleMain";
	}
}
