package controller.article;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.ArticlePaging;
import model.MusicArticle;
import model.dao.MusicDAO;

public class SearchMusicController implements Controller {
	private MusicDAO musicDAO = new MusicDAO();
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String condition = request.getParameter("condition");
		String search = request.getParameter("search");
		ArticlePaging paging = new ArticlePaging();
		int currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
		
		paging.makeBlock(currentPage);
		paging.makeLastPageNum(condition, search);
		List<MusicArticle> musicArticleList = musicDAO.SearchMusicArticle(condition, search, currentPage, 15);
		
		request.setAttribute("musicArticleList", musicArticleList);
		request.setAttribute("blockStartNum", paging.getBlockStartNum());
		request.setAttribute("blockLastNum", paging.getBlockLastNum());
		request.setAttribute("lastPageNum", paging.getLastPageNum());
		request.setAttribute("page", currentPage);
		request.setAttribute("condition", condition);
		request.setAttribute("search", search);
		request.setAttribute("total", paging.getTotal());
		
		return "/article/articleMain.jsp";
	}

}
