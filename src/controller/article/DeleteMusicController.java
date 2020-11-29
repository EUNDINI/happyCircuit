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
		// 작성자와 로그인 아이디 검사 추가
		int musicId = Integer.parseInt(request.getParameter("musicId"));
		
		String uploadPath = request.getServletContext().getRealPath("music");
		Music music = musicDAO.findMusic(musicId);
		String bePath = music.getMusicPath();
		String be = bePath.substring(bePath.lastIndexOf("/"));

		File f = new File(uploadPath + "\\" + be);
		if (f.exists()) {
			f.delete();
			System.out.println("파일 삭제됨");
		} else {
			System.out.println("파일 없음");
		}
		
		musicDAO.deleteMusicArticle(musicId);
		musicDAO.deleteMusic(musicId);
		
		return "redirect:/article/articleMain";
	}
}
