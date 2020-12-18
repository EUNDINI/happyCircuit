package controller.article;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.artist.ArtistSessionUtils;
import model.ArticlePaging;
import model.Artist;
import model.Music;
import model.MusicArticle;
import model.dao.ArtistDAO;
import model.dao.MusicDAO;
import model.dao.RecommendMusicDAO;

public class ReadMusicController implements Controller {
	private MusicDAO musicDAO = new MusicDAO();
	private RecommendMusicDAO recommendMusicDAO = new RecommendMusicDAO();
	private ArtistDAO artistDAO = new ArtistDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		String artistId = ArtistSessionUtils.getLoginArtistId(session);
		int musicId = Integer.parseInt(request.getParameter("musicId"));
		
		if (request.getParameter("like") != null) {
			musicDAO.increaseLikeCount(musicId);
			musicDAO.decreaseReadCount(musicId);
			recommendMusicDAO.create(artistId, musicId);
		}
		
		musicDAO.increaseReadCount(musicId);
		MusicArticle musicArticle = musicDAO.findMusicArticle(musicId);
		Artist artist = artistDAO.findArtistById(musicArticle.getMusic().getArtistId());
		musicArticle.setArtist(artist);
		
		int original = musicArticle.getMusic().getOriginalMusicId();
		
		
		List<Integer> nthListMusicId = musicDAO.nthList(musicId);
		List<MusicArticle> nthList = new ArrayList<MusicArticle>();
		
		for(int i = 0; i < nthListMusicId.size(); i++) {
			MusicArticle nth = musicDAO.findMusicArticle(nthListMusicId.get(i));
			Artist a = artistDAO.findArtistById(nth.getMusic().getArtistId());
			nth.setArtist(a);
			nthList.add(nth);
		}
		
		int currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
		ArticlePaging paging = new ArticlePaging();
		paging.makeBlock(currentPage);
		paging.makeLastPageNum(nthListMusicId.size());
		
		request.setAttribute("musicArticle", musicArticle);
		request.setAttribute("nthCreationList", nthList);
		request.setAttribute("blockStartNum", paging.getBlockStartNum());
		request.setAttribute("blockLastNum", paging.getBlockLastNum());
		request.setAttribute("lastPageNum", paging.getLastPageNum());
		request.setAttribute("page", currentPage);
		request.setAttribute("total", paging.getTotal());

		if (original != 0) {
			Music originalMusic = musicDAO.findMusic(original);
			int prior = musicArticle.getMusic().getPriorMusicId();
			Music priorMusic = musicDAO.findMusic(prior);

			request.setAttribute("originalMusicName", originalMusic.getMusicName());
			request.setAttribute("priorMusicName", priorMusic.getMusicName());
		}

		return "/article/articleRead.jsp";
	}
	
}
