package controller.article;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.MusicArticle;
import model.dao.MusicDAO;

public class ReadMusicController implements Controller {
	private MusicDAO musicDAO = new MusicDAO();
	
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	MusicArticle musicArticle = null;
		int musicId = Integer.parseInt(request.getParameter("musicId"));
		musicArticle = musicDAO.findMusicArticle(musicId);			
		
		List<MusicArticle> nthCreationList = musicDAO.NthCreationMusicList(musicId);
		
		request.setAttribute("musicArticle", musicArticle);	
		request.setAttribute("NthCreationList", nthCreationList);
		return "/article/articleRead.jsp";
    }
}