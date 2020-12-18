package controller.article;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Artist;
import model.MusicArticle;
import model.dao.ArtistDAO;
import model.dao.MusicDAO;

public class HistoryMusicController implements Controller {
	private MusicDAO musicDAO = new MusicDAO();
	private ArtistDAO artistDAO = new ArtistDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int musicId = Integer.parseInt(request.getParameter("musicId"));
		
		List<Integer> priorListMusicId = musicDAO.priorList(musicId);
		List<MusicArticle> priorList = new ArrayList<MusicArticle>();
		
		for(int i = 0; i < priorListMusicId.size(); i++) {
			MusicArticle priorMusicArticle = musicDAO.findMusicArticle(priorListMusicId.get(i));
			Artist artist = artistDAO.findArtistById(priorMusicArticle.getMusic().getArtistId());
			priorMusicArticle.setArtist(artist);
			
			priorList.add(priorMusicArticle);
		}
		
		request.setAttribute("priorList", priorList);
		
		return "/article/articleHistory.jsp";
	}

}
