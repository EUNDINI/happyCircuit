package controller.article;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.ArticlePaging;
import model.Artist;
import model.MusicArticle;
import model.dao.ArtistDAO;
import model.dao.MusicDAO;

public class GetMusicListController implements Controller {
	private MusicDAO musicDAO = new MusicDAO();
	private ArtistDAO artistDAO = new ArtistDAO();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ArticlePaging paging = new ArticlePaging();
		int currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));

		paging.makeBlock(currentPage);
		paging.makeLastPageNum();
		List<MusicArticle> musicArticleList = musicDAO.findMusicArticleList(currentPage, 15);
		
		for(MusicArticle mA : musicArticleList) {
			Artist artist = artistDAO.findArtistById(mA.getMusic().getArtistId());
			mA.setArtist(artist);
		}

		request.setAttribute("musiArticleList", musicArticleList);

		request.setAttribute("blockStartNum", paging.getBlockStartNum());
		request.setAttribute("blockLastNum", paging.getBlockLastNum());
		request.setAttribute("lastPageNum", paging.getLastPageNum());
		request.setAttribute("page", currentPage);
		request.setAttribute("total", paging.getTotal());
		request.setAttribute("musicArticleList", musicArticleList);

		return "/article/articleMain.jsp";
	}

}
