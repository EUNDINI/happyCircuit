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

public class CreateNthMusicController implements Controller {
	private MusicDAO musicDAO = new MusicDAO();

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
		
		int maxSize =1024 *1024 *10 * 10;
	   
	    MultipartRequest multi = new MultipartRequest(request,uploadPath,maxSize,"utf-8",new DefaultFileRenamePolicy());
	    
	    String fileName = multi.getFilesystemName("music"); //파일명
		String musicPath = "../music/" + fileName;
		String musicName = multi.getParameter("title");
		HttpSession session = request.getSession();
		String artistId = (String) session.getAttribute("artistId");
		String genre = multi.getParameter("genre");
		String content = multi.getParameter("content");
		
		System.out.println(multi.getParameter("priorMusicId"));
		
		int priorMusicId = Integer.parseInt(multi.getParameter("priorMusicId"));
		int originalMusicId = musicDAO.findOriginalMusicId(priorMusicId);
		
		if(originalMusicId == 0)
			originalMusicId = priorMusicId;
		
		int nth = musicDAO.findNth(priorMusicId) + 1;

		Music music = new Music(originalMusicId, priorMusicId, artistId, musicName, genre, nth, musicPath);
		MusicArticle musicArticle = new MusicArticle(music, content, 0, 0);

		try {
			musicDAO.createMusicArticle(musicArticle);
			return "redirect:/article/articleMain";

		} catch (Exception e) { // 예외 발생 시 입력 form으로 forwarding
			request.setAttribute("creationFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("musicArticle", musicArticle);
			request.setAttribute("music", music);
			return "/article/articleNthWrite/form";
		}
	}
}
