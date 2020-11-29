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

		// 검색해서 수정 폼 채우기!
		if (request.getMethod().equals("GET")) {
			// 작성자와 로그인 아이디 검사 추가
			// if(!musicDAO.isArticleWriter(musicId, artistId))
			// return "/article/home.jsp";

			request.setAttribute("musicArticle", musicArticle);

			return "/article/articleModify.jsp";
		}

		String uploadPath = request.getServletContext().getRealPath("/") + "\\music";
		File Folder = new File(uploadPath);
		
		if (!Folder.exists()) {
			try{
			    Folder.mkdir(); //폴더 생성합니다.
			    System.out.println("폴더가 생성되었습니다.");
		        } 
		        catch(Exception e){
			    e.getStackTrace();
			}
		}
			
		int maxSize = 1024 * 1024 * 10 * 10;

		MultipartRequest multi = new MultipartRequest(request, uploadPath, maxSize, "utf-8",
				new DefaultFileRenamePolicy());

		String fileName = multi.getFilesystemName("music"); // 파일명
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
				System.out.println("파일 삭제됨");
			} else {
				System.out.println("파일 없음");
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
